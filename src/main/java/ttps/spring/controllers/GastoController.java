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

import ttps.spring.models.Gasto;
import ttps.spring.services.GastoService;

@RestController
@RequestMapping("/gasto")
public class GastoController {
	@Autowired
	private GastoService gastoService;

	public GastoController(GastoService gastoService) {
		super();
		this.gastoService = gastoService;
	}
	
	@GetMapping()
	public ArrayList<Gasto> obtenerGastos(){
		return gastoService.obtenerGastos();
	}
	
	
	@PostMapping
	public Gasto guardarGasto(@RequestBody Gasto gasto) {
		return this.gastoService.guardarGasto(gasto);
	}
    
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.gastoService.eliminarGasto(id);
		if (ok) {
			return "Se eliminó el usuario con id" +id;
		}
		else {
			return "No se pudo eliminar el usuario con id" +id;
		}
		
	}
	

	

}
