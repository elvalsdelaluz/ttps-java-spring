package ttps.spring.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttps.spring.models.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	 public abstract Optional<Usuario> findByEmail(String email);

	public abstract Optional<Usuario> findById(Long id);

}
