package com.company.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.services.CategoriaServiceImpl;

public class CategoriaServiceImplTest {

	@InjectMocks
	CategoriaServiceImpl service;
	
	@Mock
	ICategoriaDao categoriaDao;
	
	List<Categoria> listaCategorias = new ArrayList<>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.cargarCategorias();
	}
	
	@Test
	public void buscarCategoriasTest() {
		
		when(categoriaDao.findAll()).thenReturn(listaCategorias);
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).findAll();
	}
	
	public void cargarCategorias() {
		
		Categoria catUno = new Categoria(Long.valueOf(1), "Abarrotes", "Descripcion de abarrotes");
		Categoria catDos = new Categoria(Long.valueOf(2), "Lacteos", "Descripcion de lacteos");
		Categoria catTres = new Categoria(Long.valueOf(3), "Bebidas", "Descripcion de bebidas");
		Categoria catCuatro = new Categoria(Long.valueOf(4), "Carnes blancas", "Descripcion de carnes blancas");
		
		listaCategorias.add(catUno);
		listaCategorias.add(catDos);
		listaCategorias.add(catTres);
		listaCategorias.add(catCuatro);
		
		
	}
}
