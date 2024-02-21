package ttps.spring.controllers;

import java.util.List;
import java.util.Optional;

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

import ttps.spring.dto.UsuarioCredenciales;
import ttps.spring.dto.UsuarioDTO;
import ttps.spring.dto.UsuarioRequestDTO;
import ttps.spring.models.Usuario;
import ttps.spring.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	//CONSTRUCTOR
	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	//METODOS
//	@PostMapping("/login")
//    public ResponseEntity<Boolean> autenticarUsuario(@RequestParam String email, @RequestParam String contraseña) {
//        boolean autenticado = usuarioService.autenticarUsuario(email, contraseña);
//        return ResponseEntity.ok(autenticado);
//    }

	@PostMapping("/login")
    public ResponseEntity<UsuarioDTO> autenticarUsuario(@RequestBody UsuarioCredenciales credenciales) {
        String email = credenciales.getEmail();
        String contraseña = credenciales.getContraseña();

        Usuario usuario = usuarioService.autenticarUsuario(email, contraseña);
        if (usuario != null) {
        	UsuarioDTO usuarioResponse = new UsuarioDTO();
        	usuarioResponse.setId_usuario(usuario.getId());
        	usuarioResponse.setEmail(usuario.getEmail());
            return new ResponseEntity<UsuarioDTO>(usuarioResponse, HttpStatus.OK);
        }
		return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
    }
	
	@GetMapping()
	public ResponseEntity<List<Usuario>> obtenerUsuarios(){
		List<Usuario> usuarios = usuarioService.obtenerUsuarios();
		if(usuarios.isEmpty()){
			 return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
			
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Usuario>  obtenerUsuarioPorId(@PathVariable("id") Long id){
		Optional<Usuario> usuario = this.usuarioService.obtenerPorId(id);
		if (usuario.isPresent()) {
			return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
	}
	

	
	@PostMapping
	public ResponseEntity<Void> crearUsuario(@RequestBody UsuarioRequestDTO usuario) {
		
	    boolean existe = this.usuarioService.existeUsuario(usuario.getEmail());

	    if (existe) {
	        System.out.println("Ya existe un usuario con email " + usuario.getEmail());
	        return new ResponseEntity<>(HttpStatus.CONFLICT);
	    }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuario.getNombre());
        nuevoUsuario.setApellido(usuario.getApellido());
        nuevoUsuario.setEmail(usuario.getEmail());
        nuevoUsuario.setContraseña(usuario.getPassword());
        System.out.println("Constraseñaas");
        System.out.println(usuario.getPassword());
        System.out.println(nuevoUsuario.getContraseña());
	    this.usuarioService.guardarUsuario(nuevoUsuario);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	 }
    
	 @PutMapping("/{id}")
	 public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
		 System.out.println("Actualizando el usuario " + id);
	
		 Optional<Usuario> currentUser = this.usuarioService.obtenerPorId(id);
	
		 if (currentUser.isPresent()) {
			 Usuario auxUser = currentUser.get();
			 auxUser.setNombre(usuario.getNombre());
			 auxUser.setApellido(usuario.getApellido());
			 this.usuarioService.guardarUsuario(auxUser);
			 return new ResponseEntity<Usuario>(currentUser.get(), HttpStatus.OK);
		 }
		 System.out.println("No se pudo actualizar el usuario con id" + id );
		 return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	 }
	 
	
		@DeleteMapping(path="/{id}")
		public ResponseEntity<Usuario> eliminarPorId(@PathVariable("id") Long id) {
			Optional<Usuario> user = this.usuarioService.obtenerPorId(id);
			if (user.isPresent()) {
				this.usuarioService.eliminarUsuario(id);
				System.out.println("Se eliminó el usuario con id" +id);
				return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
			}
			else {
				System.out.println("No es posible eliminar, no se encuentra el usuario con id " + id);
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}	
		}
		
	
}
