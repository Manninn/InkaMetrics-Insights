package pe.edu.upc.inkametrics_backend.serviceinterfaces;

import pe.edu.upc.inkametrics_backend.entities.Brand;

import java.util.List;
import java.util.Optional;


public interface IBrandService {
    public List<Brand> list();
    public Brand insert (Brand b);
    public Optional<Brand> listId(int idBrand);
    public void update(Brand b);
    public void delete(int idBrand);




}
