package ttps.spring.models;

import java.util.Date;

import jakarta.persistence. *;

@Entity
public class Pago {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private double monto;
	
	@ManyToOne
	@JoinColumn(name="idGrupo")
	private Grupo grupo;
	
	@ManyToOne 
	@JoinColumn(name = "idUsuarioDeudor")
	private Usuario usuario_deudor;
	
	@ManyToOne 
	@JoinColumn(name = "idUsuarioAcreedor")
	private Usuario usuario_acreedor;
	
	
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	//CONSTRUCTOR
	
	
	public Pago(double monto, Grupo grupo) {
		super();
		this.monto = monto;
		this.grupo = grupo;
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
	
	
	public Usuario getUsuario_deudor() {
		return usuario_deudor;
	}
	public void setUsuario_deudor(Usuario usuario_deudor) {
		this.usuario_deudor = usuario_deudor;
	}
	public Usuario getUsuario_acreedor() {
		return usuario_acreedor;
	}
	public void setUsuario_acreedor(Usuario usuario_acreedor) {
		this.usuario_acreedor = usuario_acreedor;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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
