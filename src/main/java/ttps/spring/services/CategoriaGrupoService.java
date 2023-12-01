package ttps.spring.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.CategoriaGrupo;
import ttps.spring.repositories.CategoriaGrupoRepository;

@Service
public class CategoriaGrupoService {
	@Autowired
	CategoriaGrupoRepository categoriaGrupoRepository;

	public CategoriaGrupoService(CategoriaGrupoRepository categoriaGrupoRepository) {
		super();
		this.categoriaGrupoRepository = categoriaGrupoRepository;
	}
    
	public ArrayList<CategoriaGrupo> obtenerCategoriasGrupo(){
		return (ArrayList<CategoriaGrupo>)categoriaGrupoRepository.findAll();
	}
	
	public CategoriaGrupo guardarCategoriaGrupo(CategoriaGrupo categoriaGrupo) {
		return categoriaGrupoRepository.save(categoriaGrupo);
	}
	
	public boolean eliminarCategoriaGrupo(Long id) {
		try {
			categoriaGrupoRepository.deleteById(id);
			return true;
		}catch(Exception err) {
			return false;
		}
	}
	

}
