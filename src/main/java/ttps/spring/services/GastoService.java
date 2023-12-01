package ttps.spring.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.Gasto;
import ttps.spring.repositories.GastoRepository;

@Service
public class GastoService {
	@Autowired
	GastoRepository gastoRepository;

	public GastoService(GastoRepository gastoRepository) {
		super();
		this.gastoRepository = gastoRepository;
	}
	
	public ArrayList<Gasto> obtenerGastos(){
		return (ArrayList<Gasto>)gastoRepository.findAll();
	}
	
	public Optional<Gasto> obtenerPorId(Long id){
		return gastoRepository.findById(id);
	}
	
	public Gasto guardarGasto(Gasto gasto) {
		return gastoRepository.save(gasto);
	}
	
	public boolean eliminarGasto(Long id) {
		try {
			gastoRepository.deleteById(id);
			return true;
		}catch(Exception err) {
			return false;
		}
	}

}
