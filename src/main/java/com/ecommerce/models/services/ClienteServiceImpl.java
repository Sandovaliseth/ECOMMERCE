package com.ecommerce.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entites.Cliente;
import com.ecommerce.models.dao.ICliente;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private ICliente IClienteDao;

	@Override
	public Cliente save(Cliente cliente) {
		return IClienteDao.save(cliente);
	}

	@Override
	public Optional<Cliente> findById (Integer Id) {
		return IClienteDao.findById(Id);
	}

	@Override
	public void update(Cliente cliente) {
		IClienteDao.save(cliente);	
	}

	@Override
	public void delete(Integer Id) {
		IClienteDao.deleteById(Id);
	}

	@Override
	public List<Cliente> findAll() {
		return  IClienteDao.findAll();
	}

}
