package ttps.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.spring.models.Deuda;

public interface DeudaRepository  extends JpaRepository <Deuda, Long>{
	Optional<Deuda> findByGastoIdAndUsuarioId(Long idGasto, Long idUsuario);


}
