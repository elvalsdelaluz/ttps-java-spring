package ttps.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.dto.GastoDTO;
import ttps.spring.dto.GastoRequestDTO;
import ttps.spring.dto.ResumenGastoRequestDTO;
import ttps.spring.dto.ResumenRequestDTO;
import ttps.spring.dto.SaldoDTO;
import ttps.spring.dto.UsuarioDTO;
import ttps.spring.models.Deuda;
import ttps.spring.models.Gasto;
import ttps.spring.models.Grupo;
import ttps.spring.models.Usuario;
import ttps.spring.services.DeudaService;
import ttps.spring.services.GastoService;
import ttps.spring.services.UsuarioService;

@RestController
@RequestMapping("/gasto")
public class GastoController {
	@Autowired
	private GastoService gastoService;
	

	public GastoController(GastoService gastoService) {
		super();
		this.gastoService = gastoService;
	}
	
	public void guardarDeudas(List<ResumenRequestDTO> deudas, Gasto gasto) {
		for (ResumenRequestDTO resumen : deudas) {
		    this.gastoService.guardarDeuda(resumen, gasto);
		}
	}
	
	@PostMapping(path="/resumen/{id_grupo}")
	public ResponseEntity<Gasto> guardarResumenGasto(@PathVariable("id_grupo") Long id_grupo, @RequestBody ResumenGastoRequestDTO gasto) {
		System.out.println(gasto.getMonto());
		List<ResumenRequestDTO> listaDeGasto = gasto.getInterests();
		for (ResumenRequestDTO resumen : listaDeGasto) {
		    System.out.println(resumen.getUser_id()); 
		    System.out.println(resumen.getDeuda());
		}
		
		 System.out.println("----------AGREGANDO GASTO-------------");
		 //Valido que el usuario exista
		  Usuario user = this.gastoService.validarUsuario(gasto.getMiembro());
		  if (user == null) {
			  System.out.println("El usuario no existe" );
			  return new ResponseEntity<Gasto>(HttpStatus.NOT_FOUND);
		  }
		  //Recupero al grupo y le agrego el gasto
		  Grupo grupo = this.gastoService.obtenerGrupo(id_grupo);
		  if (grupo == null) {
			  System.out.println("El grupo no existe" );
			  return new ResponseEntity<Gasto>(HttpStatus.NOT_FOUND); 
		  }
		  Gasto auxGasto = new Gasto ();
		  auxGasto.setMonto(gasto.getMonto());
		  auxGasto.setGrupo(grupo);
		  //Guardo el usuario que hizo el gasto
		  auxGasto.setUsuario(user);
		  //Recupero la categoria
		  //auxGasto.setCategoria()
		  auxGasto.setFormaDivision(gasto.getFormapago());
		  auxGasto.setFechaCreacion(new java.util.Date());
		  Gasto nuevoGasto = this.gastoService.guardarGasto(auxGasto);
		  guardarDeudas(gasto.getInterests(), nuevoGasto);
		  return new ResponseEntity<Gasto>(HttpStatus.OK);
	  
	}
	
	@GetMapping(path="/editar/{id_gasto}")
	public Gasto obtenerGasto(@PathVariable("id_gasto") Long id_gasto){
		//Este método retorna un gasto
		return gastoService.obtenerPorId(id_gasto);
	}
	
	
//	@PutMapping(path="/editar/{id_gasto}")
//	public Gasto editarGasto2(@PathVariable("id_gasto") Long id_gasto, @RequestBody Gasto gasto){ //Este no anda borrar
//		//Este metodo modifica un gasto
//		return gastoService.guardarGasto(gasto);
//	}

	@PutMapping(path="/editar/{id_gasto}")
	public Gasto editarGasto(@PathVariable("id_gasto") Long id_gasto, @RequestBody ResumenGastoRequestDTO gasto_editado){
		//Obtengo el gasto que hay que editar
		Gasto gasto = this.gastoService.obtenerPorId(id_gasto);
		if (gasto != null) {
		  	if (gasto_editado.getMonto() != gasto.getMonto()) {
		  		gasto.setMonto(gasto_editado.getMonto());
		  		//Cambiar el monto de los deudores
		  		this.gastoService.modificarDeudasDeUnGasto(gasto_editado.getInterests(), gasto);
		  	}
		  	if (String.valueOf(gasto_editado.getFormapago()) != gasto.getFormaDivision()) {
		        gasto.setFormaDivision(String.valueOf(gasto_editado.getFormapago()));
		        //En este caso tambien tengo que cambiar los montos de los deudores por las dudas
		        this.gastoService.modificarDeudasDeUnGasto(gasto_editado.getInterests(), gasto);
		  		
		  	}
		  	if (gasto_editado.getMiembro() != gasto.getUsuario().getId()) {
		  		//Recupero al nuevo usuario
		  		Usuario nuevo_usuario = this.gastoService.validarUsuario(gasto_editado.getMiembro());
		  		if (nuevo_usuario != null) {
		  			gasto.setUsuario(nuevo_usuario);
		  		}	
		  	}
		    gastoService.guardarGasto(gasto);
		}
		
		//Este metodo modifica un gasto
		return null;
	}
	
	@GetMapping(path="/{id_grupo}")
	//Mostrar una tabla con los grupos y cuando se ingrese ver gastos se invoque a este enpoint
	public  ResponseEntity<List<Gasto>> obtenerGastosDeUnGrupo(@PathVariable("id_grupo") Long id_grupo){
		List <Gasto> gastos = gastoService.obtenerGastosDeUnGrupo(id_grupo);
		return new ResponseEntity<>(gastos, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id_grupo}/{id_usuario}")
	public  ResponseEntity<List<Gasto>> obtenerGastosDeUnUsuarioEnUnGrupo(@PathVariable("id_grupo") Long id_grupo, @PathVariable("id_usuario") Long id_usuario){
		//Este método retorna los gastos/deudas de un usuario particular en un grupo
        		
		List <Gasto> gastos = gastoService.obtenerGastosDeUnGrupo(id_grupo);
		return new ResponseEntity<>(gastos, HttpStatus.OK);
	}
	
//	@PostMapping(path="/{id_grupo}")
//	public ResponseEntity<Gasto> guardarGasto(@PathVariable("id_grupo") Long id_grupo, @RequestBody GastoRequestDTO gasto) {
//		  System.out.println("----------AGREGANDO GASTO-------------");
//		 //Valido que el usuario exista
//		  Usuario user = this.gastoService.validarUsuario(gasto.getIdUsuario());
//		  if (user == null) {
//			  System.out.println("El usuario no existe" );
//			  return new ResponseEntity<Gasto>(HttpStatus.NOT_FOUND);
//		  }
//		  //Recupero al grupo y le agrego el gasto
//		  Grupo grupo = this.gastoService.obtenerGrupo(id_grupo);
//		  if (grupo == null) {
//			  System.out.println("El grupo no existe" );
//			  return new ResponseEntity<Gasto>(HttpStatus.NOT_FOUND); 
//		  }
//		  Gasto auxGasto = new Gasto ();
//		  auxGasto.setMonto(gasto.getMonto());
//		  double montoPrestado = this.gastoService.calcularMontoPrestado(gasto.getFormaPago(), grupo.getMiembros().size(), gasto.getMonto());
//		  auxGasto.setMontoPrestado(montoPrestado);
//		  auxGasto.setGrupo(grupo);
//		  //Guardo el usuario que hizo el gasto
//		  auxGasto.setUsuario(user);
//		  //Recupero la categoria
//		  //auxGasto.setCategoria()
//		  this.gastoService.guardarGasto(auxGasto);
//		  return new ResponseEntity<Gasto>(HttpStatus.OK);
//		
//	}
	
	
	
	
    
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.gastoService.eliminarGasto(id);
		if (ok) {
			return "Se eliminó el gasto con id" +id;
		}
		else {
			return "No se pudo eliminar el gasto con id" +id;
		}
		
	}
	
	
	@GetMapping(path="/saldos/{id_grupo}")
	public ResponseEntity<List<SaldoDTO>>  saldosDeUnGrupo(@PathVariable("id_grupo") Long id_grupo) {
		return new ResponseEntity<>(this.gastoService.obtenerSaldosDeUnGrupo(id_grupo), HttpStatus.OK);
		
	}

	

}
