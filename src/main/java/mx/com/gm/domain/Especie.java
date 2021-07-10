package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
@Table(name= "especie") //Tabla en MYSQL
public class Especie implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_especie;
    
    @NotEmpty
    private String especie;

    
}