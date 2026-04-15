package pe.edu.upc.inkametrics.servicesinterfaces;

import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.entities.Plataformas;

import java.util.List;
import java.util.Optional;

public interface ICanalesService {
    public List<Canales> list();

    public Canales insert(Canales canales);

    public Canales update(Canales canales);
    public Optional<Canales> findById(Integer id);

    public void delete(int id);


    List<Canales>findByPlataformaId(Integer id);
    Canales insertPlataformaIdAndCanale(Integer id, Canales canale);
}
