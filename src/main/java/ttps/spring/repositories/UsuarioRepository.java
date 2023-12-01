package ttps.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttps.spring.models.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
