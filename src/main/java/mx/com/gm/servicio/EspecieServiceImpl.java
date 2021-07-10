package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.InterfaceEspecieDao;
import mx.com.gm.domain.Especie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EspecieServiceImpl implements EspecieService{
    
    @Autowired
    private InterfaceEspecieDao especieDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Especie> listarEspecie() {
        return (List<Especie>) especieDao.findAll();
    }

    @Override
    @Transactional
    public void guardarEspecie(Especie especie) {
        especieDao.save(especie);
    }

    @Override
    @Transactional
    public void eliminarEspecie(Especie especie) {
        especieDao.delete(especie);
    }

    @Override
    @Transactional(readOnly=true)
    public Especie encontrarEspecieID(Especie especie) {
       return especieDao.findById(especie.getId_especie()).orElse(null);
    }
    
    
}
