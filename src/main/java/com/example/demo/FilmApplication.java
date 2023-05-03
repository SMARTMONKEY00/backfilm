package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Pelicula;
import com.example.model.Personaje;
import com.example.service.FilmServiceImpl;
import com.example.service.FilmServiseLocal;



@SpringBootApplication
@RestController
public class FilmApplication {
	
	
	private FilmServiseLocal filmServiseImpl = new FilmServiceImpl();

	public static void main(String[] args) {
		SpringApplication.run(FilmApplication.class, args);
	}
	
	@GetMapping("/api/listaPelicula")
    public List<Pelicula> listaPelicula() throws Exception    {						 
      return filmServiseImpl.listaPelicula();
    }
	
	@PostMapping("/api/listaPersonaje")
    public List<Personaje> listaPersonaje(@RequestBody List<String> urlPersona) throws Exception    {						 
      return filmServiseImpl.listaPersonaje(urlPersona);
    }

}
