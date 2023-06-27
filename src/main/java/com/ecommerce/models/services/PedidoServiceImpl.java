package com.ecommerce.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entites.Cliente;
import com.ecommerce.entites.Pedido;
import com.ecommerce.models.dao.IPedido;

@Service
public class PedidoServiceImpl implements IPedidoService{
	
	@Autowired
	private IPedido IPedidoDao;

	@Override
	public Pedido save(Pedido pedido) {
		return IPedidoDao.save(pedido);
	}

	@Override
	public List<Pedido> findAll() {
		return IPedidoDao.findAll();
	}
	
	@Override
	public Optional<Pedido> findById(Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findByCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
}
