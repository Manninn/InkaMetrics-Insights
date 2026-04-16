package pe.edu.upc.inkametrics_backend.serviceinterfaces;

import pe.edu.upc.inkametrics_backend.entities.Brand;
import pe.edu.upc.inkametrics_backend.entities.DetectionAdvertising;

import java.util.List;
import java.util.Optional;

public interface IDetectionAdvertisingService {

    public List<DetectionAdvertising> list();
    public DetectionAdvertising insert (DetectionAdvertising d);
    public Optional<DetectionAdvertising> listId(int idDetectionAdvertising);
    public void update(DetectionAdvertising d);
    public void delete(int idDetectionAdvertising);



}
