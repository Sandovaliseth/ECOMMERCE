package com.ecommerce.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entites.DetallePedido;
import com.ecommerce.models.dao.IDetallePedido;

@Service
public class DetalleServiceImpl implements IDetalleService{

	@Autowired
	private IDetallePedido IDetalleDao;

	@Override
	public void save(DetallePedido detallePedido) {
		IDetalleDao.save(detallePedido);
	}

	@Override
	public List<DetallePedido> findAll() {
		return IDetalleDao.findAll();
	}

	@Override
	public void delete(Integer Id) {
		IDetalleDao.deleteById(Id);
	}
}
