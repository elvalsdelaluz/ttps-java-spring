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

import ttps.spring.models.CategoriaGasto;
import ttps.spring.services.CategoriaGastoService;

@RestController
@RequestMapping("/categoriagasto")
public class CategoriaGastoController {
	@Autowired
	private CategoriaGastoService categoriaGastoService;

	public CategoriaGastoController(CategoriaGastoService categoriaGastoService) {
		super();
		this.categoriaGastoService = categoriaGastoService;
	}
	
	@GetMapping()
	public ArrayList<CategoriaGasto> obtenerCategoriasGasto(){
		return categoriaGastoService.obtenerCategoriasGasto();
	}
	
	
	@PostMapping
	public CategoriaGasto guardarCategoriasGasto(@RequestBody CategoriaGasto categoriaGasto) {
		return this.categoriaGastoService.guardarCategoriaGasto(categoriaGasto);
	}
    
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.categoriaGastoService.eliminarCategoriaGasto(id);
		if (ok) {
			return "Se eliminó el usuario con id" +id;
		}
		else {
			return "No se pudo eliminar el usuario con id" +id;
		}
		
	}
	

}
