package Jugador.repository;

import Jugador.Model.Futbolista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FutbolistaRepository extends JpaRepository<Futbolista,Long> {

    List<Futbolista> findByEquipoId(Long equipoId);

}
