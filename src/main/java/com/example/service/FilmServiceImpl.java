package com.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.model.Pelicula;
import com.example.model.Personaje;

@Service
public class FilmServiceImpl implements FilmServiseLocal {

	@Override
	public List<Pelicula> listaPelicula() throws JSONException, IOException {
		// TODO Auto-generated method stub
		List<Pelicula> resultado = new ArrayList<>();
		List<String> listaPer =  new ArrayList<>();
		String url = "https://swapi.dev/api/films/?format=json";		
		JSONObject json = readJsonFromUrl(url);		
		JSONArray arregloJson = new JSONArray(json.getJSONArray("results").toString());	
		for (int indice = 0; indice < arregloJson.length(); indice++) {			
			Pelicula peli = new Pelicula();			
			JSONObject objPeli = arregloJson.getJSONObject(indice);	
			peli.setEpisode_id(objPeli.getLong("episode_id"));;
			peli.setTitle(objPeli.getString("title"));;
			peli.setOpening_crawl(objPeli.getString("opening_crawl"));;
			peli.setDirector(objPeli.getString("director"));
			peli.setProducer(objPeli.getString("producer"));;
			peli.setRelease_date(objPeli.getString("release_date"));			
			peli.setCharacters(listaUrlPersona(new JSONArray(objPeli.getJSONArray("characters").toString())));			
			resultado.add(peli);
		}
		
		return resultado;
	}
	
	private List<String> listaUrlPersona(JSONArray listaPersonaje) throws JSONException, IOException{
		List<String> resultado = new ArrayList<>();		
		for (int indice = 0; indice < listaPersonaje.length(); indice++) {	
			resultado.add(listaPersonaje.getString(indice));
		}
		return resultado;
	}
	
	
	@Override
	public List<Personaje> listaPersonaje(List<String> urlPersona) throws JSONException, IOException {
		// TODO Auto-generated method stub
		List<Personaje> resultado = new ArrayList<>();		
		for (String obj : urlPersona) {
			resultado.add(obtenerPersonaje(obj));
		}
		return resultado;
	}
	
	private Personaje obtenerPersonaje(String urlTemp) throws JSONException, IOException{	
		Personaje objPersonaje =  new Personaje();
		String url = urlTemp + "?format=json";		
		JSONObject json = readJsonFromUrl(url);		
		objPersonaje.setName(json.getString("name"));		
		return objPersonaje;
	}
	
	private JSONObject readJsonFromUrl(String link) throws IOException, JSONException {
	    InputStream input = new URL(link).openStream();	   
	    try {                                
	      BufferedReader re = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));  
	    // Buffer Reading In UTF-8  
	      String Text = Read(re);        
	      JSONObject json = new JSONObject(Text);
	      return json;    // Returning JSON
	    } catch (Exception e) {
	      return null;
	    } finally {
	      input.close();
	    }
	}
	
	private String Read(Reader re) throws IOException {     
	    StringBuilder str = new StringBuilder();    
	    int temp;
	    do {
	      //reading Charcter By Chracter.
	      temp = re.read();     
	      str.append((char) temp);

	    } while (temp != -1);        
	   return str.toString();
	}
	

}
