package pe.edu.upc.tpbackinkametrics.serviceinterfaces;


import pe.edu.upc.tpbackinkametrics.entities.Canal;
import pe.edu.upc.tpbackinkametrics.entities.Streamer;

import java.util.List;
import java.util.Optional;

public interface ICanalService {
    public List<Canal> list();
    public Canal insert(Canal canal);
    public Optional<Canal> listId(int id);
    public Canal update(Canal canal);
    public void delete(int id);
    public List<Canal> listByEmpresa(int idEmpresa);

}
