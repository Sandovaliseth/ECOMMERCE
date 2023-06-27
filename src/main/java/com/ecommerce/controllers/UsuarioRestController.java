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
import java.util.stream.Collectors;

import com.ecommerce.entites.Rol;
import com.ecommerce.entites.Usuario;
import com.ecommerce.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("")
	private ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
	    List<Usuario> usuariosSinAdmin = usuarios.stream()
	            .filter(usuario -> usuario.getRol() != Rol.ADMINISTRADOR)
	            .collect(Collectors.toList());

	    return ResponseEntity.ok(usuariosSinAdmin);
	}
	
	@GetMapping("/usuario/{id}")
	public Optional<Usuario> show(@PathVariable Integer id){
		return usuarioService.findById(id);
	}
	
	@PostMapping("/guardarUser")
	public  ResponseEntity<?> save(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.save(usuario));
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Integer id) throws IOException {

	    Optional<Usuario> currentUsuario = this.usuarioService.findById(id);
	    if (currentUsuario.isPresent()) {
	        Usuario updateUsuario = currentUsuario.get();
	        updateUsuario.setUsuario(usuario.getUsuario());
	        updateUsuario.setContrasena(usuario.getContrasena());
	        updateUsuario.setConfirmarContrasena(usuario.getconfirmarContrasena());
	        updateUsuario.setRol(usuario.getRol());

	        try {
	            this.usuarioService.save(updateUsuario); // Guardar el usuario actualizado en la base de datos
	            return ResponseEntity.ok(updateUsuario);
	        } catch (DataAccessException e) {
	            Map<String, Object> response = new HashMap<>();
	            response.put("mensaje", "Error al actualizar el usuario en la base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        Map<String, Object> response = new HashMap<>();
	        response.put("mensaje", "No se encontró el usuario con ID: " + id);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Integer id){
		
		Map<String, Object> response=new HashMap<>();		
		try {	
			Optional<Usuario> usuario=this.usuarioService.findById(id);
		    this.usuarioService.delete(id);
		}catch(DataAccessException e){
			 response.put("mensaje", "Error al eliminar el usuario en la base de datos");
			 response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));	
			 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		 response.put("mensaje", "Usuario eliminado con éxito");		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
