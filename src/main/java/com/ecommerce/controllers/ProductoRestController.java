package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entites.Producto;
import com.ecommerce.models.services.IProductoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/productos")
public class ProductoRestController {

	@Autowired
	private IProductoService productoService;

	//Listar productos
	@GetMapping("")
	private ResponseEntity<List<Producto>> getAllProductos() {
		return ResponseEntity.ok(productoService.findAll());
	}
	
	@GetMapping("/producto/{id}")
	public Producto show(@PathVariable Integer id){
		return productoService.findById(id);
	}
	
	@PostMapping("/guardarProducto")
	public ResponseEntity<Producto> save(@Valid @ModelAttribute Producto producto, @RequestParam("imagen") MultipartFile imagen) {
	    Producto productoGuardado = productoService.save(producto, imagen);
	    return ResponseEntity.ok(productoGuardado);
	}
	
	/*@PutMapping("/producto/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result,
	        @PathVariable Integer id, @RequestParam("imagen") MultipartFile file) throws IOException {
	   
	    Producto currentproducto = this.productoService.findById(id);
	    Producto updateProducto = null;
	    Map<String, Object> response = new HashMap<>();
	    if (!file.isEmpty()) {
	        if (currentproducto.getImagen() != null && !currentproducto.getImagen().isEmpty()) {
	            // Eliminar la imagen actual si existe
	            upload.deleteImagen(currentproducto.getImagen());
	        }
	        String nomImagen = upload.saveImagen(file);
	        producto.setImagen(nomImagen);
	    }
	   
	    if (result.hasErrors()) {
	        // Manejar los errores de validación si existen
	        List<String> errores = new ArrayList<>();
	        for (FieldError error : result.getFieldErrors()) {
	            errores.add(error.getField() + ": " + error.getDefaultMessage());
	        }
	        response.put("errores", errores);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	   
	    try {
	        // Realizar la lógica para actualizar el producto en la base de datos
	        updateProducto = productoService.save(producto, imagen);
	    } catch (Exception e) {
	        response.put("error", "Error al actualizar el producto");
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	   
	    response.put("producto", updateProducto);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} */
	
	@DeleteMapping("/producto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Integer id){
		
		Map<String, Object> response=new HashMap<>();		
		try {	
			Producto producto=this.productoService.findById(id);
			Producto p = new Producto();
			p = productoService.get(id).get();
		    this.productoService.delete(id);
		}catch(DataAccessException e){
			 response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			 response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));	
			 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		 response.put("mensaje", "El producto eliminado con éxito");		 
		 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
