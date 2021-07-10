package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Tamano;

public interface TamanoService {
    
    public List<Tamano> listarTamano();
    
    public void guardarTamano(Tamano sexo);
    
    public void eliminarTamano(Tamano sexo);
    
    public Tamano encontrarTamano(Tamano sexo);    
}
