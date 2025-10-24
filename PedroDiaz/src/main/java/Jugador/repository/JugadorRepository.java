package Jugador.repository;

import Jugador.Model.Futbolista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Futbolista, Long>{

    List<Futbolista> findByEquipoId(Long equipoId);

    List<Futbolista> findByEdad(Long id);

    List<Futbolista> findByPosicion(Long id);
}
