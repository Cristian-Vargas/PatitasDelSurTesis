package mx.com.gm.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService") //NOMBRE REAL 
@Slf4j //Para manejo de login
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuariodao; //Sirve para interactuar tanto con la clase de USUARIO como la de ROL que se definio en usuario.
    
    //Este metodo obtiene el objeto de usuario filtrado por un username
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        
            Usuario usuario = usuariodao.findByUsername(username); //Recupera el objeto de usuario con el rol asociado
        
            //Si el usuario no existe Informa que no se encontro ese usuario.
         if(usuario == null){
                throw new UsernameNotFoundException(username);
            }
        
        
            //Si el usuario existe lista lis roles. 
            var roles = new ArrayList<GrantedAuthority>(); //Si el usuario se encuentra toma la lista. Ya que admin tiene dos usuarios (ADMIN,USER)
        
             for(Rol rol: usuario.getRoles())        //Se recorre la lista para capturar los roles
             {
                roles.add(new SimpleGrantedAuthority(rol.getNombre()));
             }
        
            //Finalmente, este metodo debe regresar es un objeto de tipo UserDetails con la informacion capturada (USUARIO Y ROLES)
            //La clase USER implementa de UserDetails. Es una clase de SPRING para que trabaje de manera automatica.
            return new User(usuario.getUsername(),usuario.getPassword(), roles);
  
    
    }
    
    
    
}