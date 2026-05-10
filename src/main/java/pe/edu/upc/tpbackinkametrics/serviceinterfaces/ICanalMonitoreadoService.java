package pe.edu.upc.tpbackinkametrics.serviceinterfaces;


import pe.edu.upc.tpbackinkametrics.entities.CanalMonitoreado;

import java.util.List;
import java.util.Optional;

public interface ICanalMonitoreadoService {
    public List<CanalMonitoreado> list();
    public CanalMonitoreado insert(CanalMonitoreado CanalMo);
    public Optional<CanalMonitoreado> listId(int id);
    public CanalMonitoreado update(CanalMonitoreado CanalMo);
    public void delete(int id);
    public List<CanalMonitoreado> listByEmpresa(int idEmpresa);
}
