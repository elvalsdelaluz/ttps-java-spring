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

import ttps.spring.models.Pago;
import ttps.spring.services.PagoService;

@RestController
@RequestMapping("/pago")
public class PagoController {
	@Autowired
	private PagoService pagoService;

	public PagoController(PagoService pagoService) {
		super();
		this.pagoService = pagoService;
	}
	
	@GetMapping()
	public ArrayList<Pago> obtenerPagos(){
		return pagoService.obtenerPagos();
	}
	
	@PostMapping
	public Pago guardarPago(@RequestBody Pago pago) {
		return this.pagoService.guardarPago(pago);
	}
    
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.pagoService.eliminarPago(id);
		if (ok) {
			return "Se eliminó el usuario con id" +id;
		}
		else {
			return "No se pudo eliminar el usuario con id" +id;
		}
		
	}
	
    
}
