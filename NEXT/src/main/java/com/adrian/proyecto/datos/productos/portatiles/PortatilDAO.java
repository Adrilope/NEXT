package com.adrian.proyecto.datos.productos.portatiles;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PortatilDAO extends CrudRepository<Portatil, String>, PagingAndSortingRepository<Portatil, String> {
	
	@Query(value="select * from producto inner join portatil USING(modelo)", nativeQuery = true)
	Page<Portatil> getPortatiles(PageRequest pageRequest);

	
}
