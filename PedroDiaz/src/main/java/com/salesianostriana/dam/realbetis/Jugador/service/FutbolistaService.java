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
    private final FutbolistaRepository futbolistaRepository;
    private final EstadisticasService estadisticasService;

    public FutbolistaService(JugadorRepository jugadorRepository,
                            FutbolistaRepository futbolistaRepository,
                            @Lazy EstadisticasService estadisticasService) {
        
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

    public void deleteFutbolista(Long id) {
        
        futbolistaRepository.deleteById(id);

    }

    public List<Futbolista> findFutbolistaByEquipoId(Long equipoId) {
        return futbolistaRepository.findByEquipoId(equipoId);
    }
    public Futbolista saveFutbolista(Futbolista f) {
        return futbolistaRepository.save(f);
        }

    //EDITAR
    private void actualizarEstadisticasJugador(Futbolista existente, Futbolista nuevo) {
    Estadisticas statsExistentes = existente.getEstadisticas();
    Estadisticas statsNuevas = nuevo.getEstadisticas();
    
    if (statsExistentes instanceof EstadisticasJugador && 
        statsNuevas instanceof EstadisticasJugador) {
        
        EstadisticasJugador ej = (EstadisticasJugador) statsExistentes;
        EstadisticasJugador ejNuevas = (EstadisticasJugador) statsNuevas;
        
        ej.setMinJugados(ejNuevas.getMinJugados());
        ej.setGoles(ejNuevas.getGoles());
        ej.setAsistencias(ejNuevas.getAsistencias());
        ej.setTarAmarilla(ejNuevas.getTarAmarilla());
        ej.setTarRoja(ejNuevas.getTarRoja());
        ej.setCalificacion(ejNuevas.getCalificacion());
    }
}
    private void actualizarEstadisticasPortero(Futbolista existente, Futbolista nuevo) {
        Estadisticas statsExistentes = existente.getEstadisticas();
        Estadisticas statsNuevas = nuevo.getEstadisticas();
        
        if (statsExistentes instanceof EstadisticasPortero && 
            statsNuevas instanceof EstadisticasPortero) {
            
            EstadisticasPortero ep = (EstadisticasPortero) statsExistentes;
            EstadisticasPortero epNuevas = (EstadisticasPortero) statsNuevas;
            
            ep.setMinJugados(epNuevas.getMinJugados());
            ep.setParadas(epNuevas.getParadas());
            ep.setPorteriasACero(epNuevas.getPorteriasACero());
            ep.setTarAmarilla(epNuevas.getTarAmarilla());
            ep.setTarRoja(epNuevas.getTarRoja());
            ep.setCalificacion(epNuevas.getCalificacion());
        }
}

    

    public Futbolista updateFutbolista(Long id, Futbolista futbolista) {
        Futbolista f = findFutbolistaById(id);

        // Actualizar datos básicos
        f.setNombre(futbolista.getNombre());
        f.setApellidos(futbolista.getApellidos());
        f.setNumCamiseta(futbolista.getNumCamiseta());
        f.setFechaNacimiento(futbolista.getFechaNacimiento());
        f.setFechaInicioContrato(futbolista.getFechaInicioContrato());
        f.setImgFutbolista(futbolista.getImgFutbolista());
        f.setSalarioMensualBase(futbolista.getSalarioMensualBase());
        f.setPiernaBuena(futbolista.getPiernaBuena());
        f.setEquipo(futbolista.getEquipo());

        // Actualizar según tipo específico
        if (futbolista instanceof Jugador && f instanceof Jugador) {
            ((Jugador) f).setPosicion(((Jugador) futbolista).getPosicion());
            actualizarEstadisticasJugador(f, futbolista);
        } else if (futbolista instanceof Portero && f instanceof Portero) {
            ((Portero) f).setManoDominante(((Portero) futbolista).getManoDominante());
            actualizarEstadisticasPortero(f, futbolista);
        }

        return futbolistaRepository.save(f);
    }


    public double calcularBonusSalario(Long id){
        int totalGoles = 0, totalAsistencias = 0;
        double extras=0.0, golesBonus=1000, asistenciasBonus=500, restarPorAmarilla=250, restarPorRoja=450;
        Futbolista futbolista = findFutbolistaById(id);
        int tarjetasAmarillas, tarjetasRojas;
        if (futbolista instanceof Jugador ){
            Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
            
            if ((estadisticas instanceof EstadisticasJugador)){
            EstadisticasJugador ej = (EstadisticasJugador) estadisticas;
            totalGoles = ej.getGoles();
            totalAsistencias = ej.getAsistencias();
            tarjetasAmarillas = ej.getTarAmarilla();
            tarjetasRojas = ej.getTarRoja();
            extras = (totalGoles * golesBonus) + (totalAsistencias * asistenciasBonus) - (tarjetasAmarillas * restarPorAmarilla) - (tarjetasRojas * restarPorRoja);
            return extras;
            }
        }
        if (futbolista instanceof Portero ){
            int totalPorteriasACero = 0;
            double porteriasImbatidasBonus = 800;
            Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
            
            if ((estadisticas instanceof EstadisticasPortero)) {
            EstadisticasPortero ep = (EstadisticasPortero) estadisticas;
            totalPorteriasACero = ep.getPorteriasACero();
            extras = totalPorteriasACero * porteriasImbatidasBonus;
            return extras;
            }
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













