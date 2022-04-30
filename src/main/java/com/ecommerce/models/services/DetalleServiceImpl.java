package com.ecommerce.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entites.DetallePedido;
import com.ecommerce.models.dao.IDetallePedido;

@Service
public class DetalleServiceImpl implements IDetalleService{

	@Autowired
	private IDetallePedido IDetalleDao;

	@Override
	public DetallePedido save(DetallePedido detallePedido) {
		return IDetalleDao.save(detallePedido);
	}
}
