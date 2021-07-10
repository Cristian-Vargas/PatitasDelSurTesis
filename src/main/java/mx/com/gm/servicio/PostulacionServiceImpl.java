package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.InterfacePostulacionDao;
import mx.com.gm.domain.Mascota;
import mx.com.gm.domain.Postulacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostulacionServiceImpl implements PostulacionService {
    
    @Autowired
    private InterfacePostulacionDao postulacionDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Postulacion> listarPostulacion() {
       return (List<Postulacion>) postulacionDao.findAll();
    }

    @Override
    @Transactional
    public void guardarPostulacion(Postulacion postulacion) {
        postulacionDao.save(postulacion);
    }

    @Override
    @Transactional
    public void eliminarPostulacion(Postulacion postulacion) {
        postulacionDao.delete(postulacion);
    }

    @Override
    @Transactional(readOnly=true)
    public Postulacion encontrarPostulacion(Postulacion postulacion) {
       return postulacionDao.findById(postulacion.getId_postulacion()).orElse(null);
    }

    @Override
    public List<Postulacion> findPostulacionbyMascota(String nombreMascota) {
        return (List<Postulacion>) postulacionDao.findPostulacionbyMascota(nombreMascota);
    }
    
    @Override
    public List<Postulacion> findPostulacionbyPersona(String nombrePersona) {
        return (List<Postulacion>) postulacionDao.findPostulacionbyPersona(nombrePersona);
    }
    
    
}
