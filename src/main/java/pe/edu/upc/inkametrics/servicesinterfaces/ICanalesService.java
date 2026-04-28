package pe.edu.upc.inkametrics.servicesinterfaces;

import pe.edu.upc.inkametrics.entities.Canales;
import java.util.List;
import java.util.Optional;

public interface ICanalesService {
    public List<Canales> list();
    public Canales insert(Canales cnl);
    public Optional<Canales> listCanales(int id);
    public void update(Canales cnl);
    public void deleteById(int id_canal);
    public List<Canales>findByPlatformId(int idPlataforma);
    public List<Canales> buscarPorSeguidores(int min);
    public List<Object[]> contarCanalesPorPlataforma();
    public List<Object[]> promedioSeguidoresPorPlataforma();
}
