package mx.com.gm.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//-----IMPORTANTE
//Esta clase unicamente sirve para encriptar las passwords que vayamos a utilizar.
//-----


public class EncriptarPassword {
    
        
     //2 - Este medoto inicializa e imprime en el historial la password 123.
    public static void main(String[] args){

//        var password = "123";
//        System.out.println("password: " + password);
//        System.out.println("password encriptado: " + encriptarPassword(password));
    }
    
    
    // 1 - Este metodo se utiliza para encriptar una password
    public String encriptarPassword(String password){
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder.encode(password);
    
    }
    
}
