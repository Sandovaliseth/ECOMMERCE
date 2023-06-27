package com.ecommerce.models.services;

import java.util.List;
import java.util.Optional;

import com.ecommerce.entites.Usuario;

public interface IUsuarioService {
	public Usuario save(Usuario usuario);
	public void update(Usuario usuario);
	public void delete(Integer Id);
	public List<Usuario> findAll();
	public Optional<Usuario> findById(Integer id);
	public List<Usuario> findByCorreoContrasena(String email, String contrasena);
}