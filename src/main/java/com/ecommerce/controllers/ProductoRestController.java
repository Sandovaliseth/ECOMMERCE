package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entites.Producto;
import com.ecommerce.entites.Cliente;
import com.ecommerce.models.services.IProductoService;
import com.ecommerce.models.services.UploadFileService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/productos")
public class ProductoRestController {

	@Autowired
	private UploadFileService upload;

	@Autowired
	private IProductoService productoService;

	@GetMapping
	private ResponseEntity<List<Producto>> getAllProductos() {
		return ResponseEntity.ok(productoService.findAll());
	}
	
	@PostMapping("/crearProducto")
	public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult bindingResult) {
		return ResponseEntity.ok(productoService.save(producto));
	}

	@GetMapping("/verProductos/{id}")
	public ResponseEntity<?> show (@Valid @RequestBody Producto producto, BindingResult bindingResult) {
		return ResponseEntity.ok(productoService.findAll());
	}

	@PostMapping("/save")
	public Producto save(Producto producto, @RequestParam("imagen") MultipartFile file) throws IOException {
		Cliente usuario = new Cliente(1, "", "", "", "", 0, "", "");
		producto.setUsuario(usuario);
		// subir y guardar imagen
		if (producto.getId() == null) {
			String nomImagen = upload.saveImagen(file);
			producto.setImagen(nomImagen);
		} else {
		}
		return productoService.save(producto);
	}

	@GetMapping("/edit/{id}")
	public ResponseEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody Producto producto,
			BindingResult bindingResult) {
		Producto p = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		p = optionalProducto.get();
		productoService.update(p);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/update") 
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result,
			@RequestParam("imagen") MultipartFile file) throws IOException {
			 if(file.isEmpty()) { 
				 Producto p = new Producto();
				 productoService.get(producto.getId()).get();
				 producto.setImagen(p.getImagen()); 
			 } else{
				Producto p = new Producto();
				p = productoService.get(producto.getId()).get();
				if(!p.getImagen().equals("default.jpg")) {
					upload.deleteImagen(p.getImagen());
				}
				String nomImagen = upload.saveImagen(file);
				producto.setImagen(nomImagen);
			 }
			 
			 productoService.update(producto);
			 return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Producto p = new Producto();
		p = productoService.get(id).get();
		upload.deleteImagen(p.getImagen());
		productoService.delete(id);
		return ResponseEntity.ok().build();
	}
}
