package com.ecommerce.models.services;

import java.util.List;
import java.util.Optional;

import com.ecommerce.entites.Cliente;
import com.ecommerce.entites.Pedido;

public interface IPedidoService {
	
	Pedido save (Pedido pedido);
	List<Pedido> findAll();
	Optional<Pedido> findById(Integer Id);
	String generarNumeroPedido();
	List<Pedido> findByCliente (Cliente cliente);
}
