package ttps.spring.services;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.Usuario;
import ttps.spring.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	public ArrayList<Usuario> obtenerUsuarios(){
		return (ArrayList<Usuario>)usuarioRepository.findAll();
	}
	
	public Optional<Usuario> obtenerPorId(Long id){
		return usuarioRepository.findById(id);
	}

	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public boolean eliminarUsuario(Long id) {
		try {
			usuarioRepository.deleteById(id);
			return true;
		}catch(Exception err) {
			return false;
		}
	}
	
	
}
