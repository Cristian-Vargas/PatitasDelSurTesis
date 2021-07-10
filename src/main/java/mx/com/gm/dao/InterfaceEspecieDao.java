package mx.com.gm.dao;

import mx.com.gm.domain.Especie;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceEspecieDao extends CrudRepository<Especie, Long>{
    //Aqui van las consultas personalizadas, sin embargo al hacer ctr+click en
    //crudrepository se pueden visualizar las consultas preestablecidas que pueden utilizarce
}
