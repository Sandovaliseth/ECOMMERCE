package com.ecommerce.models.services;

import java.util.List;
import java.util.Optional;

import com.ecommerce.entites.Cliente;

public interface IClienteService {
	
	public Cliente save(Cliente cliente);
	public void update(Cliente cliente);
	public void delete(Integer Id);
	//Lista clientes
	public List<Cliente> findAll();
	public Optional<Cliente> findById(Integer id);
}
