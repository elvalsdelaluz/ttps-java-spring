package ttps.spring.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.models.Usuario;
import ttps.spring.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	
	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@GetMapping()
	public ArrayList<Usuario> obtenerUsuarios(){
		return usuarioService.obtenerUsuarios();
	}
	
	@GetMapping(path="/{id}")
	public Optional<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id){
		return this.usuarioService.obtenerPorId(id);
	}
	
	@PostMapping
	public Usuario guardarUsuario(@RequestBody Usuario usuario) {
		return this.usuarioService.guardarUsuario(usuario);
	}
    
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.usuarioService.eliminarUsuario(id);
		if (ok) {
			return "Se eliminó el usuario con id" +id;
		}
		else {
			return "No se pudo eliminar el usuario con id" +id;
		}
		
	}
	
}
