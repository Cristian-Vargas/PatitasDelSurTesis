package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Especie;

public interface EspecieService {
    
    public List<Especie> listarEspecie();
    
    public void guardarEspecie (Especie especie);
    
    public void eliminarEspecie(Especie especie);
    
    public Especie encontrarEspecieID(Especie especie);
    
    
}
