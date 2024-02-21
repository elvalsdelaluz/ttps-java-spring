package ttps.spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Deuda {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private double monto;
	@ManyToOne  //UNIDIRECCIONAL
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="idGasto")
	private Gasto gasto;
	
	//CONSTRUCTOR
	public Deuda() {}
	
	//GETTER AND SETTER
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Gasto getGasto() {
		return gasto;
	}
	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}
	
}
