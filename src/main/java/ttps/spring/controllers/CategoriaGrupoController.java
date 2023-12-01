package ttps.spring.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.models.CategoriaGrupo;
import ttps.spring.services.CategoriaGrupoService;

@RestController
@RequestMapping("/categoriagrupo")
public class CategoriaGrupoController {
	@Autowired
	private CategoriaGrupoService categoriaGrupoService;

	public CategoriaGrupoController(CategoriaGrupoService categoriaGrupoService) {
		super();
		this.categoriaGrupoService = categoriaGrupoService;
	}
    
	@GetMapping()
	public ArrayList<CategoriaGrupo> obtenerCategoriasGrupo(){
		return categoriaGrupoService.obtenerCategoriasGrupo();
	}
	
	
	@PostMapping
	public CategoriaGrupo guardarCategoriasGasto(@RequestBody CategoriaGrupo categoriaGrupo) {
		return this.categoriaGrupoService.guardarCategoriaGrupo(categoriaGrupo);
	}
    
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.categoriaGrupoService.eliminarCategoriaGrupo(id);
		if (ok) {
			return "Se eliminó el usuario con id" +id;
		}
		else {
			return "No se pudo eliminar el usuario con id" +id;
		}
		
	}
	

	
}
