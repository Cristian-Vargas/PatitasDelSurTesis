package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Estado;

public interface EstadoService {
    
    public List<Estado> listarEstado();
    
    public void guardarEstado (Estado estado);
    
    public void eliminarEstado(Estado estado);
    
    public Estado encontrarEstado(Estado estado);
}
