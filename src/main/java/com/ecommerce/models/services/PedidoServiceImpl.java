package com.ecommerce.models.services;

import java.util.ArrayList;
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
	
	//Mirar el ultimo numero de la orden
	public String generarNumeroPedido() {
		int numero=0;
		String numeroConcatenado="";
		List<Pedido> pedidos = findAll();
		List<Integer> numeros = new ArrayList<Integer>();
		pedidos.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
			if(pedidos.isEmpty()) {
				numero=1;
			}else {
				numero=numeros.stream().max(Integer::compare).get();
				numero++;
			}if(numero<10) {
				numeroConcatenado="000000000"+String.valueOf(numero);
			}else if (numero<100) {
				numeroConcatenado="00000000"+String.valueOf(numero);
			}else if (numero<1000) {
				numeroConcatenado="0000000"+String.valueOf(numero);
			}else if (numero<10000) {
				numeroConcatenado="0000000"+String.valueOf(numero);
			}
		return numeroConcatenado;
	}

	@Override
	public List<Pedido> findByCliente(Cliente cliente) {
		//return IPedidoDao.findByCliente(cliente);
		return null;
	}

	@Override
	public Optional<Pedido> findById(Integer Id) {
		return IPedidoDao.findById(Id);
	}
}
