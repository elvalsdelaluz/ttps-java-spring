package ttps.spring.models;

import jakarta.persistence. *;

@Entity 
public class CategoriaGrupo {
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id_categoria_grupo;
	private String nombre;
	@Lob
	private String imagen;
	
	//CONSTRUCTOR
	
	public CategoriaGrupo(String nombre) {
		super();
		this.nombre = nombre;
	}
	public CategoriaGrupo() {}

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


		public Long getId_categoria_grupo() {
			return id_categoria_grupo;
		}


		public void setId_categoria_grupo(Long id_categoria_grupo) {
			this.id_categoria_grupo = id_categoria_grupo;
		} 
	
	

}
