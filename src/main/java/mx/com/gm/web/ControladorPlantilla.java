package mx.com.gm.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.InterfaceMascotaDao;
import mx.com.gm.dao.InterfacePersonaDao;
import mx.com.gm.dao.RolDao;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Edad;
import mx.com.gm.domain.Especie;
import mx.com.gm.domain.Estado;
import mx.com.gm.domain.Mascota;
import mx.com.gm.domain.Persona;
import mx.com.gm.domain.Postulacion;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Sexo;
import mx.com.gm.domain.Tamano;
import mx.com.gm.domain.Usuario;
import mx.com.gm.servicio.EdadService;
import mx.com.gm.servicio.EspecieService;
import mx.com.gm.servicio.EstadoService;
import mx.com.gm.servicio.MascotaService;
import mx.com.gm.servicio.PersonaService;
import mx.com.gm.servicio.PostulacionService;
import mx.com.gm.servicio.RolService;
import mx.com.gm.servicio.SexoService;
import mx.com.gm.servicio.TamanoService;
import mx.com.gm.servicio.UsuarioService;
import mx.com.gm.util.EncriptarPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller //Spring reconoce la clase para administrarla 
@Slf4j //Tener acceso a la variable log
public class ControladorPlantilla {
    
    @Autowired
    private MascotaService mascotaService;
    
    @Autowired
    private EspecieService especieService;
    
    @Autowired
    private PersonaService personaService;    
    
    @Autowired
    private PostulacionService postulacionService; 
    
    @Autowired
    private EstadoService estadoService; 
    
    @Autowired
    private EdadService edadService; 
    
    @Autowired
    private SexoService sexoService; 
    
    @Autowired
    private TamanoService tamanoService; 
    
    @Autowired
    private UsuarioDao usuariodao;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;
    
    @Autowired
    private InterfaceMascotaDao mascotaDao;
    
    @Autowired
    private InterfacePersonaDao personadao;
    
    
    //Metodo para cargar ComboBox
    public void cargarElementos(Model model)
    {
        //COMBOBOX especie
        var especies = especieService.listarEspecie();
        model.addAttribute("especies",especies);
            
        //COMBOBOX estado
        var estados = estadoService.listarEstado();
        model.addAttribute("estados",estados);
        
        //COMBOBOX edad
        var edades = edadService.listarEdad();
        model.addAttribute("edades",edades);
        
        //COMBOBOX sexo
        var sexos = sexoService.listarSexo();
        model.addAttribute("sexos",sexos);
        
        //COMBOBOX tamano
        var tamanos = tamanoService.listarTamano();
        model.addAttribute("tamanos",tamanos);
    }
    
    //DIRECCIONAR AL URL
    
    //----------------pestaña Administrar
    @GetMapping("/administrar")
    public String administrar()
    {
    return "administrar";
    }
    
    //----------------pestaña Login
    @GetMapping("/login")
    public String login(@AuthenticationPrincipal User user, Usuario usuario)
    {
        return "login";
    }
    

    
    //----------------pestaña Registrar
    @GetMapping("/registrar")
    public String Registrar(Usuario usuario)
    {
        
        return "registrar";
    }
    
    @PostMapping("/nuevoUsuario")
    public String nuevoUsuario(Usuario usuario,RedirectAttributes redirectAttrs)
    {
        EncriptarPassword ep = new EncriptarPassword();
        
        String pas = ep.encriptarPassword(usuario.getPassword());
        usuario.setPassword(pas);
        usuariodao.save(usuario);
        
        rolService.guardarRol(usuario.getIdUsuario());
        
         redirectAttrs
            .addFlashAttribute("mensaje", "¡Bienvenido! Inicie Sesión para continuar.")
            .addFlashAttribute("clase", "success");
        
        
        return "redirect:login";
    }
    
    //-------------------pestaña Mascotas + lista 
    @GetMapping("/mascotas")
    public String mascotas(Model model, Mascota mascota)
    {
        //Cargar Combos
        cargarElementos(model);
        
        //listado de mascotas
    var listaMascotas = mascotaDao.findAllByEstado();
    model.addAttribute("listaMascotas", listaMascotas);

    return "mascotas";
    }
    
    @GetMapping("/mascotasFiltradas")
    public String mascotasFiltradas(@RequestParam(value="especie", required = false) Especie especie,@RequestParam(value="edad", required = false) Edad edad,
            @RequestParam(value="sexo", required = false) Sexo sexo,@RequestParam(value="tamano", required = false) Tamano tamano,
            Model model, @ModelAttribute("mascota") Mascota mascota)      
    {   
        //Cargar Combos
        cargarElementos(model);
        
        //listado de mascotas perros
    var listaMascotas = mascotaService.findMascotasbyFiltros(especie,edad, sexo, tamano);
    model.addAttribute("listaMascotas", listaMascotas);

    return "mascotas";
    }
    
       
    
    @GetMapping("/postularse")
    public String postularse(Mascota mascota, Persona persona, Postulacion postulacion, Principal principal, Model model,RedirectAttributes redirectAttrs)
    {
     

    //persona
    User usuarioActivo = (User) ((Authentication) principal).getPrincipal();
     Usuario usuario = usuariodao.findByUsername(usuarioActivo.getUsername());     
     model.addAttribute("usuario",usuario);
     //query que busca un formulario por el usuario
     persona = personadao.findByUsuario(usuario);

     
     if(persona == null)
     {
         redirectAttrs
            .addFlashAttribute("texto", "Complete el formulario para continuar.")
            .addFlashAttribute("completar", "success");
     return "redirect:formularioPersona";
     
     }
     else
     {   //insertar postularion
        
      postulacion.setMascota(mascota);
      postulacion.setPersona(persona);
     postulacionService.guardarPostulacion(postulacion);
     redirectAttrs
            .addFlashAttribute("mensaje", "¡Su postulación ha sido exitosa! Dentro de las 48hs nos comunicaremos con usted.")
            .addFlashAttribute("clase", "success");

    return "redirect:mascotas";
    }
    }

    
    //-------------Pestaña MI PERFIL 
    @GetMapping("/formularioPersona")
    public String formularioPersona(Persona persona, Principal principal, Model model)
    {
     //devuelve el usuario logeado
     User usuarioActivo = (User) ((Authentication) principal).getPrincipal();
     Usuario usuario = usuariodao.findByUsername(usuarioActivo.getUsername());     
     model.addAttribute("usuario",usuario);
     //query que busca un formulario por el usuario
     persona = personadao.findByUsuario(usuario);
     //Si el formulario existe lo trae para modificar
     if( persona != null)
     {
     model.addAttribute("persona",persona);
     }
     //Si el formulario NO existe lo trae vacio para completar
    return "formularioPersona";
    }
    
    //-----Guardar persona
    @PostMapping("/guardar") //Accion del boton guardar
    public String guardar(@Valid Persona persona, Errors errores, Principal principal, Model model,RedirectAttributes redirectAttrs){
    
        if(errores.hasErrors()){
             //devuelve el usuario logeado
     User usuarioActivo = (User) ((Authentication) principal).getPrincipal();
     Usuario usuario = usuariodao.findByUsername(usuarioActivo.getUsername());     
     model.addAttribute("usuario",usuario);
        return "formularioPersona";
        }
        personaService.guardar(persona);
        redirectAttrs
            .addFlashAttribute("mensaje", "Guardado correctamente.")
            .addFlashAttribute("clase", "success");
        
        return "redirect:formularioPersona";

    }
    
    
    //----------------pestaña Sobre nosotros
    @GetMapping("/sobreNosotros")
    public String sobreNosotros()
    {
        return "sobreNosotros";
    }
    
    //----------------pestaña terminos y condiciones 
    @GetMapping("/terminosCondiciones")
    public String terminosCondiciones()
    {
        return "terminosCondiciones";
    }
    
     //----------------pestaña preguntas frecuentes
    @GetMapping("/preguntasFrecuentes")
    public String preguntasFrecuentes()
    {
        return "preguntasFrecuentes";
    }
    
}
