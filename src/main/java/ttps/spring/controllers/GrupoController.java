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

import ttps.spring.models.Grupo;
import ttps.spring.services.GrupoService;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
	@Autowired
	private GrupoService grupoService;

	public GrupoController(GrupoService grupoService) {
		super();
		this.grupoService = grupoService;
	}
	
	@GetMapping()
	public ArrayList<Grupo> obtenerGrupos(){
		return grupoService.obtenerGrupos();
	}
	
	@PostMapping
	public Grupo guardarGrupo(@RequestBody Grupo grupo) {
		return this.grupoService.guardarGrupo(grupo);
	}
    
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.grupoService.eliminarGrupo(id);
		if (ok) {
			return "Se eliminó el usuario con id" +id;
		}
		else {
			return "No se pudo eliminar el usuario con id" +id;
		}
		
	}

}
