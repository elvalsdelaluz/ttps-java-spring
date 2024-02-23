package ttps.spring.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.Grupo;
import ttps.spring.models.Pago;
import ttps.spring.models.Usuario;
import ttps.spring.repositories.PagoRepository;

@Service
public class PagoService {
	@Autowired
	PagoRepository pagoRepository;
	UsuarioService usuarioService;
	GrupoService grupoService;
	
	
	
	public PagoService(PagoRepository pagoRepository, UsuarioService usuarioService, GrupoService grupoService) {
		super();
		this.pagoRepository = pagoRepository;
		this.usuarioService = usuarioService;
		this.grupoService = grupoService;
	}

	public ArrayList<Pago> obtenerPagos(){
		return (ArrayList<Pago>)pagoRepository.findAll();
	}
	
	public Optional<Pago> obtenerPorId(Long id){
		return pagoRepository.findById(id);
	}

	public Pago guardarPago(Pago pago) {
		return pagoRepository.save(pago);
	}
	
	public boolean eliminarPago(Long id) {
		try {
			pagoRepository.deleteById(id);
			return true;
		}catch(Exception err) {
			return false;
		}
	}
	
	
	//ESTOS METODOS SE REPITEN EN LOS DIFERENTES SERVICES 
	public Grupo obtenerGrupo (Long id) {
		return this.grupoService.obtenerPorId(id).get();
	}
	
	public Usuario validarUsuario (Long id) {
	    return this.usuarioService.obtenerPorId(id).get();
	}
	

}
