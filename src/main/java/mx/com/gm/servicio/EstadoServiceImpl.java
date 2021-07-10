package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.InterfaceEstadoDao;
import mx.com.gm.domain.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoServiceImpl implements EstadoService{
    
    @Autowired
    private InterfaceEstadoDao estadoDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Estado> listarEstado() {
        return (List<Estado>) estadoDao.findAll();
    }

    @Override
    @Transactional
    public void guardarEstado(Estado estado) {
        estadoDao.save(estado);
    }

    @Override
    @Transactional
    public void eliminarEstado(Estado estado) {
        estadoDao.delete(estado);
    }

    @Override
    @Transactional(readOnly=true)
    public Estado encontrarEstado(Estado estado) {
       return estadoDao.findById(estado.getId_estado()).orElse(null);
    }
}
