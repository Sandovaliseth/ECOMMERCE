package com.ecommerce.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entites.DetallePedido;
import com.ecommerce.entites.Pedido;
import com.ecommerce.entites.Producto;
import com.ecommerce.entites.Usuario;
import com.ecommerce.models.services.IDetalleService;
import com.ecommerce.models.services.IProductoService;
import com.ecommerce.models.services.UsuarioServiceImpl;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("")
public class HomeRestController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private IDetalleService detalleService;

	//para almacenar los detalles de la orden
	List<DetallePedido> detalles = new ArrayList<DetallePedido>();
	
	// datos de la orden
	Pedido orden = new Pedido();
	
	@GetMapping("/home")
	private ResponseEntity<List<Producto>> getAllProductos() {
		return ResponseEntity.ok(productoService.findAll());
	}

	@GetMapping("/detalle/{id}")
	public Producto show(@PathVariable Integer id){
		return productoService.findById(id);
	}
	
	@PutMapping("/productoHome/{id}")	
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto,BindingResult result,@PathVariable Integer id, 
			@RequestParam("imagen") MultipartFile file) throws IOException{
		
		Producto currentproducto=this.productoService.findById(id);
		Producto updateProducto=null;
        Map<String, Object> response=new HashMap<>();	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);	
	}
	
	@GetMapping("/Carrito")
	private ResponseEntity<List<DetallePedido>> getAll() {
		return ResponseEntity.ok(detalleService.findAll());
	}
	
	@PostMapping("/guardarCarro")
	public ResponseEntity<?> save (@Valid @RequestBody DetallePedido detallePedido) {
		detalleService.save(detallePedido);
		Map<String, Object> response=new HashMap<>();	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/detalleCarro/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Map<String, Object> response=new HashMap<>();		
		try {	
			detalleService.delete(id);
		}catch(DataAccessException e){		
		}
		 response.put("mensaje", "El producto del carrito eliminado con éxito");		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping(path = "/login")
	public Usuario login (@RequestBody Usuario usuario) {
		List<Usuario> usuarios = usuarioServiceImpl.findByCorreoContrasena(usuario.getUsuario(), usuario.getContrasena());
		if (!usuarios.isEmpty()) {
			return usuarios.get(0);
		}
		return null;
	}
}
