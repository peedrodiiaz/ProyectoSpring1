package Estadisticas.repository;

import Estadisticas.Model.Estadisticas;
import Jugador.Model.Futbolista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadisticasRepository extends JpaRepository<Estadisticas , Long> {
    List<Estadisticas> findByJugador(Futbolista futbolista);
}
