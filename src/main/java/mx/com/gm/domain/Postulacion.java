package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name= "postulacion") //Tabla en MYSQL
public class Postulacion implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_postulacion;
    
    @OneToOne
    @JoinColumn(name="id_mascota")
    private Mascota mascota;
    
    @OneToOne
    @JoinColumn(name="id_persona")
    private Persona persona;
    
    private String estado;

}
