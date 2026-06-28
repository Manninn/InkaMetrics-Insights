package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Broadcast;

import java.util.List;
import java.util.Optional;

public interface IBroadcastService {
    public List<Broadcast> list();
    public Broadcast insert(Broadcast broadcast);
    public Optional<Broadcast> listId(int id);
    public Broadcast update(Broadcast broadcast);
    public void delete(int id);
    public List<Broadcast> listByCompany(int companyId);
}
