package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Platform;

import java.util.List;
import java.util.Optional;

public interface IPlatformService {
    public List<Platform> list();
    public Platform insert(Platform platform);
    public Optional<Platform> listId(int id);
    public Platform update(Platform platform);
    public void delete(int id);
}
