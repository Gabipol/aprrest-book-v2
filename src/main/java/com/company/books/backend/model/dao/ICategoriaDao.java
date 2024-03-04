package com.company.books.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.Categoria;

/*
 * CrudRepository tiene metodos basicos de CRUD de Spring Data/JPA
 */

public interface ICategoriaDao extends CrudRepository<Categoria, Long>{

}
