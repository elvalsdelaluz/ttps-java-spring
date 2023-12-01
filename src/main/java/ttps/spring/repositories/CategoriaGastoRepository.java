package ttps.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.spring.models.CategoriaGasto;

public interface CategoriaGastoRepository extends JpaRepository <CategoriaGasto, Long> {

}
