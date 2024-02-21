package ttps.spring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dto.ResumenRequestDTO;
import ttps.spring.models.Deuda;
import ttps.spring.models.Gasto;
import ttps.spring.models.Grupo;
import ttps.spring.models.Usuario;
import ttps.spring.repositories.GastoRepository;

@Service
public class GastoService {
	@Autowired
	GastoRepository gastoRepository;
	UsuarioService usuarioService;
	GrupoService grupoService;
	private DeudaService deudaService;

	public GastoService(GastoRepository gastoRepository, UsuarioService usuarioService, GrupoService grupoService,  DeudaService deudaService) {
		super();
		this.gastoRepository = gastoRepository;
		this.usuarioService = usuarioService;
		this.grupoService = grupoService;
		this.deudaService = deudaService;
	}
	
	public ArrayList<Gasto> obtenerGastos(){
		return (ArrayList<Gasto>)gastoRepository.findAll();
	}
	
	public List<Gasto> obtenerGastosDeUnGrupo(Long idGrupo){
		//Obtengo el grupo y le pido sus gastos
		Grupo grupo = grupoService.obtenerPorId(idGrupo).get();
		return grupo.getGastos();
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
	
	public Usuario validarUsuario (Long id) {
	    return this.usuarioService.obtenerPorId(id).get();
	}
    
	public Grupo obtenerGrupo (Long id) {
		return this.grupoService.obtenerPorId(id).get();
	}
	
	//Lógica de negocio
	public double calcularMontoPrestado(Long categoria, int cantMiembros, double monto) {
		if (cantMiembros == 1) {
			return 0;
		}
		else {
			//Division por partes iguales
			return monto/cantMiembros;
		}
	}
	
	public Deuda guardarDeuda(ResumenRequestDTO resumen, Gasto gasto) {
		Deuda deuda = new Deuda();
		deuda.setMonto(resumen.getDeuda());
		deuda.setUsuario(validarUsuario(resumen.getUser_id()));
		deuda.setGasto(gasto);
		return this.deudaService.guardarDeuda(deuda);
	}
	
	
	private HashMap<String, HashMap<String, Double>> inicializarHash(List<Usuario> miembros) {
		HashMap<String, HashMap<String, Double>> hash = new HashMap<String, HashMap<String, Double>> ();
		
		for (Usuario miembro : miembros) {
            HashMap<String, Double> innerMap = new HashMap<>();
            String email = miembro.getEmail();
            
            // Agregar el email como clave en el mapa exterior
            hash.put(email, innerMap);
            
            // Iterar sobre los miembros nuevamente para agregar el email y 0 para los demás usuarios
            for (Usuario otroMiembro : miembros) {
                // Si el usuario no es el mismo que el miembro actual, agregarlo con valor 0
                if (!otroMiembro.getEmail().equals(email)) {
                    innerMap.put(otroMiembro.getEmail(), 0.0);
                }
            }
        }
		System.out.println(hash);
        return hash;
    }
	
	private HashMap<String, HashMap<String, Double>>  cargarDeudasAlHash(List<Gasto> gastos,HashMap<String, HashMap<String, Double>> hash ){
		System.out.println(hash);
		// Incrementar el valor de un elemento específico en un HashMap interno

		for (Gasto gasto : gastos) {
            //Me quedo con el email de usuario que pago el gasto
			String emailExterno = gasto.getUsuario().getEmail();
			List<Deuda> deudas = gasto.getParticipantes();
			
			//Itero la lista de participantes del gasto y actualizo el hash
			for (Deuda deuda: deudas) {
				String emailAModificar = deuda.getUsuario().getEmail();
				// Si el usuario no es el mismo que el miembro actual, agregarlo con valor 0
                if (!deuda.getUsuario().getEmail().equals(emailExterno)) {
                	Double valorActual = hash.get(emailExterno).get(emailAModificar);
                	hash.get(emailExterno).put(emailAModificar, valorActual + deuda.getMonto());
                }
			}
		}	
		return hash;
	}
	
	private HashMap<String, HashMap<String, Double>>  simplificarDeudas(HashMap<String, HashMap<String, Double>> hash ){
		for (String emailClave: hash.keySet()) {
			HashMap<String, Double> hashInterno = hash.get(emailClave);
			
			for (String emailClaveInterno: hashInterno.keySet()) {
				Double valorActual = hash.get(emailClave).get(emailClaveInterno);
				if (valorActual > 0) {
					//Recupero el valor hash.get(emailClaveInterno).get(emailClave)
					Double valorActualInverso = hash.get(emailClaveInterno).get(emailClave);
					if (valorActualInverso > 0) {
						if (valorActualInverso >= valorActual) {
							//Le resto a la deuda la deudaInversa
		                	hash.get(emailClaveInterno).put(emailClave, valorActualInverso - valorActual);
		                	//Como es mayor reseteo a 0.0
		                	hash.get(emailClave).put(emailClaveInterno, 0.0);
						}
						else {
							hash.get(emailClave).put(emailClaveInterno, valorActual - valorActualInverso);
							hash.get(emailClaveInterno).put(emailClave, 0.0);
						}
						
					}
				}
			}
		}
		System.out.println("----DEUDAS SIMPLIFICADAS----");
		System.out.println(hash);
		
		return hash;
	}
		
	
	
    public String obtenerSaldosDeUnGrupo(Long id_grupo) {
    	//Necesito los miembros del grupo 
    	Grupo grupo = grupoService.obtenerPorId(id_grupo).get();
    	List<Usuario> miembros = grupo.getMiembros();
    	
    	//Creo el hashMap
    	HashMap<String, HashMap<String, Double>> deudas = inicializarHash(miembros);
    	
    	//Cargo las deudas al hash
    	List<Gasto> gastos = grupo.getGastos();
    	deudas = cargarDeudasAlHash(gastos, deudas);
    	System.out.println(deudas);
    	simplificarDeudas(deudas);
    	System.out.println("----DEUDAS SIMPLIFICADAS----");
    	System.out.println(deudas);
    	return "holi";
    }
    
	
}
