package com.ecommerce.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import  com.ecommerce.models.services.IClienteService;
import com.ecommerce.models.services.IPedidoService;
import com.ecommerce.models.services.IUsuarioService;
import com.ecommerce.entites.Cliente;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("")
	private ResponseEntity<List<Cliente>> getAllClientes() {
		return ResponseEntity.ok(clienteService.findAll());
	}
	
	@GetMapping("/cliente/{id}")
	public Optional<Cliente> show(@PathVariable Integer id){
		return clienteService.findById(id);
	}
	
	@PostMapping("/guardarCli")
	public  ResponseEntity<?> save(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.save(cliente));
	}
	
	@PutMapping("/cliente/{id}")	
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result,@PathVariable Integer id) throws IOException{
		
		Optional<Cliente> currentproducto=this.clienteService.findById(id);
		Cliente updateCliente=null;
        Map<String, Object> response=new HashMap<>();	
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Integer id){
		
		Map<String, Object> response=new HashMap<>();		
		try {	
			Optional<Cliente> cliente=this.clienteService.findById(id);
		    this.clienteService.delete(id);
		}catch(DataAccessException e){
			 response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			 response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));	
			 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		 response.put("mensaje", "El cliente se ha eliminado con éxito");		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
