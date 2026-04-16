package pe.edu.upc.inkametrics_backend.serviceinterfaces;

import pe.edu.upc.inkametrics_backend.entities.DetectionAdvertising;
import pe.edu.upc.inkametrics_backend.entities.Transmission;

import java.util.List;
import java.util.Optional;

public interface ITransmissionService {


    public List<Transmission> list();
    public Transmission insert (Transmission t);
    public Optional<Transmission> listId(int idTransmission);
    public void update(Transmission t);
    public void delete(int idTransmission);

}
