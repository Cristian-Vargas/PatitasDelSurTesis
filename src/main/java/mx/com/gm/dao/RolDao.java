
package mx.com.gm.dao;
import mx.com.gm.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolDao extends JpaRepository<Rol, Long>{
    
    @Modifying
    @Query(value = "INSERT INTO Rol (nombre, id_usuario) values ('ROLE_USER', :usuario)",nativeQuery = true)
    void guardarRol(@Param("usuario") Long usuario);
    
}
