package pe.edu.upc.inkametrics_backend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics_backend.entities.Brand;
import pe.edu.upc.inkametrics_backend.repositories.IBrandRepository;
import pe.edu.upc.inkametrics_backend.serviceinterfaces.IBrandService;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImplement implements IBrandService {

    @Autowired
    private IBrandRepository bR;

    @Override
    public List<Brand> list() {return bR.findAll();}

    @Override
    public Brand insert(Brand b) {
        return bR.save(b);
    }

    @Override
    public void update(Brand b) { bR.save(b);

    }

    @Override
    public void delete(int idBrand) {
        bR.deleteById(idBrand);
    }

    @Override
    public Optional<Brand> listId(int idBrand) {
        return bR.findById(idBrand);
    }




}
