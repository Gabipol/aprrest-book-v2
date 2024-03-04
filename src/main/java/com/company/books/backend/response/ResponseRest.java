package com.company.books.backend.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
	
	//Agregar metada a nuestra respuesta. Como respondio el servicio
	
	private ArrayList<HashMap<String , String>> metadata 
		= new ArrayList<>();
	
	public ArrayList<HashMap<String, String>> getMetadata(){
		return metadata;		
	}
	
	public void setmetadata(String tipo, String codigo, String date) {
		HashMap<String, String> mapa = new HashMap<>();
	
	mapa.put("tipo", tipo);
	mapa.put("codigo", codigo);
	mapa.put("date", date);
	
	metadata.add(mapa);
	}
}
