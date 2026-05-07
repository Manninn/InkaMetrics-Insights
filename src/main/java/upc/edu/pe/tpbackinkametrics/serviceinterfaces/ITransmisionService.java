package upc.edu.pe.tpbackinkametrics.serviceinterfaces;


import upc.edu.pe.tpbackinkametrics.entities.Transmision;

import java.util.List;
import java.util.Optional;

public interface ITransmisionService {
    public List<Transmision> list();
    public Transmision insert(Transmision transmision);
    public Optional<Transmision> listId(int id);
    public Transmision update(Transmision transmision);
    public void delete(int id);
    public List<Transmision> listByEmpresa(int idEmpresa);

}
