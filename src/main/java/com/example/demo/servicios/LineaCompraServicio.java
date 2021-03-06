package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.LineaCompra;
import com.example.demo.entidades.User;

public interface LineaCompraServicio{
	
	public LineaCompra crearLineaCompra(LineaCompra lineaCompra);
	public List<LineaCompra> buscarCompra(User usuario);
	public LineaCompra buscarLineaCompra(Long id);
	public void borraLineaCompra(Long id);
	public LineaCompra actualizar(LineaCompra lineacompra);
}
