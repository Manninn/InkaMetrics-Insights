package upc.edu.pe.tpbackinkametrics.serviceinterfaces;


import upc.edu.pe.tpbackinkametrics.entities.Streamer;

import java.util.List;
import java.util.Optional;

public interface IStreamerService {
    public List<Streamer> list();
    public Streamer insert(Streamer streamer);
    public Optional<Streamer> listId(int id);
    public Streamer update(Streamer streamer);
    public void delete(int id);
    public List<Streamer> listByEmpresa(int idEmpresa);
}
