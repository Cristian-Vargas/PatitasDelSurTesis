package mx.com.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="rol")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idRol;
    
    @NotEmpty
    private String nombre;
    
//    @OneToOne
//    @JoinColumn(name="id_usuario")
//    private Usuario usuario;
    
    
    
    
}
