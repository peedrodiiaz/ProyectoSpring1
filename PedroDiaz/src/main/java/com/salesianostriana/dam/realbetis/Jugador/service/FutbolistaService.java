package com.salesianostriana.dam.realbetis.Jugador.service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.realbetis.Estadisticas.Model.Estadisticas;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasJugador;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasPortero;
import com.salesianostriana.dam.realbetis.Estadisticas.service.EstadisticasService;
import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;
import com.salesianostriana.dam.realbetis.Jugador.Model.Jugador;
import com.salesianostriana.dam.realbetis.Jugador.Model.Portero;
import com.salesianostriana.dam.realbetis.Jugador.repository.FutbolistaRepository;
import com.salesianostriana.dam.realbetis.Jugador.repository.JugadorRepository;

import java.util.List;

@Service
public class FutbolistaService {
    private final JugadorRepository jugadorRepository;
    private final FutbolistaRepository futbolistaRepository;
    private final EstadisticasService estadisticasService;

    public FutbolistaService(JugadorRepository jugadorRepository,
                            FutbolistaRepository futbolistaRepository,
                            @Lazy EstadisticasService estadisticasService) {
        this.jugadorRepository = jugadorRepository;
        this.futbolistaRepository = futbolistaRepository;
        this.estadisticasService = estadisticasService;
    }

    public List<Futbolista> getAllFutbolistas() {
        return futbolistaRepository.findAll();
    }

    public Futbolista findFutbolistaById(Long id) {
        return futbolistaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Futbolista no encontrado"));
    }


    public Futbolista createFutbolista(Futbolista futbolista) {
        return futbolistaRepository.save(futbolista);
    }

    public Futbolista updateFutbolista(Long id, Futbolista futbolistaDetails) {
        Futbolista futbolista = findFutbolistaById(id);
        futbolista.setNombre(futbolistaDetails.getNombre());
        futbolista.setApellidos(futbolistaDetails.getApellidos());
        futbolista.setImgFutbolista(futbolistaDetails.getImgFutbolista());
        futbolista.setFechaNacimiento(futbolistaDetails.getFechaNacimiento());
        futbolista.setFechaInicioContrato(futbolistaDetails.getFechaInicioContrato());
        futbolista.setNacionalidad(futbolistaDetails.getNacionalidad());
        futbolista.setNumCamiseta(futbolistaDetails.getNumCamiseta());
        futbolista.setSalarioMensualBase(futbolistaDetails.getSalarioMensualBase());
        futbolista.setEquipo(futbolistaDetails.getEquipo());
        return jugadorRepository.save(futbolista);
    }

    public void deleteFutbolista(Long id) {
        
        futbolistaRepository.deleteById(id);

    }

    public List<Futbolista> findFutbolistaByEquipoId(Long equipoId) {
        return futbolistaRepository.findByEquipoId(equipoId);
    }



    public double calcularBonusSalario(Long id){
        int totalGoles = 0, totalAsistencias = 0;
        double extras=0.0, golesBonus=1000, asistenciasBonus=500, restarPorAmarilla=250, restarPorRoja=450;
        Futbolista futbolista = findFutbolistaById(id);
        int tarjetasAmarillas, tarjetasRojas;
        if (futbolista instanceof Jugador ){
            Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
            totalGoles = ((EstadisticasJugador)estadisticas).getGoles();
            totalAsistencias = ((EstadisticasJugador)estadisticas).getAsistencias();
            tarjetasAmarillas= ((EstadisticasJugador)estadisticas).getTarAmarilla();
            tarjetasRojas= ((EstadisticasJugador)estadisticas).getTarRoja();
             extras = (totalGoles * golesBonus) + (totalAsistencias * asistenciasBonus)-(tarjetasAmarillas*restarPorAmarilla)-(tarjetasRojas*restarPorRoja);
            return extras;

        }
        if (futbolista instanceof Portero ){
            int totalPorteriasACero = 0;
            double porteriasImbatidasBonus = 800;
            Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
            totalPorteriasACero = ((EstadisticasPortero)estadisticas).getPorteriasACero();
            extras = totalPorteriasACero * porteriasImbatidasBonus;
            return extras;
        }
        return extras;
    }
    public double calcularSalarioFutbolista(Long id){
        Futbolista  futbolista = findFutbolistaById(id);
        double extra=0.0;
        extra = calcularBonusSalario(id);

        return extra+futbolista.getSalarioMensualBase();
    }









}


