package com.ecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entites.Pedido;

@Repository
public interface IPedido extends JpaRepository<Pedido, Integer>{

}
