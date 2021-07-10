package mx.com.gm.dao;

import java.util.List;
import mx.com.gm.domain.Mascota;
import mx.com.gm.domain.Postulacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InterfacePostulacionDao extends CrudRepository<Postulacion, Long>{
    
    @Query("select m from Postulacion m where m.estado is null")
    List<Postulacion> findAllByPostulacionSinEstado();
    
    
    @Query("select m from Postulacion m where m.estado ='Adoptado'")
    List<Postulacion> findAllByAdoptados();
    
    //Mascotas adoptadas por nombre mascota
    @Query("FROM Postulacion m WHERE m.estado ='Adoptado' and m.mascota.nombre like %:nombreMascota%")
    List<Postulacion> findPostulacionbyMascota(@Param("nombreMascota") String nombreMascota);
    
    //Mascotas adoptadas por nombre persona
    @Query("FROM Postulacion m WHERE m.estado ='Adoptado' and m.persona.nombre like %:nombrePersona%")
    List<Postulacion> findPostulacionbyPersona(@Param("nombrePersona") String nombrePersona);
    
    
}
