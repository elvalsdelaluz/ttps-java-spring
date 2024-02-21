package ttps.spring.models;

import java.util.Date;

import jakarta.persistence. *;

@Entity
public class Pago {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private double monto;
	
	@ManyToOne
	@JoinColumn(name="idGasto")
	private Gasto gasto;
	
	@ManyToOne 
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	//CONSTRUCTOR
	
	
	public Pago(double monto, Gasto gasto, Usuario usuario) {
		super();
		this.monto = monto;
		this.gasto = gasto;
		this.usuario = usuario;
		this.fechaCreacion = new java.util.Date();;
	}
	public Pago() {}
	
	 

	//GETTER AND SETTER
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario deudor) {
		this.usuario = deudor;
	}

	public Gasto getGasto() {
		return gasto;
	}


	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
    
	
	
	 
	

}
