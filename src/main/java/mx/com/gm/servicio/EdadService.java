package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Edad;

public interface EdadService {
    
    public List<Edad> listarEdad();
    
    public void guardarEdad (Edad edad);
    
    public void eliminarEdad(Edad edad);
    
    public Edad encontrarEdad(Edad edad);
}
