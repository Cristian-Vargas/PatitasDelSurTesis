package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Mascota;
import mx.com.gm.domain.Postulacion;

public interface PostulacionService {
    
    public List<Postulacion> listarPostulacion();
    
    public void guardarPostulacion (Postulacion postulacion);
    
    public void eliminarPostulacion(Postulacion postulacion);
    
    public Postulacion encontrarPostulacion(Postulacion postulacion);
    
    List<Postulacion> findPostulacionbyMascota(String nombreMascota);
    
    List<Postulacion> findPostulacionbyPersona(String nombrePersona);
}
