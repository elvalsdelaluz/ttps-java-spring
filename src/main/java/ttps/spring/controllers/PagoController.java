package ttps.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.dto.PagoDTO;
import ttps.spring.models.Gasto;
import ttps.spring.models.Grupo;
import ttps.spring.models.Pago;
import ttps.spring.models.Usuario;
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
	
	@PostMapping("/{id_grupo}")
	public ResponseEntity<Void> guardarPagoDeUnUsuario(@PathVariable("id_grupo") Long id_grupo,@RequestBody PagoDTO pago){
		//Valido que el usuario exista
		  Usuario user_deudor = this.pagoService.validarUsuario(pago.getId_deudor());
		  if (user_deudor == null) {
			  System.out.println("El usuario no existe" );
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		//Valido que el usuario exista
		  Usuario user_acreedor = this.pagoService.validarUsuario(pago.getId_acreedor());
		  if (user_acreedor == null) {
			  System.out.println("El usuario no existe" );
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		//Recupero al grupo y le agrego el gasto
		  Grupo grupo = this.pagoService.obtenerGrupo(id_grupo);
		  if (grupo == null) {
			  System.out.println("El grupo no existe" );
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		  }
		  
		  Pago auxPago = new Pago();
		  auxPago.setGrupo(grupo);
		  auxPago.setUsuario_acreedor(user_acreedor);
		  auxPago.setUsuario_deudor(user_deudor);
		  auxPago.setMonto(pago.getMonto());
		  this.pagoService.guardarPago(auxPago);
	    return new ResponseEntity<>(HttpStatus.OK);
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
