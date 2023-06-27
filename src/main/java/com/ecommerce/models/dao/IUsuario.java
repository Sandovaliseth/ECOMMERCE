package com.ecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entites.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer>{

}
