package mx.com.gm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    
    //Este metodo sirve para mapear con la restriccion de las URL de SecurityConfig
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        
        //mapeo con el index html
        registro.addViewController("/").setViewName("index");
        
        //mapeo con el login html
        registro.addViewController("/login");
        
        //mapeo error 403
        registro.addViewController("/errores/403").setViewName("/errores/403");
        
    }
    
}
