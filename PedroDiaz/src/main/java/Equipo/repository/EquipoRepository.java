package Equipo.repository;

import Equipo.Model.Equipo;
import Jugador.dto.JugadorDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo,Long> {
}
