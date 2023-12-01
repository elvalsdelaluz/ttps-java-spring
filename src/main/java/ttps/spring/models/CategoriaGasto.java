package ttps.spring.models;

import jakarta.persistence. *;

@Entity 
public class CategoriaGasto {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id_categoria_gasto;
	private String nombre;
	@Lob
	private String imagen;

	//CONSTRUCTOR
	
	
	public CategoriaGasto(String nombre) {
		super();
		this.nombre = nombre;
	}
	public CategoriaGasto() {}
	
	//GETTER AND SETTER
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Long getId_categoria_gasto() {
		return id_categoria_gasto;
	}

	public void setId_categoria_gasto(Long id_categoria_gasto) {
		this.id_categoria_gasto = id_categoria_gasto;
	} 
	
	

}
