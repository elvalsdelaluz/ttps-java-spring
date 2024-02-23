package ttps.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.dto.GastoDTO;
import ttps.spring.dto.GrupoDTO;
import ttps.spring.dto.PagoDTO;
import ttps.spring.dto.UsuarioCredenciales;
import ttps.spring.dto.UsuarioDTO;
import ttps.spring.models.Grupo;
import ttps.spring.models.Usuario;
import ttps.spring.services.GrupoService;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
	@Autowired
	private GrupoService grupoService;

	public GrupoController(GrupoService grupoService) {
		super();
		this.grupoService = grupoService;
	}
	
//	@GetMapping()
//	public ArrayList<Grupo> obtenerGrupos(){
//		return grupoService.obtenerGrupos();
//	}
	
	@PostMapping("/obtenerGrupos")
	public ResponseEntity<List<Grupo>> obtenerGruposDeUnUsuario(@RequestBody UsuarioDTO usuario){
	    List<Grupo> grupos = grupoService.obtenerGrupos(usuario.getId_usuario());
	    System.out.println(grupos);
	    if (grupos != null) {
	        return new ResponseEntity<>(grupos, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
    
	@PostMapping
	public ResponseEntity<Void> crearGrupo(@RequestBody GrupoDTO grupo) {
		System.out.println("-----------CREANDO UN GRUPO NUEVO--------------");
		//Chequeo que la categoria de grupo exista
        Grupo grupoValido = new Grupo();
        grupoValido.setNombre(grupo.getNombre());
        //Buscar al usuario creador
        Usuario miembro = this.grupoService.validarUsuario(grupo.getIdUser());
        System.out.println("Agregando usuario id "+ grupo.getIdUser()+ " al grupo.");
        //El problema de esto es que se me pisan los usuarios
        List<Usuario> miembros = new ArrayList<Usuario>();
        miembros.add(miembro);
        grupoValido.setMiembros(miembros);
	    this.grupoService.guardarGrupo(grupoValido);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	 }
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Grupo> agregarUsuarioGrupo(@PathVariable("id") Long id, @RequestBody UsuarioDTO userRequest ) {
	  //Este controlador me permite agregar a un grupo usuarios, 
	  //uso el método put porque estoy actualizando el grupo
	  System.out.println("----------AGREGANDO USUARIO A UN GRUPO-------------");
	  //Valido que el usuario exista
	  Usuario user = this.grupoService.validarUsuario(userRequest.getId_usuario());
	  if (user == null) {
		  System.out.println("El usuario no existe" );
		  return new ResponseEntity<Grupo>(HttpStatus.NOT_FOUND);
	  }
	  //Recupero al grupo y le agrego el usuario
	  Grupo grupo = this.grupoService.obtenerPorId(id).get();
	  if (grupo == null) {
		  System.out.println("El grupo no existe" );
		  return new ResponseEntity<Grupo>(HttpStatus.NOT_FOUND);
	  }
	  List<Usuario> usuarios = grupo.getMiembros();
	  usuarios.add(user);
	  grupo.setMiembros(usuarios);
	  this.grupoService.guardarGrupo(grupo);
	 
	  return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
	}
	
	@GetMapping("/participantes/{id}")
	public ResponseEntity<List<Usuario>> retornarUsuariosQueNoEstanEnElGrupo(@PathVariable("id") Long id) {
	  //Recupero al grupo
	  Grupo grupo = this.grupoService.obtenerPorId(id).get();
	  if (grupo == null) {
		  System.out.println("El grupo no existe" );
		  return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
	  }
	  //Recupero los miembros del grupo 
	  List<Usuario> usuariosDelGrupo = grupo.getMiembros();
	  //Recupero todos los usuarios del sistema
	  List<Usuario> todosLosUsuarios = this.grupoService.devolverUsuariosQueNoEstanEnElGrupo(usuariosDelGrupo); 
	  //Retorno los usuarios que no estan en el grupo
	  return new ResponseEntity<List<Usuario>>(todosLosUsuarios, HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.grupoService.eliminarGrupo(id);
		if (ok) {
			return "Se eliminó el usuario con id" +id;
		}
		else {
			return "No se pudo eliminar el usuario con id" +id;
		}
		
	}

}
