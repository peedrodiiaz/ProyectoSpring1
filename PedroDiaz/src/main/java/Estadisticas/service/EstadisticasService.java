package Estadisticas.service;


import Equipo.repository.EquipoRepository;
import Estadisticas.Model.Estadisticas;
import Estadisticas.dto.EstadisticaResumenDto;
import Estadisticas.dto.EstadisticasDto;
import Estadisticas.repository.EstadisticasRepository;
import Jugador.Model.Futbolista;
import Jugador.repository.JugadorRepository;
import Jugador.service.FutbolistaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadisticasService {

    private final FutbolistaService futbolistaService;
    private final  JugadorRepository jugadorRepository;

    private final EstadisticasRepository estadisticasRepository;
    private final EquipoRepository equipoRepository;

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
        estadistica.setFutbolista(estadisticaDetails.getFutbolista());

        return estadisticasRepository.save(estadistica);
    }

    public void deleteEstadistica(Long id) {
        estadisticasRepository.deleteById(id);
    }

    public List<EstadisticasDto> getEstadisticasByJugador(Long jugadorId) {
        Futbolista futbolista = futbolistaService.findFutbolistaById(jugadorId);
        return estadisticasRepository.findByFutbolista(futbolista)
                .stream()
                .map(Estadisticas::EntityconverToDto)
                .collect(Collectors.toList());
    }


    public EstadisticaResumenDto getResumenEstadisticasEquipo(Long equipoId) {
        List <Futbolista> jugadores = futbolistaService.findFutbolistaByEquipoId(equipoId);
        int totalGoles = 0, totalAsistencias = 0, totalTarjetasAmarillas = 0, totalTarjetasRojas = 0,totalPartidos = 0;
        double totalMinJugados = 0.0, totalCalificacion = 0.0, mediaCalificacion = 0.0;
        for (Futbolista futbolista : jugadores){
            List <Estadisticas> estadisticasJugador = estadisticasRepository.findByFutbolista(futbolista);
            for (Estadisticas estadisticas : estadisticasJugador){
                totalGoles += estadisticas.getGoles();
                totalAsistencias += estadisticas.getAsistencias();
                totalTarjetasAmarillas += estadisticas.getTarAmarilla();
                totalTarjetasRojas += estadisticas.getTarRoja();
                totalMinJugados += estadisticas.getMinJugados();
                totalCalificacion += estadisticas.getCalificacion();
                totalPartidos ++;
        }}
        mediaCalificacion= totalPartidos > 0 ? totalCalificacion / totalPartidos : 0.0;

        return EstadisticaResumenDto.builder()
                .equipoId(equipoId)
                .totalGoles(totalGoles)
                .totalAsistencias(totalAsistencias)
                .totalTarAmarillas(totalTarjetasAmarillas)
                .totalTarRojas(totalTarjetasRojas)
                .totalMinJugados(totalMinJugados)
                .mediaCalificacion(mediaCalificacion)
                .build();

    }






}
