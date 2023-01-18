package com.adrian.proyecto.datos.productos.sobremesa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface SobremesaDAO extends CrudRepository<Sobremesa, String>, PagingAndSortingRepository<Sobremesa, String> {

	
}
