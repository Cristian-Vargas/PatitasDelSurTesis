package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Sexo;

public interface SexoService {
    
     public List<Sexo> listarSexo();
    
    public void guardarSexo (Sexo sexo);
    
    public void eliminarSexo(Sexo sexo);
    
    public Sexo encontrarSexo(Sexo sexo);
}
