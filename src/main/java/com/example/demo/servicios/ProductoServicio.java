package com.example.demo.servicios;

import java.util.List;


import com.example.demo.entidades.Producto;

public interface ProductoServicio {
	
	public Producto crearProducto(Producto producto);
	
	public void eliminarProducto (long idProducto);

	public Producto obtenerProducto(long idProducto);
	
	public Producto modificarProducto(Producto producto);

	public List<Producto> listarProductos(Integer maximo);
	
	public List<Producto> listarProductoPorNombre(String nombre);
	
	
 
}
