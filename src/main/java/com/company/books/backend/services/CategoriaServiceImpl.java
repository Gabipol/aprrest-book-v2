package com.company.books.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;

@Service //Se encarga de acceder a los datos de la tabla categoria
public class CategoriaServiceImpl implements ICategoriaService {

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly=true) // Es un metodo transaccional que va a la BBDD
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		
		log.info("Inicio método buscarCategoría");
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			response.getCategoriaResponse().setCategoria(categoria);
			response.setmetadata("Respuesta OK", "0", "2024-02-26");
		} catch (Exception e) {
			response.setmetadata("Respuesta incorrecta al consultar categorias", "-1", "2024-02-26");
			log.error("Error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true) // Es un metodo transaccional que va a la BBDD
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
		
		log.info("Inicio método buscarPorId");
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			// Optional lo pide el metodo FindById
			Optional<Categoria> categoria = categoriaDao.findById(id);
			
			if (categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("Error al consultar categorias");
				response.setmetadata("Respuesta NOK", "-1", "2024-02-27");
				return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error al consultar categorias: ", e.getMessage());
			response.setmetadata("Respuesta NOK", "-1", "2024-02-27");
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setmetadata("Respuesta OK", "0", "2024-02-26");
		return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
		
		log.info("Inicio método crear categoria");
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Categoria categoriaGuardada = categoriaDao.save(categoria);
			
			if (categoriaGuardada != null) {
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("Error al crear categoria");
				response.setmetadata("Respuesta NOK", "-1", "2024-02-27");
				return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			log.error("Error al crear categoria: ", e.getMessage());
			response.setmetadata("Respuesta NOK", "-1", "2024-02-27");
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setmetadata("Respuesta OK", "0", "2024-02-26");
		return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {

		log.info("Inicio método actualizar categoria");
		CategoriaResponseRest response = new CategoriaResponseRest();

		List<Categoria> list = new ArrayList<>();

		try {
		
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			
			if (categoriaBuscada.isPresent()) {
				
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get());
				
				if(categoriaActualizar != null) {
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
				} else {
					log.error("Error al guardar categoria");
					response.setmetadata("Respuesta NOK", "-1", "2024-02-27");
					return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);
				}
			}  else {
				log.error("Error al guardar categoria");
				response.setmetadata("Respuesta NOK", "-1", "2024-02-27");
				return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			
			log.error("Error al guardar categoria");
			response.setmetadata("Respuesta NOK", "-1", "2024-02-27");
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.setmetadata("Respuesta OK", "0", "2024-02-26");
		return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
		
		log.info("Inicio método eliminar categoria");
		
		CategoriaResponseRest response = new CategoriaResponseRest();

		try {
		
			//eliminar el registro
			categoriaDao.deleteById(id);
			response.setmetadata("Respuesta OK", "0", "2024-02-27");
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			
			log.error("Error al eliminar categoria");
			response.setmetadata("Respuesta NOK", "-1", "2024-02-27");
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
}
