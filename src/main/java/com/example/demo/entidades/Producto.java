package com.example.demo.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
	
	private static final long serialVersionUID = -8668594760203621162L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "idproductos")
	private Long idProducto;
	
	@Column(name = "nombreproducto")
	private String nombre;
	
	@Column(name = "descripcionproducto")
	private String descripcion;
	
	@Column(name = "precio")
	private double precio;
	
	@Column(name = "descuento")
	private int descuento;
	
	//Relación OneToMany Compra
	@OneToMany(
			mappedBy = "producto",
			cascade = { CascadeType.PERSIST, CascadeType.MERGE },
			orphanRemoval = true)
	private Set<LineaCompra> lineasCompras = new HashSet<>();
	

	@OneToMany(
			mappedBy = "producto",
			cascade = { CascadeType.PERSIST, CascadeType.MERGE },
			orphanRemoval = true)
	private Set<Pregunta> preguntas = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "producto", orphanRemoval = true)
	private Set<Imagen> imagen = new HashSet<>();


	public Producto() {
	}
	
	public Producto(String nombre, String descripcion,  double precio, int descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.descuento = descuento;
	}



	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public Set<LineaCompra> getLineasCompras() {
		return lineasCompras;
	}


	public void setLineasCompras(Set<LineaCompra> lineasCompras) {
		this.lineasCompras = lineasCompras;
	}

	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public void addImagen(Imagen img) {
		this.imagen.add(img);
		img.setProducto(this);
	}
	
	public Set<Imagen> getImagen() {
		return imagen;
	}

	public void setImagen(Set<Imagen> imagen) {
		this.imagen = imagen;
	}
	
	

}
