package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.ProductoDao;
import com.example.demo.entidades.Producto;

public class ProductoServicioImp implements ProductoServicio {
	
	@Autowired
	private ProductoDao productoDao;

	@Override
	public Producto crearProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarProducto(long idProducto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto obtenerProfesor(long idProfesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto modificarProducto(Producto profesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> listarProductos() {
		return productoDao.listarProductos();
	}

}
