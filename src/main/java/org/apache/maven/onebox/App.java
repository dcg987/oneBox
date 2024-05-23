package org.apache.maven.onebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controlador", "ehcache", "servicio"})
public class App {
    public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
    }
    
}