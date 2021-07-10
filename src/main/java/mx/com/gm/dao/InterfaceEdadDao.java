package mx.com.gm.dao;

import mx.com.gm.domain.Edad;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceEdadDao extends CrudRepository<Edad, Long>{
    
}
