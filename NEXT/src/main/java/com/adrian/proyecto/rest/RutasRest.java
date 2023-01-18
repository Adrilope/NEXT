package com.adrian.proyecto.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.proyecto.datos.favoritos.ListaFavoritosDAO;
import com.adrian.proyecto.datos.productos.ProductoDAO;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavorito;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavoritoDAO;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavoritoId;


@RestController
public class RutasRest {
	
	@Autowired
	ListaFavoritosDAO listaFavoritosDAO;
	
	@Autowired 
	ProductoFavoritoDAO productoFavoritoDAO;
	
	@Autowired
	ProductoDAO productoDAO;
	
	
	
	
	@PostMapping("/favoritos/addProducto")
	public void addProducto(@RequestBody ProductoFavoritoId id) {	
		ProductoFavorito pf = new ProductoFavorito();
		pf.setIdentificador(id);
		pf.setProducto(productoDAO.findById(id.getProductoKey()).get());
		pf.setListaFavoritos(listaFavoritosDAO.findById(id.getListaFavoritosKey()).get());
		
		productoFavoritoDAO.save(pf);
	}
	

}
