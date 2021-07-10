
package mx.com.gm.dao;

///Esta clase sirve para acceder a metodos ya establecidos en relacion a la administracion de usuarios.

import mx.com.gm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    
    Usuario findByUsername(String username); //Va a buscar un usuario. 
    
}
