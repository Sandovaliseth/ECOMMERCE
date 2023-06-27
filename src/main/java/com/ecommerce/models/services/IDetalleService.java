package com.ecommerce.models.services;

import java.util.List;

import com.ecommerce.entites.DetallePedido;

public interface IDetalleService {
	
	public void save (DetallePedido detallePedido);
	public List<DetallePedido> findAll();
	public void delete(Integer Id);
}
