package com.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.ecommerce.models.services.IClienteService;
import com.ecommerce.models.services.IPedidoService;
import com.ecommerce.entites.Cliente;
import com.ecommerce.entites.Pedido;
import com.ecommerce.entites.Producto;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	@GetMapping
	private ResponseEntity<List<Cliente>> getAllProductos() {
		return ResponseEntity.ok(clienteService.findAll());
	}
	
	@PostMapping("/save")
	public Cliente save(@Valid @RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@GetMapping("/compras")
	public ResponseEntity<?> obtenerCompras(@Valid @RequestBody Producto producto, BindingResult bindingResult, HttpSession session) {
		Cliente usuario= clienteService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()) ).get();
		List<Pedido> pedidos= pedidoService.findByCliente(usuario);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<?> detalleCompra(@PathVariable Integer id, HttpSession session) {
		Optional<Pedido> pedido =pedidoService.findById(id);
		pedido.get().getDetalle();
		return ResponseEntity.ok().build();
	}
}
