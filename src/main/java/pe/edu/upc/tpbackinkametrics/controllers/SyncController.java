package pe.edu.upc.tpbackinkametrics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tpbackinkametrics.sync.StreamSyncScheduler;

import java.util.Map;

@RestController
@RequestMapping("/api/sync")
public class SyncController {

    @Autowired
    private StreamSyncScheduler scheduler;

    // GET /api/sync/estado — muestra cuándo fue la última sync y cuántos canales
    @GetMapping("/estado")
    public ResponseEntity<Map<String, Object>> estado() {
        return ResponseEntity.ok(Map.of(
            "ultimaSincronizacion", scheduler.getUltimaSincronizacion(),
            "canalesSincronizados", scheduler.getCanalesSincronizados(),
            "ultimoError", scheduler.getUltimoError()
        ));
    }

    // POST /api/sync/ejecutar — dispara la sync inmediatamente sin esperar el timer
    @PostMapping("/ejecutar")
    public ResponseEntity<Map<String, String>> ejecutar() {
        new Thread(scheduler::sincronizar).start();
        return ResponseEntity.ok(Map.of("mensaje", "Sincronización iniciada en segundo plano"));
    }
}
