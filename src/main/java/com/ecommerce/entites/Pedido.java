package com.ecommerce.entites;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	//Relacion con usuario
	@ManyToOne
	private Cliente cliente;
	
	//Relacion con detalle pedido
	@OneToOne(mappedBy = "pedido")
	private DetallePedido detalle;
	
	public Pedido() {
	}

	public Pedido(Integer id, Date fechaCreacion) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public DetallePedido getDetalle() {
		return detalle;
	}

	public void setDetalle(DetallePedido detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fechaCreacion=" + fechaCreacion + "]";
	}
}
