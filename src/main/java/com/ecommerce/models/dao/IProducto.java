package com.ecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entites.Producto;

@Repository
public interface IProducto extends JpaRepository<Producto, Integer>{
	
}
