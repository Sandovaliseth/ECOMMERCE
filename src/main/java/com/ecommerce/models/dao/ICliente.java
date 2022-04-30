package com.ecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entites.Cliente;

@Repository
public interface ICliente extends JpaRepository<Cliente, Integer>{

}
