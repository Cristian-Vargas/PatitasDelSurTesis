package mx.com.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
@Table(name= "usuario") //Tabla en MYSQL
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    
    //RELACION ENTRE CLASES:
    @OneToMany   //Esta anotacion sirve para hacer el mapeo entre la clase usuario y rol.
    @JoinColumn(name="id_usuario") //Se debe colocar el nombre que figura en SQL.
    private List<Rol> roles; //Sirve para recuperar los roles asociados a un usuario.
    
    
    
}