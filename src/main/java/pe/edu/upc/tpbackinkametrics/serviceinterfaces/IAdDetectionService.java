package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.AdDetection;

import java.util.List;
import java.util.Optional;

public interface IAdDetectionService {
    public List<AdDetection> list();
    public AdDetection insert(AdDetection adDetection);
    public Optional<AdDetection> listId(int id);
    public AdDetection update(AdDetection adDetection);
    public void delete(int id);
    public List<AdDetection> listByCompany(int companyId);
}
