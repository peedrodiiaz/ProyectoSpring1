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

    

    public Futbolista upadateFutbolista (Long id, Futbolista futbolista){
        Futbolista f = findFutbolistaById(id);

        f.setApellidos(futbolista.getApellidos());
        f.setEquipo(futbolista.getEquipo());
        Estadisticas incomingEst = futbolista.getEstadisticas();
                if (f instanceof Jugador) {
                    EstadisticasJugador ej = new EstadisticasJugador();
                    ej.setMinJugados(incomingEst.getMinJugados());
                    ej.setTarAmarilla(incomingEst.getTarAmarilla());
                    ej.setTarRoja(incomingEst.getTarRoja());
                    ej.setCalificacion(incomingEst.getCalificacion());
                    if (incomingEst instanceof EstadisticasJugador) {
                        ej.setGoles(((EstadisticasJugador) incomingEst).getGoles());
                        ej.setAsistencias(((EstadisticasJugador) incomingEst).getAsistencias());
                    }
                    f.setEstadisticas(ej);
                } else if (f instanceof Portero) {
                    EstadisticasPortero ep = new EstadisticasPortero();
                    ep.setMinJugados(incomingEst.getMinJugados());
                    ep.setTarAmarilla(incomingEst.getTarAmarilla());
                    ep.setTarRoja(incomingEst.getTarRoja());
                    ep.setCalificacion(incomingEst.getCalificacion());
                    if (incomingEst instanceof EstadisticasPortero) {
                        ep.setParadas(((EstadisticasPortero) incomingEst).getParadas());
                        ep.setPorteriasACero(((EstadisticasPortero) incomingEst).getPorteriasACero());
                    }
                    f.setEstadisticas(ep);
                } 

        f.setFechaInicioContrato(futbolista.getFechaInicioContrato());
        f.setImgFutbolista(futbolista.getImgFutbolista());
        f.setNacionalidad(futbolista.getNacionalidad());
        f.setNombre(futbolista.getNombre());
        f.setNumCamiseta(futbolista.getNumCamiseta());
        f.setPiernaBuena(futbolista.getPiernaBuena());
        f.setSalarioMensualBase(futbolista.getSalarioMensualBase());
        if(f instanceof Jugador jugador && futbolista instanceof Jugador jugador2 ){
            ((jugador)).setPosicion(jugador2.getPosicion());
            
        }
        if (f instanceof Portero portero && futbolista instanceof Portero portero2){
            ((portero)).setManoDominante(portero2.getManoDominante());
        }
        
        return futbolistaRepository.save(f);
        
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


