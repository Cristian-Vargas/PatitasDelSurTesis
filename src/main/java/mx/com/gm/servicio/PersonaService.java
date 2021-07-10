
package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Persona;

public interface PersonaService {
    
    //La idea de esta interfaza es definir el nombre de los metodos para que
    //se mantengan estaticos y sin modificacion.
    //La codificacion, o lo que ocurre dentro de cada metodo se encuentra definido en
    //PersonaServiceImpl
    
    public List<Persona> listarPersonas();
    
    public void guardar (Persona persona);
    
    public void eliminar (Persona persona);
    
    public Persona encontrarPersona(Persona persona);
}
