package com.adrian.proyecto.datos.favoritos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ListaFavoritosDAO extends CrudRepository<ListaFavoritos, Long>{

	@Query(value="select * from favoritos where user_username=?1", nativeQuery = true)
	List<ListaFavoritos> getListasFavoritos(String username);
	
}
