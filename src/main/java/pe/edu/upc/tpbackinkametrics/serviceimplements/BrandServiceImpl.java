package pe.edu.upc.tpbackinkametrics.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tpbackinkametrics.entities.Brand;
import pe.edu.upc.tpbackinkametrics.repositories.IBrandRepository;
import pe.edu.upc.tpbackinkametrics.serviceinterfaces.IBrandService;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public List<Brand> list() {
        return brandRepository.findAll();
    }

    @Override
    public Brand insert(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Optional<Brand> listId(int id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand update(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        brandRepository.deleteById(id);
    }
}
