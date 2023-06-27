package com.ecommerce.models.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entites.Producto;
import com.ecommerce.models.dao.IProducto;

@Service
public class ProductoServiceImpl implements IProductoService{

	//Inyectar un objeto a una clase
	@Autowired
	private IProducto productoDao;
	
	@Override
	public Producto save(Producto producto, MultipartFile imagen) {
	    String rutaImagen = guardarImagen(imagen);
	    producto.setImagen(rutaImagen);
	    return productoDao.save(producto);
	}

	private String guardarImagen(MultipartFile imagen) {
	    try {
	        String nombreImagen = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
	        Path rutaImagenCompleta = Paths.get("src", "main", "resources", "static", "images", nombreImagen);
	        Files.copy(imagen.getInputStream(), rutaImagenCompleta, StandardCopyOption.REPLACE_EXISTING);
	        return nombreImagen;
	    } catch (IOException e) {
	        throw new RuntimeException("Error al guardar la imagen", e);
	    }
	}

	@Override
	public Optional<Producto> get(Integer Id) {
		return productoDao.findById(Id);
	}

	@Override
	public void update(Producto producto) {
		productoDao.save(producto);	
	}

	@Override
	public void delete(Integer Id) {
		productoDao.deleteById(Id);
	}

	@Override
	public List<Producto> findAll() {
		return productoDao.findAll();
	}

	@Override
	public Producto findById(Integer id) {
		return productoDao.findById(id).orElse(null);
	}
}
