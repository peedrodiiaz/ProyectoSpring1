package Jugador.service;

import Jugador.Model.Jugador;
import Jugador.repository.JugadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JugadorService {
    private final JugadorRepository jugadorRepository;

    public List<Jugador> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    public Jugador findJugadorById(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Jugador no encontrado"));
    }


    public Jugador createJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public Jugador updateJugador(Long id, Jugador jugadorDetails) {
        Jugador jugador = findJugadorById(id);
        jugador.setNombre(jugadorDetails.getNombre());
        jugador.setApellidos(jugadorDetails.getApellidos());
        jugador.setImgJugador(jugadorDetails.getImgJugador());
        jugador.setFechaNacimiento(jugadorDetails.getFechaNacimiento());
        jugador.setFechaInicioContrato(jugadorDetails.getFechaInicioContrato());
        jugador.setNacionalidad(jugadorDetails.getNacionalidad());
        jugador.setNumCamiseta(jugadorDetails.getNumCamiseta());
        jugador.setSalarioMensualBase(jugadorDetails.getSalarioMensualBase());
        jugador.setEquipo(jugadorDetails.getEquipo());
        return jugadorRepository.save(jugador);
    }

    public void deleteJugador(Long id) {
        jugadorRepository.deleteById(id);
    }

    public List<Jugador> findJugadoresByEquipo(Long equipoId) {
        return jugadorRepository.findByEquipoId(equipoId);
    }
}
