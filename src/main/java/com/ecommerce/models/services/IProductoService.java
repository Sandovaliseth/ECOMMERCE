package com.ecommerce.models.services;

import java.util.List;
import java.util.Optional;

import com.ecommerce.entites.Producto;

public interface IProductoService {
	
	public Producto save(Producto producto);
	//obtener un solo producto por su id
	public Optional<Producto> get(Integer Id);
	public void update(Producto producto);
	public void delete(Integer Id);
	//Mostrar la lista de productos 
	public List<Producto> findAll();

}
