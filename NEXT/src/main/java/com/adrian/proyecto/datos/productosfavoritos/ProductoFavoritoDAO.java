package com.adrian.proyecto.datos.productosfavoritos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ProductoFavoritoDAO extends CrudRepository<ProductoFavorito, ProductoFavoritoId> {

	@Query(value="select * from producto_favorito where id=?1", nativeQuery = true)
	List<ProductoFavorito> getFavoritos(Long id);
	
}
