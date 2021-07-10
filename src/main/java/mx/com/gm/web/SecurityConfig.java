package mx.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    //Metodo para loguear utilizando los usuarios y roles de la BD
    
    //Nombre real definido en UsuarioService
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    //LLama a la misma clase que se utilizo para crear la encriptacion de passwords CLASE: EncriptarPassword
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    //Metodo para utilizar la IMPLEMENTACION de UserDetailsService(UsuarioService) y el metodo anterior passwordEncoder()
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{ //Este objeto se encuenta definido en la fabrica de spring para trabajar automaticamente
    
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    
    }
    
    //Metodo para RESTRINGIR las URL segun los roles
    @Override
    protected void configure(HttpSecurity http) throws Exception{
    
        //Esto sirve para restringir las URL deseeadas
    http.authorizeRequests()
            .antMatchers("/administrar/**")
                .hasRole("ADMIN")
            .antMatchers("/formularioPersona/**")
                .hasAnyRole("USER","ADMIN")
            
            //Esto sirve para determinar la pagina html en donde se va a realizar el login
            .and()
                .formLogin()
                .loginPage("/login") 
            
            //Determinar cual va a ser la pagina que muestre el error
            .and()
                .exceptionHandling().accessDeniedPage("/errores/403")
            ;
    
    }
    
    
    
}
