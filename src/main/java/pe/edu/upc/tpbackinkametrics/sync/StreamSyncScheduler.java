package pe.edu.upc.tpbackinkametrics.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.*;
import pe.edu.upc.tpbackinkametrics.repositories.*;
import pe.edu.upc.tpbackinkametrics.sync.dto.KickChannelData;
import pe.edu.upc.tpbackinkametrics.sync.dto.TwitchStreamData;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StreamSyncScheduler {

    @Autowired private ICanalMonitoreadoRepository canalMonitoreadoRepo;
    @Autowired private ICanalRepository canalRepo;
    @Autowired private ITransmisionRepository transmisionRepo;
    @Autowired private IMetricaSnapshotRepository metricaRepo;
    @Autowired private IRegionRepository regionRepo;

    @Autowired private TwitchApiService twitchApi;
    @Autowired private KickApiService kickApi;

    private String ultimaSincronizacion = "Nunca";
    private int canalesSincronizados = 0;
    private String ultimoError = "";

    @Scheduled(fixedRateString = "${sync.interval-ms:300000}")
    public void sincronizar() {
        System.out.println("[Sync] Iniciando sincronización con APIs de streaming...");
        int contador = 0;

        List<CanalMonitoreado> monitoreados = canalMonitoreadoRepo.findAll();
        for (CanalMonitoreado cm : monitoreados) {
            Canal canal = cm.getCanal();
            if (canal == null || canal.getUrlCanal() == null) continue;

            String plataforma = canal.getPlataforma().getNombre().toLowerCase();
            String url = canal.getUrlCanal();
            String username = extraerUsername(url);
            if (username == null) continue;

            try {
                if (plataforma.contains("twitch")) {
                    contador += sincronizarTwitch(canal, username) ? 1 : 0;
                } else if (plataforma.contains("kick")) {
                    contador += sincronizarKick(canal, username) ? 1 : 0;
                }
            } catch (Exception e) {
                ultimoError = "Canal " + username + ": " + e.getMessage();
                System.err.println("[Sync] " + ultimoError);
            }
        }

        canalesSincronizados = contador;
        ultimaSincronizacion = LocalDate.now().toString();
        System.out.println("[Sync] Completado. Canales actualizados: " + contador);
    }

    private boolean sincronizarTwitch(Canal canal, String username) {
        if (!twitchApi.isConfigured()) return false;

        // Actualizar seguidores del canal
        int seguidores = twitchApi.getFollowers(username);
        if (seguidores > 0) {
            canal.setSeguidoresActuales(seguidores);
            canalRepo.save(canal);
        }

        // Buscar stream en vivo
        Optional<TwitchStreamData> streamOpt = twitchApi.getStream(username);

        // Marcar transmisiones previas como terminadas
        Optional<Transmision> transmisionActiva =
            transmisionRepo.findFirstByCanal_IdCanalAndEnVivoTrue(canal.getIdCanal());

        if (streamOpt.isPresent()) {
            TwitchStreamData stream = streamOpt.get();
            Transmision t = transmisionActiva.orElseGet(Transmision::new);
            t.setTituloStream(stream.getTitle() != null ? stream.getTitle() : "Sin título");
            t.setEnVivo(true);
            t.setFechaInicio(LocalDate.now());
            t.setFechaFin(LocalDate.now());
            t.setCanal(canal);
            t = transmisionRepo.save(t);

            // Guardar snapshot de viewers
            MetricaSnapshot snap = new MetricaSnapshot();
            snap.setNombre("viewers");
            snap.setCantidad(stream.getViewerCount());
            snap.setTransmision(t);
            metricaRepo.save(snap);

            // Guardar snapshot del juego/categoría si existe
            if (stream.getGameName() != null && !stream.getGameName().isBlank()) {
                MetricaSnapshot snapJuego = new MetricaSnapshot();
                snapJuego.setNombre("categoria:" + stream.getGameName());
                snapJuego.setCantidad(1);
                snapJuego.setTransmision(t);
                metricaRepo.save(snapJuego);
            }

            System.out.println("[Twitch] " + username + " EN VIVO — " + stream.getViewerCount() + " viewers");
        } else {
            // No está en vivo: cerrar transmisión activa si existía
            transmisionActiva.ifPresent(t -> {
                t.setEnVivo(false);
                t.setFechaFin(LocalDate.now());
                transmisionRepo.save(t);
                System.out.println("[Twitch] " + username + " offline — transmisión cerrada");
            });
        }
        return true;
    }

    private boolean sincronizarKick(Canal canal, String username) {
        Optional<KickChannelData> dataOpt = kickApi.getChannel(username);
        if (dataOpt.isEmpty()) return false;

        KickChannelData data = dataOpt.get();

        // Actualizar seguidores
        if (data.getFollowersCount() > 0) {
            canal.setSeguidoresActuales(data.getFollowersCount());
            canalRepo.save(canal);
        }

        Optional<Transmision> transmisionActiva =
            transmisionRepo.findFirstByCanal_IdCanalAndEnVivoTrue(canal.getIdCanal());

        if (data.getLivestream() != null) {
            KickChannelData.KickLivestream live = data.getLivestream();
            Transmision t = transmisionActiva.orElseGet(Transmision::new);
            t.setTituloStream(live.getSessionTitle() != null ? live.getSessionTitle() : "Sin título");
            t.setEnVivo(true);
            t.setFechaInicio(LocalDate.now());
            t.setFechaFin(LocalDate.now());
            t.setCanal(canal);
            t = transmisionRepo.save(t);

            MetricaSnapshot snap = new MetricaSnapshot();
            snap.setNombre("viewers");
            snap.setCantidad(live.getViewers());
            snap.setTransmision(t);
            metricaRepo.save(snap);

            System.out.println("[Kick] " + username + " EN VIVO — " + live.getViewers() + " viewers");
        } else {
            transmisionActiva.ifPresent(t -> {
                t.setEnVivo(false);
                t.setFechaFin(LocalDate.now());
                transmisionRepo.save(t);
                System.out.println("[Kick] " + username + " offline — transmisión cerrada");
            });
        }
        return true;
    }

    // Extrae el username de URLs como:
    // https://www.twitch.tv/shroud → shroud
    // https://kick.com/shroud       → shroud
    private String extraerUsername(String url) {
        if (url == null || url.isBlank()) return null;
        String clean = url.trim().replaceAll("/$", "");
        int lastSlash = clean.lastIndexOf('/');
        if (lastSlash < 0 || lastSlash == clean.length() - 1) return null;
        String username = clean.substring(lastSlash + 1);
        return username.isBlank() ? null : username;
    }

    // Getters para el endpoint de estado
    public String getUltimaSincronizacion() { return ultimaSincronizacion; }
    public int getCanalesSincronizados() { return canalesSincronizados; }
    public String getUltimoError() { return ultimoError; }
}
