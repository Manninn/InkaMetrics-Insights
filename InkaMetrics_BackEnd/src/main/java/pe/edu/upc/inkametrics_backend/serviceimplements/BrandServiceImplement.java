package pe.edu.upc.inkametrics_backend.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics_backend.entities.Brand;
import pe.edu.upc.inkametrics_backend.repositories.IBrandRepository;
import pe.edu.upc.inkametrics_backend.serviceinterfaces.IBrandService;

import java.util.List;

@Service
public class BrandServiceImplement implements IBrandService {

    @Autowired
    private IBrandRepository bR;

    @Override
    public List<Brand> list() {return bR.findAll();}


}
