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
	
	//CONSTRUCTOR
	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
    //METODOS
	public Usuario autenticarUsuario(String email, String contraseña) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElse(null);
       
        if (usuario != null){
        	if (!usuario.getContraseña().equals(contraseña)) {
        		System.out.println(usuario.getContraseña());
        		System.out.println(contraseña);
        		return null;
        	}         	
        }
        return usuario;
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
	
	public boolean existeUsuario(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email)
                .orElse(null);
		return usuario != null;
	}
	
}
