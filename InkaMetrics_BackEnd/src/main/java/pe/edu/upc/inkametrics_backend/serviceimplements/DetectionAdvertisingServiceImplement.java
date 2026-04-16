package pe.edu.upc.inkametrics_backend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics_backend.entities.DetectionAdvertising;
import pe.edu.upc.inkametrics_backend.repositories.IDetectionAdvertisingRepository;
import pe.edu.upc.inkametrics_backend.serviceinterfaces.IDetectionAdvertisingService;

import java.util.List;
import java.util.Optional;

@Service
public class DetectionAdvertisingServiceImplement implements IDetectionAdvertisingService {

    @Autowired
    private IDetectionAdvertisingRepository  dA;


    @Override
    public List<DetectionAdvertising> list() {
        return dA.findAll();
    }

    @Override
    public DetectionAdvertising insert(DetectionAdvertising d) {
        return dA.save(d);
    }

    @Override
    public void update(DetectionAdvertising d) { dA.save(d); }


    @Override
    public void delete(int idDetectionAdvertising)
    {dA.deleteById(idDetectionAdvertising);}


    @Override
    public Optional<DetectionAdvertising> listId(int idDetectionAdvertising) {
        return dA.findById(idDetectionAdvertising);}





}
