package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name= "mascota") //Tabla en MYSQL
public class Mascota implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_mascota;
    
    @NotEmpty
    private String nombre;
    
    private String descripcion;
    
    @NotNull
    private String vacuna_1="No";
    
    @NotEmpty
    private String vacuna_2="No";
    
    @NotEmpty
    private String vacuna_3="No";
    
    @NotEmpty
    private String desparasitado="No";
    
    @NotEmpty
    private String castrado="No";
    
    @OneToOne
    @JoinColumn(name="id_especie")
    private Especie especie;
    
    @OneToOne
    @JoinColumn(name="id_estado")
    private Estado estado;
    
    @OneToOne
    @JoinColumn(name="id_edad")
    private Edad edad;
    
    @OneToOne
    @JoinColumn(name="id_sexo")
    private Sexo sexo;
    
    @OneToOne
    @JoinColumn(name="id_tamano")
    private Tamano tamano;
    
    
    private String imagen;
}
