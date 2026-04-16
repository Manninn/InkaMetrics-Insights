package pe.edu.upc.inkametrics.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inkametrics.entities.Canales;
import pe.edu.upc.inkametrics.entities.Canales_monitoreados;
import pe.edu.upc.inkametrics.repositories.ICanales_monitoreadosRepository;
import pe.edu.upc.inkametrics.servicesinterfaces.ICanales_monitoreadosService;

import java.util.List;

@Service
public class Canales_monitoreadosServiceImplement implements ICanales_monitoreadosService {
    @Autowired
    private ICanales_monitoreadosRepository cR; // primero

    // luego debe arreglar el error que saldrá en la linea trece y lo de abajo (@Override y demás) se autogenerará
    @Override
    public List<Canales_monitoreados> list() {
        return cR.findAll();
    } // esas 4 lineas se autogeneran
}
