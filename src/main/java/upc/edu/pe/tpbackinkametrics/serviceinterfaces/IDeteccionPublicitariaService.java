package upc.edu.pe.tpbackinkametrics.serviceinterfaces;

import upc.edu.pe.tpbackinkametrics.entities.DeteccionPublicitaria;

import java.util.List;
import java.util.Optional;

public interface IDeteccionPublicitariaService {
    public List<DeteccionPublicitaria> list();
    public DeteccionPublicitaria insert(DeteccionPublicitaria deteccionPublicitaria);
    public Optional<DeteccionPublicitaria> listId(int id);
    public DeteccionPublicitaria update(DeteccionPublicitaria deteccionPublicitaria);
    public void delete(int id);
    public List<DeteccionPublicitaria> listByEmpresa(int idEmpresa);
}
