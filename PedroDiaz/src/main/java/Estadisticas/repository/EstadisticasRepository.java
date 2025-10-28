package Estadisticas.repository;

import Estadisticas.Model.Estadisticas;
import Jugador.Model.Futbolista;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadisticasRepository extends JpaRepository<Estadisticas , Long> {
    Estadisticas findByFutbolista(Futbolista futbolista);
}
