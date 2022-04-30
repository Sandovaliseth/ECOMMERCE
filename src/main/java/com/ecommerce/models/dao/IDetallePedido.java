package com.ecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entites.DetallePedido;

@Repository
public interface IDetallePedido extends JpaRepository<DetallePedido, Integer>{

}
