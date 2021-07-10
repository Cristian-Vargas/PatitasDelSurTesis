
package mx.com.gm.dao;



import java.util.List;
import mx.com.gm.domain.Edad;
import mx.com.gm.domain.Especie;
import mx.com.gm.domain.Estado;
import mx.com.gm.domain.Mascota;
import mx.com.gm.domain.Sexo;
import mx.com.gm.domain.Tamano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InterfaceMascotaDao extends JpaRepository<Mascota, Long>{
     //Aqui van las consultas personalizadas, sin embargo al hacer ctr+click en
    //crudrepository se pueden visualizar las consultas preestablecidas que pueden utilizarce
    
    @Query("select m from Mascota m where m.estado.id_estado = 1")
    List<Mascota> findAllByEstado();
    
 
    List<Mascota> findByEspecie(Especie especie); //funciona
    
    //Mascotas en adopcion
    @Query("FROM Mascota m WHERE m.estado.id_estado = 1 and m.especie= :especie and m.edad = :edad and m.sexo = :sexo and m.tamano= :tamano")
    List<Mascota> findMascotasbyFiltros(@Param("especie") Especie especie, @Param("edad") Edad edad, @Param("sexo") Sexo sexo, @Param("tamano") Tamano tamano);
    
    //Mascotas en adopcion y adoptadas
    @Query("FROM Mascota m WHERE m.especie= :especie and m.edad = :edad and m.sexo = :sexo and m.tamano= :tamano")
    List<Mascota> findMascotasbyFiltrosAdmin(@Param("especie") Especie especie, @Param("edad") Edad edad, @Param("sexo") Sexo sexo, @Param("tamano") Tamano tamano);
    
    //Mascotas por nombre
    @Query("FROM Mascota m WHERE m.nombre like %:nombre%")
    List<Mascota> findMascotasbyNombre(@Param("nombre") String nombre);
    
    
    
}
