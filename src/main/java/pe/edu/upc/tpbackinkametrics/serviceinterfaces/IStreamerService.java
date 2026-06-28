package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Streamer;

import java.util.List;
import java.util.Optional;

public interface IStreamerService {
    public List<Streamer> list();
    public Streamer insert(Streamer streamer);
    public Optional<Streamer> listId(int id);
    public Streamer update(Streamer streamer);
    public void delete(int id);
    public List<Streamer> listByCompany(int companyId);
}
