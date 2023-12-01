package ttps.spring.models;


import java.util.Date;
import java.util.List;

import jakarta.persistence. *;


@Entity
public class Usuario {
	 @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	 @Column(unique=true, nullable=false)
	 private Long id_usuario;
	 private String apellido;
	 private String nombre;
	 private String email;
	 private String contraseña;
	 @ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(
	        name = "contactos",
	        joinColumns = @JoinColumn(name = "usuario_id"),
	        inverseJoinColumns = @JoinColumn(name = "contacto_id"))
	 private List<Usuario> contactos;
	 
	 @Temporal(TemporalType.DATE)
	 private Date fecha_creacion;
	 
	 @ManyToMany(mappedBy="miembros", fetch = FetchType.EAGER)
	 private List<Grupo> grupos; 
	 
	 //CONSTRUCTORES
	
	
	 public Usuario(String apellido, String nombre, String email, String contraseña) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.email = email;
		this.contraseña = contraseña;
		this.fecha_creacion = new java.util.Date();
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

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public List<Usuario> getContactos() {
		return contactos;
	}

	public void setContactos(List<Usuario> contactos) {
		this.contactos = contactos;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

//	public List<Grupo> getGrupos() {
//		return grupos;
//	}
//
//	public void setGrupos(List<Grupo> grupos) {
//		this.grupos = grupos;
//	}
//    
	
	 

}

