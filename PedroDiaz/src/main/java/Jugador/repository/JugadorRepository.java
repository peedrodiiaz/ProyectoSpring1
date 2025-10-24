package Jugador.repository;

import Jugador.Model.Futbolista;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JugadorRepository extends JpaRepository<Futbolista, Long>{

}
