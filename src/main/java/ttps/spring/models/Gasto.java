package ttps.spring.models;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence. *;

@Entity
public class Gasto {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private double monto;
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	@Lob
	private String imagen;
	
	@ManyToOne  //UNIDIRECCIONAL
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	private String formaDivision;
	
	@ManyToOne  //UNIDIRECCIONAL
	@JoinColumn(name = "idCategoriaGasto")
	private CategoriaGasto categoria;
	
	@JsonIgnore
	@ManyToOne 
	@JoinColumn(name="idGrupo")
	private Grupo grupo;
	
	@OneToMany(mappedBy="gasto", fetch = FetchType.EAGER)
	private List<Deuda> participantes;
	
	 
	//CONSTRUCTOR
	
	
	public Gasto(double monto, Usuario usuario,
			String forma_division, CategoriaGasto categoria, Grupo grupo) {
		super();
		this.monto = monto;
		this.fechaCreacion = new java.util.Date();
		this.usuario = usuario;
		this.formaDivision = forma_division;
		this.categoria = categoria;
		this.grupo = grupo;
	}
	public Gasto() {}
	
	

	//GETTER AND SETTER
	

	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario deudor) {
		this.usuario = deudor;
	}
	public CategoriaGasto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaGasto categoria) {
		this.categoria = categoria;
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
	public String getFormaDivision() {
		return formaDivision;
	}
	public void setFormaDivision(String formaDivision) {
		this.formaDivision = formaDivision;
	}
	public List<Deuda> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<Deuda> participantes) {
		this.participantes = participantes;
	}
    
	
	

}
