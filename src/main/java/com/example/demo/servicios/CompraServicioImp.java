package com.example.demo.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CompraDao;
import com.example.demo.entidades.Compra;
import com.example.demo.entidades.Producto;
import com.example.demo.entidades.User;

@Transactional
@Service
public class CompraServicioImp implements CompraServicio {
	
	@Autowired
	private CompraDao compraDao;

	@Override
	public Compra crearCompra(Compra compra) {
		return compraDao.crear(compra);
	}

}