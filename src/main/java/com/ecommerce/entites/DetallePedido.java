package com.ecommerce.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalles")
public class DetallePedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombreCli;
	private Integer telefono;
	private String pais;
	private String direccionCli;
	private double cantidad;
	private double precio;
	private double total;
	
	//Relacion con pedido
	@OneToOne
	private Pedido pedido;
	
	//Relacion con producto
	@ManyToOne
	private Producto producto;
	
	public DetallePedido() {
	}

	public DetallePedido(Integer id, String nombreCli, Integer telefono, String pais, String direccionCli,
			double cantidad, double precio, double total) {
		super();
		this.id = id;
		this.nombreCli = nombreCli;
		this.telefono = telefono;
		this.pais = pais;
		this.direccionCli = direccionCli;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreCli() {
		return nombreCli;
	}

	public void setNombreCli(String nombreCli) {
		this.nombreCli = nombreCli;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDireccionCli() {
		return direccionCli;
	}

	public void setDireccionCli(String direccionCli) {
		this.direccionCli = direccionCli;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "DetallePedido [id=" + id + ", nombreCli=" + nombreCli + ", telefono=" + telefono + ", pais=" + pais
				+ ", direccionCli=" + direccionCli + ", cantidad=" + cantidad + ", precio=" + precio + ", total="
				+ total + "]";
	}
}
