package com.example.service;

import java.util.List;

import com.example.model.Pelicula;
import com.example.model.Personaje;

public interface FilmServiseLocal {

	List<Pelicula> listaPelicula() throws Exception;
	
	List<Personaje> listaPersonaje(List<String> urlPersona) throws Exception;
	
}
