package ttps.spring.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.Pago;
import ttps.spring.repositories.PagoRepository;

@Service
public class PagoService {
	@Autowired
	PagoRepository pagoRepository;
	
	
	
	public PagoService(PagoRepository pagoRepository) {
		super();
		this.pagoRepository = pagoRepository;
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
	

}
