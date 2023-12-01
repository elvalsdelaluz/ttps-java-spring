package ttps.spring.models;


import java.util.Date;
import java.util.List;

import jakarta.persistence. *;

@Entity
public class Gasto {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id_gasto;
	private double monto;
	@Temporal(TemporalType.DATE)
	private Date fecha_creacion;
	@Lob
	private String imagen;
	
	@ManyToOne  //UNIDIRECCIONAL
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	private String forma_division;
	
	@ManyToOne  //UNIDIRECCIONAL
	@JoinColumn(name = "id_categoria_gasto")
	private CategoriaGasto categoria;
	
	@ManyToOne 
	@JoinColumn(name="id_grupo")
	private Grupo grupo;
	
	@OneToMany(mappedBy="gasto")
	private List<Pago> pagos;
	 
	//CONSTRUCTOR
	
	
	public Gasto(double monto, Usuario usuario,
			String forma_division, CategoriaGasto categoria, Grupo grupo) {
		super();
		this.monto = monto;
		this.fecha_creacion = new java.util.Date();
		this.usuario = usuario;
		this.forma_division = forma_division;
		this.categoria = categoria;
		this.grupo = grupo;
	}
	public Gasto() {}
	
	

	//GETTER AND SETTER
	public Long getId_gasto() {
	    return id_gasto;
	}
	
	public void setId_gasto(Long id_gasto) {
		this.id_gasto = id_gasto;
	}
	
	public List<Pago> getPagos() {
		return pagos;
	}

	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha_creacion;
	}
	public void setFecha(Date fecha) {
		this.fecha_creacion = fecha;
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


	public Date getFecha_creacion() {
		return fecha_creacion;
	}


	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}


	public String getForma_division() {
		return forma_division;
	}


	public void setForma_division(String forma_division) {
		this.forma_division = forma_division;
	}
	
	
	

}
