package ttps.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.Grupo;
import ttps.spring.models.Usuario;
import ttps.spring.repositories.GrupoRepository;

@Service
public class GrupoService {
	
	private GrupoRepository grupoRepository;
	private UsuarioService usuarioService;
    
	@Autowired
	public GrupoService(GrupoRepository grupoRepository, UsuarioService usuarioService) {
		super();
		this.grupoRepository = grupoRepository;
		this.usuarioService = usuarioService;
	}
	

//	public ArrayList<Grupo> obtenerGrupos(){
//		return (ArrayList<Grupo>)grupoRepository.findAll();
//	}
//	
	public List<Grupo> obtenerGrupos(Long idUsuario) {
		//Tengo a usuarioService, tambien podria pedirle los grupos a él c:
        //return grupoRepository.findByMiembros_Id_Usuario(idUsuario);
		
		//Recupero al usuario
		Usuario usuario = usuarioService.obtenerPorId(idUsuario).get();
	    return usuario.getGrupos();
		
    }
	
	
	public Optional<Grupo> obtenerPorId(Long id){
		return grupoRepository.findById(id);
	}
	
	
	public Grupo guardarGrupo(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	public boolean eliminarGrupo(Long id) {
		try {
			grupoRepository.deleteById(id);
			return true;
		}catch(Exception err) {
			return false;
		}
	}
	

	public Usuario validarUsuario (Long id) {
	    return this.usuarioService.obtenerPorId(id).get();
	}
	
	public List <Usuario> devolverUsuariosQueNoEstanEnElGrupo(List<Usuario> usuariosDelGrupo){
		List<Usuario> usuarios = this.usuarioService.obtenerUsuarios();
		List<Usuario> usuariosNoEnGrupo = usuarios.stream()
                .filter(usuario -> usuariosDelGrupo.stream().noneMatch(usuariogrupo -> usuariogrupo.getId().equals(usuario.getId())))
                .toList();

        return usuariosNoEnGrupo;
	}
}
