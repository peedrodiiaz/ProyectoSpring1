package com.salesianostriana.dam.realbetis.Estadisticas.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.Estadisticas;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasJugador;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasPortero;
import com.salesianostriana.dam.realbetis.Estadisticas.dto.EstadisticaResumenDto;
import com.salesianostriana.dam.realbetis.Estadisticas.repository.EstadisticasRepository;
import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;
import com.salesianostriana.dam.realbetis.Jugador.service.FutbolistaService;

import java.util.Collections;
import java.util.List;

@Service
public class EstadisticasService {

    private final FutbolistaService futbolistaService;
    private final EstadisticasRepository estadisticasRepository;

    public EstadisticasService(@Lazy FutbolistaService futbolistaService,
                                EstadisticasRepository estadisticasRepository) {
        this.futbolistaService = futbolistaService;
        this.estadisticasRepository = estadisticasRepository;
    }

    public List<Estadisticas> getAllEstadisticas() {
        return estadisticasRepository.findAll();
    }

    public Estadisticas findById(Long id) {
        return estadisticasRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Estadística no encontrada"));
    }



    public Estadisticas updateEstadistica(Long id, Estadisticas estadisticaDetails) {
        Estadisticas estadistica = findById(id);

        if (estadistica instanceof  EstadisticasJugador && estadisticaDetails instanceof EstadisticasJugador) {
            ((EstadisticasJugador) estadistica).setMinJugados(estadisticaDetails.getMinJugados());
            ((EstadisticasJugador) estadistica).setAsistencias(((EstadisticasJugador) estadisticaDetails).getAsistencias());
            ((EstadisticasJugador) estadistica).setTarAmarilla(estadisticaDetails.getTarAmarilla());
            ((EstadisticasJugador) estadistica).setTarRoja(estadisticaDetails.getTarRoja());
            ((EstadisticasJugador) estadistica).setCalificacion(estadisticaDetails.getCalificacion());
            ((EstadisticasJugador) estadistica).setGoles(((EstadisticasJugador) estadisticaDetails).getGoles());

        }
        if (estadistica instanceof EstadisticasPortero && estadisticaDetails instanceof EstadisticasPortero) {
            ((EstadisticasPortero) estadistica).setMinJugados(estadisticaDetails.getMinJugados());
            ((EstadisticasPortero) estadistica).setTarAmarilla(estadisticaDetails.getTarAmarilla());
            ((EstadisticasPortero) estadistica).setTarRoja(estadisticaDetails.getTarRoja());
            ((EstadisticasPortero) estadistica).setCalificacion(estadisticaDetails.getCalificacion());
            ((EstadisticasPortero) estadistica).setPorteriasACero(((EstadisticasPortero) estadisticaDetails).getPorteriasACero());
            ((EstadisticasPortero) estadistica).setParadas(((EstadisticasPortero) estadisticaDetails).getParadas());
        }



        return estadisticasRepository.save(estadistica);
    }

    public void deleteEstadistica(Long id) {
        estadisticasRepository.deleteById(id);
    }

    public Estadisticas getEstadisticasByFutbolista(Long futbolistaId) {
        Futbolista futbolista = futbolistaService.findFutbolistaById(futbolistaId);
        return estadisticasRepository.findByFutbolista(futbolista);
    }

    public Estadisticas saveEstadistica(Estadisticas estadisticas) {
        return estadisticasRepository.save(estadisticas);
    }


    public EstadisticaResumenDto getResumenEstadisticasEquipo(Long equipoId) {
        List<Futbolista> futbolistas = futbolistaService.findFutbolistaByEquipoId(equipoId);

        int totalGoles = 0, totalAsistencias = 0, totalTarjetasAmarillas = 0, totalTarjetasRojas = 0, totalPartidos = 0;
        int totalPorteriasACero = 0, totalParadas = 0;
        double totalMinJugados = 0.0, totalCalificacion = 0.0;

        for (Futbolista futbolista : futbolistas) {
            List<Estadisticas> estadisticasList = Collections.singletonList(estadisticasRepository.findByFutbolista(futbolista));

            for (Estadisticas estadistica : estadisticasList) {
                totalMinJugados += estadistica.getMinJugados();
                totalTarjetasAmarillas += estadistica.getTarAmarilla();
                totalTarjetasRojas += estadistica.getTarRoja();
                totalCalificacion += estadistica.getCalificacion();
                totalPartidos++;

                if (estadistica instanceof EstadisticasJugador ej) {
                    totalGoles += ej.getGoles();
                    totalAsistencias += ej.getAsistencias();
                }

                if (estadistica instanceof EstadisticasPortero ep) {
                    totalPorteriasACero += ep.getPorteriasACero();
                    totalParadas += ep.getParadas();
                }
            }
        }

        double mediaCalificacion = totalPartidos > 0 ? totalCalificacion / totalPartidos : 0.0;

        return EstadisticaResumenDto.builder()
                .equipoId(equipoId)
                .totalGoles(totalGoles)
                .totalAsistencias(totalAsistencias)
                .totalTarAmarillas(totalTarjetasAmarillas)
                .totalTarRojas(totalTarjetasRojas)
                .totalMinJugados(totalMinJugados)
                .mediaCalificacion(mediaCalificacion)
                .totalPorteriasACero(totalPorteriasACero)
                .totalParadas(totalParadas)
                .build();
    }
    }










