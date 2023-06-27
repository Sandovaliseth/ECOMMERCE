package com.ecommerce.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entites.Usuario;
import com.ecommerce.models.dao.IUsuario;


@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuario IUsuarioDao; 
	
	@Override
	public Usuario save(Usuario usuario) {
		return IUsuarioDao.save(usuario);
	}

	@Override
	public void update(Usuario usuario) {
		IUsuarioDao.save(usuario);		
	}
	
	@Override
	public Optional<Usuario> findById (Integer Id) {
		return IUsuarioDao.findById(Id);
	}

	@Override
	public void delete(Integer Id) {
		IUsuarioDao.deleteById(Id);
	}

	@Override
	public List<Usuario> findAll() {
		return  IUsuarioDao.findAll();
	}

	@Override
	public List<Usuario> findByCorreoContrasena(String email, String contrasena) {
		return IUsuarioDao.findAll();
	}

}
