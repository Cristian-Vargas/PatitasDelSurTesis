package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name= "persona") //Tabla en MYSQL
public class Persona implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_persona;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    private String telefono;
    
    @NotEmpty
    private String calle;
    
    @NotEmpty
    private String numero;
    
    @NotEmpty
    private String piso;
    
    @NotEmpty
    private String departamento;
    
    
    private String facebook;
    
    private String instagram;
    
    private String ocupacion;
    
    private String familia;
    
    private String espacio;
    
    private String responsabilidad;
    
    @OneToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    public String nombreApellido(){
        return nombre +", "+apellido;
    }
    
    public String direccionPersona(){
        return calle + " " + numero + " " + piso + " " + departamento;
    }
}
