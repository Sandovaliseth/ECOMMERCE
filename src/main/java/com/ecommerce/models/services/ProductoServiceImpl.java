package com.ecommerce.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entites.Producto;
import com.ecommerce.models.dao.IProducto;

@Service
public class ProductoServiceImpl implements IProductoService{

	//Inyectar un objeto a una clase
	@Autowired
	private IProducto productoDao;
	
	@Override
	public Producto save(Producto producto) {
		return productoDao.save(producto);
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

}
