package Estadisticas.service;


import Equipo.repository.EquipoRepository;
import Estadisticas.Model.Estadisticas;
import Estadisticas.repository.EstadisticasRepository;
import Jugador.Model.Jugador;
import Jugador.repository.JugadorRepository;
import Jugador.service.JugadorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadisticasService {

    private final JugadorService jugadorService;

    private final EstadisticasRepository estadisticasRepository;

    public List<Estadisticas> getAllEstadisticas() {
        return estadisticasRepository.findAll();
    }

    public Estadisticas findById(Long id) {
        return estadisticasRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Estadística no encontrada"));
    }

    public Estadisticas createEstadistica(Estadisticas estadistica) {
        return estadisticasRepository.save(estadistica);
    }

    public Estadisticas updateEstadistica(Long id, Estadisticas estadisticaDetails) {
        Estadisticas estadistica = findById(id);

        estadistica.setMinJugados(estadisticaDetails.getMinJugados());
        estadistica.setGoles(estadisticaDetails.getGoles());
        estadistica.setAsistencias(estadisticaDetails.getAsistencias());
        estadistica.setTarAmarilla(estadisticaDetails.getTarAmarilla());
        estadistica.setTarRoja(estadisticaDetails.getTarRoja());
        estadistica.setCalificacion(estadisticaDetails.getCalificacion());
        estadistica.setJugador(estadisticaDetails.getJugador());

        return estadisticasRepository.save(estadistica);
    }

    public void deleteEstadistica(Long id) {
        estadisticasRepository.deleteById(id);
    }

    public List<Estadisticas> getEstadisticasByJugador(Long jugadorId) {
        Jugador jugador = jugadorService.findJugadorById(jugadorId);
        return estadisticasRepository.findByJugador(jugador);
    }


}
