package ttps.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.models.Deuda;
import ttps.spring.repositories.DeudaRepository;

@Service
public class DeudaService {
	@Autowired
	DeudaRepository deudaRepository;
	
	public DeudaService(DeudaRepository gastoRepository) {
		super();
		this.deudaRepository = gastoRepository;
	}
	
	public Deuda guardarDeuda(Deuda deuda) {
		return deudaRepository.save(deuda);
	}
	
	
	

}
