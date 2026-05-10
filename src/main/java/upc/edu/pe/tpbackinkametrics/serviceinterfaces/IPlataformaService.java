package upc.edu.pe.tpbackinkametrics.serviceinterfaces;


import upc.edu.pe.tpbackinkametrics.entities.Plataforma;

import java.util.List;
import java.util.Optional;

public interface IPlataformaService {
    public List<Plataforma> list();
    public Plataforma insert(Plataforma plataforma);
    public Optional<Plataforma> listId(int id);
    public Plataforma update(Plataforma plataforma);
    public void delete(int id);
}
