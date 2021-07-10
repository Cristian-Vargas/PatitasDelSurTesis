package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //Spring reconoce la clase para administrarla 
@Slf4j //Tener acceso a la variable log

public class ControladorInicio {   
    
    @Autowired
    private PersonaService personaServ;  //Va a buscar los metodos definidos en la INTERFAZ PersonaService
    
    //-------INDEX
    //------- listar persona
    @GetMapping("/") 
    public String inicio(Model model){
       
       
        return "index"; 
    }
    

}
