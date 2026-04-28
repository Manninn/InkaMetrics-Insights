package pe.edu.upc.inkametrics.servicesinterfaces;
import pe.edu.upc.inkametrics.entities.Plataformas;

import java.util.List;
import java.util.Optional;

public interface IPlataformasService {
    public List<Plataformas> list();
    public Plataformas insert(Plataformas p);
    public Optional<Plataformas> ListById(int id_plataforma);
    public void update(Plataformas p);
    public void delete(int id_plataforma);
    public List<Plataformas> buscarPorNombre(String nombre);
}