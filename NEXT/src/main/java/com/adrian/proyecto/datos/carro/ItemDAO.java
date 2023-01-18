package com.adrian.proyecto.datos.carro;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemDAO extends CrudRepository<Item, ItemId> {

	
	@Query(value="select * from item where username=?1", nativeQuery = true)
	List<Item> getUserItems(String username);
}
