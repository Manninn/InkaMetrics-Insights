package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.repositories.ICanalesRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanalesService;

import java.util.List;
import java.util.Optional;

@Service
public class CanalesServiceImplement implements ICanalesService {
    @Autowired
    private ICanalesRepository cR;

    @Override
    public List<Canales> list() {
        return cR.findAll();
    }

    @Override
    public Canales insert(Canales cnl) {
        return cR.save(cnl);
    }

    @Override
    public Optional<Canales> listCanales(int id) {
        return cR.findById(id);
    }

    @Override
    public void update(Canales cnl) {
        cR.save(cnl);
    }

    @Override
    public void deleteById(int id_canal) {
        cR.deleteById(id_canal);
    }

    @Override
    public List<Canales> findByPlatformId(int idPlataforma) {
        return cR.findByPlataforma_IdPlataforma(idPlataforma);
    }

    @Override
    public List<Canales> buscarPorSeguidores(int min) {
        return cR.buscarPorSeguidores(min);
    }

    @Override
    public List<Object[]> contarCanalesPorPlataforma() {
        return cR.contarCanalesPorPlataforma();
    }

    @Override
    public List<Object[]> promedioSeguidoresPorPlataforma() {
        return cR.promedioSeguidoresPorPlataforma();
    }
}
