package ttps.spring.models;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence. *;


@Entity
@JsonIgnoreProperties({"contactos", "grupos", "contraseña" })
public class Usuario {
	 @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	 @Column(unique=true, nullable=false)
	 private Long id;
	 private String apellido;
	 private String nombre;
	 private String email;
	 private String contraseña;
	 @ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(
	        name = "contactos",
	        joinColumns = @JoinColumn(name = "idUsuario"),
	        inverseJoinColumns = @JoinColumn(name = "idContacto"))
	 private List<Usuario> contactos;
	 
	 @Temporal(TemporalType.DATE)
	 private Date fechaCreacion;
	 
	 @ManyToMany(mappedBy="miembros", fetch = FetchType.EAGER)
	 private List<Grupo> grupos; 
	 
	 //CONSTRUCTORES
	
	
	 public Usuario(String apellido, String nombre, String email, String contraseña) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.email = email;
		this.contraseña = contraseña;
		this.fechaCreacion = new java.util.Date();
		//this.grupos = new ArrayList<Grupo> ();
		//this.contactos = new ArrayList<Usuario>();
	 }
	 
	 public Usuario () {}
	 

	//GETTER AND SETTER
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	
	public List<Usuario> getContactos() {
		return contactos;
	}

	public void setContactos(List<Usuario> contactos) {
		this.contactos = contactos;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
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

