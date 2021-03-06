package com.example.demo.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LineaCompraDao;
import com.example.demo.entidades.LineaCompra;
import com.example.demo.entidades.User;

@Transactional
@Service
public class LineaCompraServicioImp implements LineaCompraServicio{

	@Autowired
	private LineaCompraDao lineaCompraDao;
	
	@Override
	public LineaCompra crearLineaCompra(LineaCompra lineaCompra) {
		return lineaCompraDao.crear(lineaCompra);
	}

	@Override
	public List<LineaCompra> buscarCompra(User usuario) {
		return lineaCompraDao.buscarComprasPorIdCompra(usuario);
	}

	@Override
	public LineaCompra buscarLineaCompra(Long id) {
		return lineaCompraDao.buscar(id);
	}

	@Override
	public void borraLineaCompra(Long id) {
		lineaCompraDao.borrar(id);
		
	}

	@Override
	public LineaCompra actualizar(LineaCompra lineacompra) {
		return lineaCompraDao.actualizar(lineacompra);
	}

}
