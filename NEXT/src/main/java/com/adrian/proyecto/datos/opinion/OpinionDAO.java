package com.adrian.proyecto.datos.opinion;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionDAO extends CrudRepository<Opinion, Long> {

}
