package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.InterfaceTamanoDao;
import mx.com.gm.domain.Tamano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TamanoServiceImpl implements TamanoService {
    
    @Autowired
    private InterfaceTamanoDao tamanoDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Tamano> listarTamano() {
       return (List<Tamano>) tamanoDao.findAll();
    }

    @Override
    @Transactional
    public void guardarTamano(Tamano tamano) {
        tamanoDao.save(tamano);
    }

    @Override
    @Transactional
    public void eliminarTamano(Tamano tamano) {
        tamanoDao.delete(tamano);
    }

    @Override
    @Transactional(readOnly=true)
    public Tamano encontrarTamano(Tamano tamano) {
       return tamanoDao.findById(tamano.getId_tamano()).orElse(null);
    }
}
