package com.example.demo.dao;

import java.util.List;

import com.example.demo.entidades.Pregunta;
import com.example.demo.entidades.Producto;

public interface ProductoDao extends DaoGenerico<Producto>{
	public List<Producto> buscarPorNombre (String nombre);
	
	public Producto buscarPorCategoria(String categoria);
	
	public Producto anadirProducto(String nombre, String descripcion, String imagen);

	public List<Producto> listarProductos(Integer maximo);

	
	
}
