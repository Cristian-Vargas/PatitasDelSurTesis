
package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.InterfacePersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    //Aqui se tomaran los metodos definidos en PersonaServ para configurar su contenido.
    //Se pueden crear nuevos metodos o llamar y reutilizar los ya existentes que Spring ofrece por defecto.
    //En este caso se llamo a los metodos de Spring definidos de manera automatica en la INTERFAZ InterfacePersonaDao
    
    @Autowired
    private InterfacePersonaDao personadao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personadao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personadao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personadao.delete(persona);
    }

    @Override
    @Transactional(readOnly=true)
    public Persona encontrarPersona(Persona persona) {
       return personadao.findById(persona.getId_persona()).orElse(null);
    }
    
}
