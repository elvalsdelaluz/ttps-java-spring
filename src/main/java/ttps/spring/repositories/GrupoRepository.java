package ttps.spring.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ttps.spring.models.Grupo;

public interface GrupoRepository extends JpaRepository <Grupo, Long> {
}
