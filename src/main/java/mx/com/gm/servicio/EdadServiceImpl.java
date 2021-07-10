package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.InterfaceEdadDao;
import mx.com.gm.domain.Edad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EdadServiceImpl implements EdadService {

    @Autowired
    private InterfaceEdadDao edadDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Edad> listarEdad() {
       return (List<Edad>) edadDao.findAll();
    }

    @Override
    @Transactional
    public void guardarEdad(Edad edad) {
        edadDao.save(edad);
    }

    @Override
    @Transactional
    public void eliminarEdad(Edad edad) {
        edadDao.delete(edad);
    }

    @Override
    @Transactional(readOnly=true)
    public Edad encontrarEdad(Edad edad) {
       return edadDao.findById(edad.getId_edad()).orElse(null);
    }
    
}
