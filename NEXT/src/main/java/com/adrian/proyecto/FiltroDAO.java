package com.adrian.proyecto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adrian.proyecto.datos.productos.Producto;


@Repository
public interface FiltroDAO<T extends Producto> extends CrudRepository<T, String>{

	@Query(value="select distinct ?1 from ?2 order by ?1 asc", nativeQuery = true)
	List<String> getFiltro(String filtro, String tabla);
	
}
