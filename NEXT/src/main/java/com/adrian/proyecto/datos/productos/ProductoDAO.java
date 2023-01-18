package com.adrian.proyecto.datos.productos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoDAO extends CrudRepository<Producto, String>, PagingAndSortingRepository<Producto, String>{

	@Query(value="select p from Producto p order by rand()")
	List<Producto> getSomeProductos(Pageable pageable);
	
	
	@Query(value="select p from Producto p where p.tipo=?1")
	Page<Producto> getProductos(String tipo, PageRequest pageRequest);
	
	
	
	@Query(value="select p from Producto p where p.descripcion like %:busqueda%")
	List<Producto> getProductosBusqueda(@Param("busqueda") String busqueda);
}
