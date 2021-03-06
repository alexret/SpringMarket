package com.example.demo.dao;

import java.util.List;

import com.example.demo.entidades.Pregunta;
import com.example.demo.entidades.Producto;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component("ProductoDao")
public class ProductoDaoImp extends DaoGenericoImp<Producto> implements ProductoDao {

	@Override
	public List<Producto> buscarPorNombre(String nombre) {
		Query query = this.em.createQuery("FROM Producto u where u.nombre LIKE CONCAT('%', :nombre ,'%')");
		query.setParameter("nombre", nombre);
		List<Producto> lProducto = query.getResultList();

		if (lProducto != null) {
			return lProducto;
		}
		return null;
	}

	@Override
	public Producto buscarPorCategoria(String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto anadirProducto(String nombre, String descripcion, String imagen) {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override

	public List<Producto> listarProductos(Integer maximo) {
		Query query =  this.em.createQuery("FROM Producto");
		query.setFirstResult(0);
		query.setMaxResults(maximo);
		List<Producto> lProducto =  query.getResultList();

		if (lProducto != null) {
			return lProducto;
		}
		return null;
	}

	
	

}
