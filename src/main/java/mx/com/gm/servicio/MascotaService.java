package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Edad;
import mx.com.gm.domain.Especie;
import mx.com.gm.domain.Estado;
import mx.com.gm.domain.Mascota;
import mx.com.gm.domain.Sexo;
import mx.com.gm.domain.Tamano;

public interface MascotaService {
    
    public List<Mascota> listarMascota();
    
    public void guardarMascota (Mascota mascota);
    
    public void eliminarMascota (Mascota mascota);
    
    public Mascota encontrarMascota(Mascota mascota);
    
    public List<Mascota> listarMascotaPorEspecie(Especie especie);
    
    public List<Mascota> findMascotasbyFiltros(Especie especie, Edad edad, Sexo sexo, Tamano tamano);
    
    public List<Mascota> findMascotasbyFiltrosAdmin(Especie especie, Edad edad, Sexo sexo, Tamano tamano);
    
    List<Mascota> findMascotasbyNombre(String nombre);
    
    
}
