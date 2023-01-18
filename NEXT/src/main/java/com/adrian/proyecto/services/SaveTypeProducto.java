package com.adrian.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.proyecto.datos.productos.FullProducto;
import com.adrian.proyecto.datos.productos.Producto;
import com.adrian.proyecto.datos.productos.ProductoDAO;
import com.adrian.proyecto.datos.productos.portatiles.Portatil;
import com.adrian.proyecto.datos.productos.sobremesa.Sobremesa;


@Service
public class SaveTypeProducto {
	
	@Autowired
	ProductoDAO productoDAO;

	
	
	public void saveProducto(String tipo, FullProducto producto) {
		if(tipo.equals("portatil")) {
			Portatil portatil = new Portatil();
			
			portatil.setModelo(producto.getModelo());
			portatil.setPrecio(producto.getPrecio());
			portatil.setMarca(producto.getMarca());
			portatil.setImagen(producto.getImagen());
			portatil.setTipo(producto.getTipo());
			portatil.setGrafica(producto.getGrafica());
			portatil.setProcesador(producto.getProcesador());
			portatil.setAlmacenamiento(producto.getAlmacenamiento());
			portatil.setRam(producto.getRam());
			portatil.setSizePantalla(producto.getSizePantalla());
			portatil.setTipoPantalla(producto.getTipoPantalla());
			portatil.setDescripcion(getDescripcionProducto(tipo, producto));
			
			productoDAO.save(portatil);
		}
		else if (tipo.equals("sobremesa")) {
			Sobremesa sobremesa = new Sobremesa();
			
			sobremesa.setModelo(producto.getModelo());
			sobremesa.setPrecio(producto.getPrecio());
			sobremesa.setMarca(producto.getMarca());
			sobremesa.setImagen(producto.getImagen());
			sobremesa.setTipo(producto.getTipo());
			sobremesa.setGrafica(producto.getGraficaSobremesa());
			sobremesa.setProcesador(producto.getProcesadorSobremesa());
			sobremesa.setAlmacenamiento(producto.getAlmacenamientoSobremesa());
			sobremesa.setRam(producto.getRamSobremesa());
			sobremesa.setSistemaOperativo(producto.getSistemaOperativo());
			sobremesa.setDescripcion(getDescripcionProducto(tipo, producto));
			
			
			productoDAO.save(sobremesa);
		}
		else {
			Producto componente = new Producto();
			
			componente.setModelo(producto.getModelo());
			componente.setPrecio(producto.getPrecio());
			componente.setMarca(producto.getMarca());
			componente.setImagen(producto.getImagen());
			componente.setTipo(producto.getTipo());
			componente.setDescripcion(getDescripcionProducto(tipo, producto));
			
			productoDAO.save(componente);
		}
	}
	

	public FullProducto toFullProducto(Producto producto) {
		FullProducto fullProducto = new FullProducto();
		
		fullProducto.setModelo(producto.getModelo());
		fullProducto.setPrecio(producto.getPrecio());
		fullProducto.setMarca(producto.getMarca());
		fullProducto.setImagen(producto.getImagen());
		fullProducto.setTipo(producto.getTipo());
		
		if(producto.getTipo().equals("portatil")) {
			
			fullProducto.setGrafica(((Portatil) producto).getGrafica());
			fullProducto.setProcesador(((Portatil) producto).getProcesador());
			fullProducto.setAlmacenamiento(((Portatil) producto).getAlmacenamiento());
			fullProducto.setRam(((Portatil) producto).getRam());
			fullProducto.setSizePantalla(((Portatil) producto).getSizePantalla());
			fullProducto.setTipoPantalla(((Portatil) producto).getTipoPantalla());
		}
		else if (producto.getTipo().equals("sobremesa")) {			
			fullProducto.setGraficaSobremesa(((Sobremesa) producto).getGrafica());
			fullProducto.setProcesadorSobremesa(((Sobremesa) producto).getProcesador());
			fullProducto.setAlmacenamientoSobremesa(((Sobremesa) producto).getAlmacenamiento());
			fullProducto.setRamSobremesa(((Sobremesa) producto).getRam());
			fullProducto.setSistemaOperativo(((Sobremesa) producto).getSistemaOperativo());
		}
		
		return fullProducto;
	}
	
	
	
	
	public String getDescripcionProducto(String tipo, FullProducto producto) {
		String desc = "";
		
		desc += producto.getModelo()+"/"+producto.getMarca()+"/"+producto.getTipo()+"/";
		
		if(tipo.equals("portatil")) {
			desc += producto.getGrafica()+"/"+producto.getProcesador()+"/"+producto.getAlmacenamiento()+"/"+producto.getRam()+"/"+producto.getTipoPantalla();
		}
		else if(tipo.equals("sobremesa")) {
			desc += producto.getGraficaSobremesa()+"/"+producto.getProcesadorSobremesa()+"/"+producto.getAlmacenamientoSobremesa()+"/"+producto.getRamSobremesa()+"/"+producto.getSistemaOperativo();
		}
		
		return desc;
	}


	

}
