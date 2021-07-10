package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.InterfaceSexoDao;
import mx.com.gm.domain.Sexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SexoServiceImpl implements SexoService{
    
    @Autowired
    private InterfaceSexoDao sexoDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Sexo> listarSexo() {
       return (List<Sexo>) sexoDao.findAll();
    }

    @Override
    @Transactional
    public void guardarSexo(Sexo sexo) {
        sexoDao.save(sexo);
    }

    @Override
    @Transactional
    public void eliminarSexo(Sexo sexo) {
        sexoDao.delete(sexo);
    }

    @Override
    @Transactional(readOnly=true)
    public Sexo encontrarSexo(Sexo sexo) {
       return sexoDao.findById(sexo.getId_sexo()).orElse(null);
    }
    
    
    
    
    
}
