package mx.com.gm.servicio;

import mx.com.gm.dao.RolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDao rolDao;
    
    @Override
    @Transactional
    public void guardarRol(Long usuario) {
       rolDao.guardarRol(usuario);
    }
    
}
