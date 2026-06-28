package pe.edu.upc.tpbackinkametrics.serviceinterfaces;

import pe.edu.upc.tpbackinkametrics.entities.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {
    public List<Brand> list();
    public Brand insert(Brand brand);
    public Optional<Brand> listId(int id);
    public Brand update(Brand brand);
    public void delete(int id);
}
