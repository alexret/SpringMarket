package com.example.demo.controladores;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entidades.Carro;
import com.example.demo.entidades.Compra;
import com.example.demo.entidades.LineaCompra;
import com.example.demo.entidades.Producto;
import com.example.demo.entidades.User;
import com.example.demo.servicios.CompraServicio;
import com.example.demo.servicios.LineaCompraServicio;
import com.example.demo.servicios.ProductoServicio;
import com.example.demo.servicios.UserServicio;

@Controller
@RequestMapping(value = "/compra")
public class CompraController {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private UserServicio userServicio;
	
	@Autowired
	private CompraServicio compraServicio;
	
	@Autowired
	private LineaCompraServicio lineaCompraServicio;
	
	@GetMapping("/carrocompra")
	public ModelAndView product(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		List<Carro> listacarro = (List<Carro>) session.getAttribute("listacarro");
		

		mav.addObject("listacarro", listacarro);
		mav.setViewName("carro/carro");
		return mav;
	}
	
	@GetMapping("/add/{idProducto}")
	public String buscarProducto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {

		List<Carro> listacarros = (List<Carro>) request.getSession().getAttribute("listacarro");
		Carro carro = new Carro(productoServicio.obtenerProducto(idProducto));
		Integer cantidad = 1;
		// Se recoge la cantidad del producto
		if(listacarros==null) {
			listacarros = new ArrayList<Carro>();
			carro.setCantidadProductoCarro(cantidad);
			listacarros.add(carro);
		}
		else if(listacarros.contains(carro)) {
			carro=listacarros.get(listacarros.indexOf(carro));
			cantidad=carro.getCantidadProductoCarro()+1;
			carro.setCantidadProductoCarro(cantidad);
			listacarros.remove(carro);
			listacarros.add(carro);
		}
		else
			listacarros.add(carro);

		//Meter el carrito en lista
		request.getSession().setAttribute("listacarro", listacarros);

		return "redirect:/index";
	}
	
	@GetMapping("/quitarcarro/{idProducto}")
	public String buscarProductoborrar(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {

		List<Carro> listacarros = (List<Carro>) request.getSession().getAttribute("listacarro");
		Carro carro = new Carro(productoServicio.obtenerProducto(idProducto));
		Integer cantidad = 1;
		// Se recoge la cantidad del producto
		if(listacarros.contains(carro)) {
			carro=listacarros.get(listacarros.indexOf(carro));
			cantidad=carro.getCantidadProductoCarro()-1;
			if(cantidad==0)
				listacarros.remove(carro);
			else {
				carro.setCantidadProductoCarro(cantidad);
				listacarros.remove(carro);
				listacarros.add(carro);
			}
		}

		//Meter el carrito en lista
		request.getSession().setAttribute("listacarro", listacarros);

		return "redirect:/compra/carrocompra";
	}
	
	@PostMapping("/{idProducto}")
	public String compraProductoDirecto(HttpServletRequest request, @PathVariable("idProducto") long idProducto) {
		User usario=new User();
		Producto producto=new Producto();
		LineaCompra lineaCompra=new LineaCompra();
		Compra compra=new Compra();

		if(request.getSession().getAttribute("idUsuario")!=null) {
			usario=userServicio.obtenerUsuario((long) request.getSession().getAttribute("idUsuario"));	
			producto=productoServicio.obtenerProducto(idProducto);
			compra.setUser(usario);
			lineaCompra.setProducto(producto);
			lineaCompra.setCompra(compra);
			lineaCompra.setCantidad(1);
			compraServicio.crearCompra(compra);
			lineaCompraServicio.crearLineaCompra(lineaCompra);
			return "redirect:/index";
		}
		else
			return "redirect:/user/login";	
	}

}