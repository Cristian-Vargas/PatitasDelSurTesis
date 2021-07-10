package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.InterfaceMascotaDao;
import mx.com.gm.domain.Edad;
import mx.com.gm.domain.Especie;
import mx.com.gm.domain.Estado;
import mx.com.gm.domain.Mascota;
import mx.com.gm.domain.Sexo;
import mx.com.gm.domain.Tamano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MascotaServiceImpl implements MascotaService {
    
    @Autowired
    private InterfaceMascotaDao mascotaDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Mascota> listarMascota() {
        return (List<Mascota>) mascotaDao.findAll();
    }

    @Override
    @Transactional
    public void guardarMascota(Mascota mascota) {
        mascotaDao.save(mascota);
    }

    @Override
    @Transactional
    public void eliminarMascota(Mascota mascota) {
        mascotaDao.delete(mascota);
    }

    @Override
    @Transactional(readOnly=true)
    public Mascota encontrarMascota(Mascota mascota) {
       return mascotaDao.findById(mascota.getId_mascota()).orElse(null);
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Mascota> listarMascotaPorEspecie(Especie especie) {
        return (List<Mascota>) mascotaDao.findByEspecie(especie);
    }

    @Override
    public List<Mascota> findMascotasbyFiltros(Especie especie, Edad edad, Sexo sexo, Tamano tamano) {
        return (List<Mascota>) mascotaDao.findMascotasbyFiltros(especie, edad,sexo,tamano);
    }
    
    @Override
    public List<Mascota> findMascotasbyFiltrosAdmin(Especie especie, Edad edad, Sexo sexo, Tamano tamano) {
        return (List<Mascota>) mascotaDao.findMascotasbyFiltrosAdmin(especie, edad,sexo,tamano);
    }

    @Override
    public List<Mascota> findMascotasbyNombre(String nombre) {
        return (List<Mascota>) mascotaDao.findMascotasbyNombre(nombre);
    }

    
}
