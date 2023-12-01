package ttps.spring.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.CategoriaGasto;
import ttps.spring.repositories.CategoriaGastoRepository;

@Service
public class CategoriaGastoService {
	@Autowired
	CategoriaGastoRepository categoriaGastoRepository;

	public CategoriaGastoService(CategoriaGastoRepository categoriaGastoRepository) {
		super();
		this.categoriaGastoRepository = categoriaGastoRepository;
	}
	
	public ArrayList<CategoriaGasto> obtenerCategoriasGasto(){
		return (ArrayList<CategoriaGasto>)categoriaGastoRepository.findAll();
	}
	
	public CategoriaGasto guardarCategoriaGasto(CategoriaGasto categoriaGasto) {
		return categoriaGastoRepository.save(categoriaGasto);
	}
	
	public boolean eliminarCategoriaGasto(Long id) {
		try {
			categoriaGastoRepository.deleteById(id);
			return true;
		}catch(Exception err) {
			return false;
		}
	}
	


}
