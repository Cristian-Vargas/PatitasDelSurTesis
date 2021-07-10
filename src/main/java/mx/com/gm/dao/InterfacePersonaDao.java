
package mx.com.gm.dao;

import mx.com.gm.domain.Persona;
import mx.com.gm.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface InterfacePersonaDao extends CrudRepository<Persona, Long> {
    
    //Aqui van las consultas personalizadas, sin embargo al hacer ctr+click en
    //crudrepository se pueden visualizar las consultas preestablecidas que pueden utilizarce
    
    @Query("select p from Persona p where p.usuario = :usuario")
    Persona findByUsuario(@Param("usuario") Usuario usuario);
    
}
