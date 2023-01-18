package com.adrian.proyecto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.adrian.proyecto.datos.favoritos.ListaFavoritos;
import com.adrian.proyecto.datos.favoritos.ListaFavoritosDAO;
import com.adrian.proyecto.datos.productos.Producto;
import com.adrian.proyecto.datos.productos.ProductoDAO;
import com.adrian.proyecto.datos.productos.portatiles.PortatilDAO;

@Service
public class Paginacion {

	@Autowired
	ProductoDAO productoDAO;
	
	@Autowired
	PortatilDAO portatilDAO;
	
	
//	@Autowired
//	ProductoFavoritoDAO productoFavoritoDAO;
	
	
	@Autowired
	ListaFavoritosDAO listaFavoritosDAO;
	
	
	
	/**
	 * Metodo que devuelve una pagina y el tamaño de la misma  (productos por pagina)
	 * 
	 * @param pageNumber
	 * @return Pagina 
	 */
	public PageRequest productosPorPagina(int pageNumber) {		 
		  PageRequest pageRequest;
		  pageRequest = PageRequest.of(pageNumber-1, 12);
		  
	      return  pageRequest;
	}
	
	
	
	public Page<Producto> getProductos(PageRequest pageRequest, String tipo){ 
		Page<Producto> res = productoDAO.getProductos(tipo, pageRequest);
		return  res;
	}
	
	
	
	
//	public List<Producto> getFavoritos(PageRequest pageRequest, ListaFavoritos lista, Integer pagina){
//		List<Producto> favoritos = new ArrayList<Producto>();
//		List<Producto> productos = productoFavoritoDAO.getProductosFromLista(lista.getNombre());
//		int i = (pagina -1) * pageRequest.getPageSize();
//		
//		while((favoritos.size() < pageRequest.getPageSize()) || (productos.size() == i)) {	//cambiar segunda condicion
//			favoritos.add(productos.get(i));
//			i++;
//		}
//		
//		return favoritos;
//	}
	
	
	
	/**
	 * Construimos un listado de numeros enteros entre dos valores dados y luego pasamos a un List
	 * 
	 * @param paginas
	 * @return paginas totales
	 */
	public List<Integer> paginasTotales(int paginas) {
		List<Integer> paginasFinales = IntStream.rangeClosed(1, paginas).boxed().collect(Collectors.toList());	
		
		return paginasFinales;
	}
	
	
	
}
