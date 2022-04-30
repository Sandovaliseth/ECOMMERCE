package com.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entites.Cliente;
import com.ecommerce.entites.DetallePedido;
import com.ecommerce.entites.Pedido;
import com.ecommerce.entites.Producto;
import com.ecommerce.models.services.IClienteService;
import com.ecommerce.models.services.IDetalleService;
import com.ecommerce.models.services.IPedidoService;
import com.ecommerce.models.services.IProductoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/")
public class HomeRestController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IDetalleService detalleService;

	// para almacenar los detalles de la orden
	List<DetallePedido> detalles = new ArrayList<DetallePedido>();

	// datos de la orden
	Pedido orden = new Pedido();
	
	@GetMapping("pedidos")
	private ResponseEntity<List<Pedido>> getAllPedidos() {
		return ResponseEntity.ok(pedidoService.findAll());
	}

	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();

		model.addAttribute("producto", producto);

		return "usuario/productohome";
	}

	@PostMapping("/carrito")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetallePedido detallePedido = new DetallePedido();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();

		detallePedido.setCantidad(cantidad);
		detallePedido.setPrecio(producto.getPrecio());
		detallePedido.setNombreCli(producto.getNombre());
		detallePedido.setTotal(producto.getPrecio() * cantidad);
		detallePedido.setProducto(producto);
		
		//validar que le producto no se añada 2 veces
		Integer idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detallePedido);
		}
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	// quitar un producto del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// lista nueva de prodcutos
		List<DetallePedido> ordenesNueva = new ArrayList<DetallePedido>();

		for (DetallePedido detallePedido : detalles) {
			if (detallePedido.getProducto().getId() != id) {
				ordenesNueva.add(detallePedido);
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = ordenesNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}
	
	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		//sesion
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "/usuario/carrito";
	}
	
	@GetMapping("/order")
	public String order(Model model, HttpSession session) {
		
		Cliente usuario =clienteService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
		
		return "usuario/resumenorden";
	}
	
	// guardar la orden
	/*@GetMapping("/saveOrden")
	public Pedido save(HttpSession session ) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(pedidoService.generarNumeroPedido());
		
		//usuario
		Cliente usuario =clienteService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();
		
		orden.setUsuario(usuario);
		pedidoService.save(orden);
		
		//guardar detalles
		for (DetallePedido dt:detalles) {
			dt.setPedido(orden);
			detalleService.save(dt);
		}
		
		///limpiar lista y orden
		orden = new Pedido();
		detalles.clear();
		
		return detalleService.save(dt);
	} */
}
