package ttps.spring.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.Grupo;
import ttps.spring.repositories.GrupoRepository;

@Service
public class GrupoService {
	@Autowired
	GrupoRepository grupoRepository;

	public GrupoService(GrupoRepository grupoRepository) {
		super();
		this.grupoRepository = grupoRepository;
	}
	

	public ArrayList<Grupo> obtenerGrupos(){
		return (ArrayList<Grupo>)grupoRepository.findAll();
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
	

}
