package com.ecommerce.entites;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	private String usuario;
	private String contrasena;
	private String confirmarcontrasena;
	@Enumerated(EnumType.STRING)
	private Rol rol;
	
	@OneToOne(mappedBy = "usuario")
    private Cliente cliente;
	
	public Usuario() {
		this.rol = Rol.USUARIO;
	}
	
	public Usuario(Integer id, String usuario, String contrasena, String confirmarContrasena, Rol rol, Cliente cliente) {
	    this.usuario = usuario;
	    this.contrasena = contrasena;
	    this.confirmarcontrasena = confirmarContrasena;
	    this.rol = rol;
	    this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getconfirmarContrasena() {
		return confirmarcontrasena;
	}

	public void setConfirmarContrasena(String confirmarContrasena) {
		this.confirmarcontrasena = confirmarContrasena;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", contrasena=" + contrasena + ", confirmarContrasena="
				+ confirmarcontrasena + ", rol=" + rol + ", cliente=" + cliente + "]";
	}

}
