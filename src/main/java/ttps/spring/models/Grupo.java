package ttps.spring.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence. *;

@Entity
public class Grupo {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
    private String nombre;
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
        name = "grupoUsuario",
        joinColumns = @JoinColumn(name = "idGrupo"),
        inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    
    private List<Usuario> miembros;
    
    @OneToMany(mappedBy="grupo", fetch = FetchType.EAGER)
    @JsonIgnore
	private List<Gasto> gastos;
    
    
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	//CONSTRUCTOR
	
	
	public Grupo(String nombre, List<Usuario> miembros, List<Gasto> gastos) {
		super();
		this.nombre = nombre;
		this.miembros = miembros;
		this.gastos = gastos;
		this.fechaCreacion = new java.util.Date();;
	}
	
	public Grupo() {}
	

	//GETTER AND SETTER
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Usuario> getMiembros() {
		return miembros;
	}
	public void setMiembros(List<Usuario> miembros) {
		this.miembros = miembros;
	}
	public List<Gasto> getGastos() {
		return gastos;
	}
	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
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
