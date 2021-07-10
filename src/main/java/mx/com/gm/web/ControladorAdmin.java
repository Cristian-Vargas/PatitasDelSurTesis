package mx.com.gm.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.InterfacePostulacionDao;
import mx.com.gm.domain.Edad;
import mx.com.gm.domain.Especie;
import mx.com.gm.domain.Estado;
import mx.com.gm.domain.Mascota;
import mx.com.gm.domain.Postulacion;
import mx.com.gm.domain.Sexo;
import mx.com.gm.domain.Tamano;
import mx.com.gm.servicio.EdadService;
import mx.com.gm.servicio.EspecieService;

import mx.com.gm.servicio.EstadoService;
import mx.com.gm.servicio.MascotaService;
import mx.com.gm.servicio.PostulacionService;
import mx.com.gm.servicio.SexoService;
import mx.com.gm.servicio.TamanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller //Spring reconoce la clase para administrarla 
@Slf4j //Tener acceso a la variable log
public class ControladorAdmin {
    
    @Autowired
    private MascotaService mascotaService;
    
    @Autowired
    private EspecieService especieService;
    
    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private EdadService edadService;
    
    @Autowired
    private SexoService sexoService;
    
    @Autowired
    private TamanoService tamanoService;
    
    @Autowired
    private PostulacionService postulacionService;
    
    @Autowired
    private InterfacePostulacionDao interfacePostulacionDao;
    
    
    
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
    
    
    //ADMINISTRAR - Listado de Mascotas
    @GetMapping("/adminListaMascotas")
    public String adminListaMascotas(Model model,Mascota mascota)
    {
        cargarElementos(model);
        
    var listamascota = mascotaService.listarMascota();
    model.addAttribute("mascotas", listamascota);
    return "adminListaMascotas";
    }
    
    @GetMapping("/adminListaMascotasFiltradas")
    public String adminListaMascotasFiltradas(@RequestParam(value="nombre", required = false) String nombre,@RequestParam(value="especie", required = false) Especie especie,@RequestParam(value="edad", required = false) Edad edad,
            @RequestParam(value="sexo", required = false) Sexo sexo,@RequestParam(value="tamano", required = false) Tamano tamano,
            Model model, @ModelAttribute("mascota") Mascota mascota)          
    {   
        //Cargar Combos
        cargarElementos(model);
        
        //listado de mascotas 
    var listaMascotas = mascotaService.findMascotasbyFiltrosAdmin(especie,edad, sexo, tamano);
    model.addAttribute("mascotas", listaMascotas);
    


    return "adminListaMascotas";
    
    }
    
    @GetMapping("/adminListaMascotasFiltradasNombre")
    public String adminListaMascotasFiltradasNombre(@RequestParam(value="nombre", required = false) String nombre,@RequestParam(value="especie", required = false) Especie especie,@RequestParam(value="edad", required = false) Edad edad,
            @RequestParam(value="sexo", required = false) Sexo sexo,@RequestParam(value="tamano", required = false) Tamano tamano,
            Model model, @ModelAttribute("mascota") Mascota mascota)          
    {   
        //Cargar Combos
        cargarElementos(model);
        

    var listaMascotasNombre = mascotaService.findMascotasbyNombre(nombre);
    model.addAttribute("mascotas", listaMascotasNombre);  

    return "adminListaMascotas";
    
    }
    
    
    //ADMINISTRAR - Formulario de Mascota
    @GetMapping("/formularioMascota")
    public String formularioMascota(Mascota mascota, Model model)
    {
        //carga de combos
        cargarElementos(model);
        return "formularioMascota";
    }
    
    
    //ADMINISTRAR - Guardar Mascota
    @PostMapping("/guardarMascota") //Accion del boton guardar
    public String guardarMascota(@Valid Mascota mascota, Errors errores, Model model, @RequestParam("file") MultipartFile imagen,RedirectAttributes redirectAttrs)
    {   
        if(errores.hasErrors()){
        //Si ha errores vuelve a cargar el formulario y vuelve a cargar los combos
        //carga de combos
        cargarElementos(model);    
        return "formularioMascota";
        }
        
        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta =directorioImagenes.toFile().getAbsolutePath();
        
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
            
            mascota.setImagen(imagen.getOriginalFilename());
        } catch (IOException ex) {
            Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mascotaService.guardarMascota(mascota);
        redirectAttrs
            .addFlashAttribute("mensaje", "Guardado correctamente.")
            .addFlashAttribute("clase", "success");
        
        return "redirect:formularioMascota";
    }
    
    
    //---------- Editar mascota
    @GetMapping("/editarMascota/{id_mascota}")
    public String editar(Mascota mascota, Model model){
        
        //carga de combos
        cargarElementos(model);
        
        mascota = mascotaService.encontrarMascota(mascota);
        model.addAttribute("mascota",mascota);
        return "formularioMascota";
    }
    
    @GetMapping("/eliminarMascota")
    public String eliminar(Mascota mascota, Model model,RedirectAttributes redirectAttrs){
    
        mascotaService.eliminarMascota(mascota);
        
        redirectAttrs
            .addFlashAttribute("mensaje", "La mascota fue removida correctamente.")
            .addFlashAttribute("clase", "success");
        
        
        return "redirect:adminListaMascotas";
    
    }
    
    //--------------------------------------------------------------
    //Listado de Postulaciones
    
    
    @GetMapping("/adminListaPostulaciones")
    public String adminListaPostulaciones(Model model)
    {
        var listaPostulaciones = interfacePostulacionDao.findAllByPostulacionSinEstado();
        
        model.addAttribute("listaPostulaciones",listaPostulaciones);
    return "adminListaPostulaciones";
    }
    
    
    //---------- postulacion aceptada
    @GetMapping("/postulacionAceptada")
    public String postulacionAceptada(Postulacion postulacion, Model model,RedirectAttributes redirectAttrs){

        
        //Encuentra la postulacion
        postulacion = postulacionService.encontrarPostulacion(postulacion);
        //Envia la informacion de adoptado
        postulacion.setEstado("Adoptado");
        
        //Encuentra una mascota
        Mascota mascota = mascotaService.encontrarMascota(postulacion.getMascota());
        
        //Crea un estado de 'Adoptado'
        Estado estado = new Estado();
        estado.setId_estado(2l);
        mascota.setEstado(estado);
        
        //Al existir una mascota lo actualiza cambiando 'En adopcion' por 'Adoptado'
        mascotaService.guardarMascota(mascota);
        //Guarda la postulacion
        postulacionService.guardarPostulacion(postulacion);
        
        redirectAttrs
            .addFlashAttribute("mensaje", "La acción se ha completado correctamente.")
            .addFlashAttribute("clase", "success");
        
        return "redirect:adminListaPostulaciones";
    }
    
    //---------- postulacion rechazada
    
    @GetMapping("/postulacionRechazada")
    public String postulacionRechazada(Postulacion postulacion, Model model,RedirectAttributes redirectAttrs){

        postulacion = postulacionService.encontrarPostulacion(postulacion);
        postulacion.setEstado("Rechazado");
        
        postulacionService.guardarPostulacion(postulacion);
        
        redirectAttrs
            .addFlashAttribute("mensaje", "La acción se ha completado correctamente.")
            .addFlashAttribute("clase", "success");
        
       
        
        return "redirect:adminListaPostulaciones";
    }
    
    //--------------------------------------------------------------
    //Listado de adoptados
    
    @GetMapping("/adminListaAdoptados")
    public String adminListaAdoptados(Model model, Postulacion postulacion )
    {
        
        List<Postulacion> listaAdoptados = interfacePostulacionDao.findAllByAdoptados();
        model.addAttribute("listaAdoptados",listaAdoptados);
        return "adminListaAdoptados";
    }
    
    @GetMapping("/adminListaAdoptadosFiltro")
    public String adminListaAdoptadosFiltro(@RequestParam(value="mascota.nombre", required = false) String nombreMascota,
            @RequestParam(value="persona.nombre", required = false) String nombrePersona, Model model, @ModelAttribute("postulacion") Postulacion postulacion)          
    {   

    var listaAdoptadosPorMascota = interfacePostulacionDao.findPostulacionbyMascota(nombreMascota);
        model.addAttribute("listaAdoptados",listaAdoptadosPorMascota);

        return "adminListaAdoptados";
    
    }
    
    @GetMapping("/adminListaAdoptadosByPersona")
    public String adminListaAdoptadosByPersona(@RequestParam(value="persona.nombre", required = false) String nombrePersona, Model model, @ModelAttribute("postulacion") Postulacion postulacion)          
    {   

        var listaAdoptadosPorPersona = interfacePostulacionDao.findPostulacionbyPersona(nombrePersona);
        model.addAttribute("listaAdoptados",listaAdoptadosPorPersona);

        return "adminListaAdoptados";
    
    }
    
    
   
    
    
    
    
    //GRAFICOS
    @GetMapping("/graficos")
    public String graficos(Model model)
    {
        var listaPostulaciones = postulacionService.listarPostulacion();
      
    //GRAFICO 1: De las postulaciones cuantos son gatos y cuantos son perros
        int perro=0;
        int gato=0;
        int total=0;
        
        
        for( Postulacion p : listaPostulaciones)
        {
            if(p.getMascota().getEspecie().getId_especie() == 1)
            {
                perro = perro + 1;
                total= total +1;
            }
            else
            {
                gato = gato + 1;
                total= total +1;
            }
        }
        
        
        model.addAttribute("perro",perro);
        model.addAttribute("gato",gato);
        model.addAttribute("total",total);
        
        //GRAFICO 2: De las postulaciones. Mostrar cantidades por tamaño
        int pequeno = 0;
        int mediano = 0;
        int grande = 0;
        
        for( Postulacion p : listaPostulaciones)
        {
            if(p.getMascota().getTamano().getId_tamano() == 1)
            {
                pequeno= pequeno + 1;
            }
            
            if(p.getMascota().getTamano().getId_tamano() == 2)
            {
                mediano= mediano + 1;
            }
            
            if(p.getMascota().getTamano().getId_tamano() == 3)
            {
                grande= grande + 1;
            }
            
        }
        
        model.addAttribute("pequeno",pequeno);
        model.addAttribute("mediano",mediano);
        model.addAttribute("grande",grande);
        
        
        //GRAFICO 3: De las postulaciones. mostrar cantidades por edad
        
        int cachorro = 0;
        int joven = 0;
        int adulto = 0;
        
        for( Postulacion p : listaPostulaciones)
        {
            if(p.getMascota().getEdad().getId_edad() == 1)
            {
                cachorro= cachorro + 1;
            }
            
            if(p.getMascota().getEdad().getId_edad() == 2)
            {
                joven= joven + 1;
            }
            
           if(p.getMascota().getEdad().getId_edad() == 3)
            {
                adulto= adulto + 1;
            }
            
        }
        
        model.addAttribute("cachorro",cachorro);
        model.addAttribute("joven",joven);
        model.addAttribute("adulto",adulto);
        
        
        
        return "graficos";
    }
    
}
