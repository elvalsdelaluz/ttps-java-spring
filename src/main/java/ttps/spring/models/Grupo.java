package ttps.spring.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence. *;

@Entity
public class Grupo {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id_grupo;
    private String nombre;
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
        name = "grupo_usuario",
        joinColumns = @JoinColumn(name = "id_grupo"),
        inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private List<Usuario> miembros;
    
    @OneToMany(mappedBy="grupo")
	private List<Gasto> gastos;
    
    @ManyToOne //UNIDIRECCIONAL
	@JoinColumn(name = "id_categoria_grupo")
	private CategoriaGrupo categoria;
    
	@Temporal(TemporalType.DATE)
	private Date fecha_creacion;
	
	//CONSTRUCTOR
	
	
	public Grupo(String nombre, List<Usuario> miembros, List<Gasto> gastos, CategoriaGrupo categoria) {
		super();
		this.nombre = nombre;
		this.miembros = miembros;
		this.gastos = gastos;
		this.categoria = categoria;
		this.fecha_creacion = new java.util.Date();;
	}
	
	public Grupo() {}
	

	//GETTER AND SETTER
	public Long getId_grupo() {
		return id_grupo;
	}
	public void setId_grupo(Long id_grupo) {
		this.id_grupo = id_grupo;
	}
	
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

	public CategoriaGrupo getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaGrupo categoria) {
		this.categoria = categoria;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	
	

}
