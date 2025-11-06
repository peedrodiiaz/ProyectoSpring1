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

    

    public Futbolista updateFutbolista (Long id, Futbolista futbolista){
        Futbolista f = findFutbolistaById(id);

        f.setApellidos(futbolista.getApellidos());
        f.setEquipo(futbolista.getEquipo());
        Estadisticas estadisticasForm = futbolista.getEstadisticas();

        //Para coger las estadistica ya creadas de la base de datos del jugador que modificamos
        Estadisticas estadisticasExistente = estadisticasService.getEstadisticasByFutbolista(id);

        if (f instanceof Jugador) {
            if (estadisticasExistente instanceof EstadisticasJugador) {
                
                EstadisticasJugador ejExist = (EstadisticasJugador) estadisticasExistente;
                
                ejExist.setMinJugados(estadisticasForm.getMinJugados());
                ejExist.setTarAmarilla(estadisticasForm.getTarAmarilla());
                ejExist.setTarRoja(estadisticasForm.getTarRoja());
                ejExist.setCalificacion(estadisticasForm.getCalificacion());
                if (estadisticasForm instanceof EstadisticasJugador) {
                    ejExist.setGoles(((EstadisticasJugador) estadisticasForm).getGoles());
                    ejExist.setAsistencias(((EstadisticasJugador) estadisticasForm).getAsistencias());
                }
                
                ejExist.setFutbolista(f);
                f.setEstadisticas(ejExist);
            } else {
                // la primera vez no van a exitir porque en la base de datos lo que hay son estadisticas a secas
                EstadisticasJugador ej = new EstadisticasJugador();
                if (estadisticasForm != null) {
                    ej.setMinJugados(estadisticasForm.getMinJugados());
                    ej.setTarAmarilla(estadisticasForm.getTarAmarilla());
                    ej.setTarRoja(estadisticasForm.getTarRoja());
                    ej.setCalificacion(estadisticasForm.getCalificacion());
                    if (estadisticasForm instanceof EstadisticasJugador) {
                        ej.setGoles(((EstadisticasJugador) estadisticasForm).getGoles());
                        ej.setAsistencias(((EstadisticasJugador) estadisticasForm).getAsistencias());
                    }
                }
                // si el form trae id, respetarla (por si el binding incluye el id)
                if (estadisticasForm != null && estadisticasForm.getId() != null) {
                    ej.setId(estadisticasForm.getId());
                }
                ej.setFutbolista(f);
                f.setEstadisticas(ej);
            }
        } else if (f instanceof Portero) {
            if (estadisticasExistente instanceof EstadisticasPortero) {
                EstadisticasPortero epExist = (EstadisticasPortero) estadisticasExistente;
                if (estadisticasForm != null) {
                    epExist.setMinJugados(estadisticasForm.getMinJugados());
                    epExist.setTarAmarilla(estadisticasForm.getTarAmarilla());
                    epExist.setTarRoja(estadisticasForm.getTarRoja());
                    epExist.setCalificacion(estadisticasForm.getCalificacion());
                    if (estadisticasForm instanceof EstadisticasPortero) {
                        epExist.setParadas(((EstadisticasPortero) estadisticasForm).getParadas());
                        epExist.setPorteriasACero(((EstadisticasPortero) estadisticasForm).getPorteriasACero());
                    }
                }
                epExist.setFutbolista(f);
                f.setEstadisticas(epExist);
            } else {
                EstadisticasPortero ep = new EstadisticasPortero();
                if (estadisticasForm != null) {
                    ep.setMinJugados(estadisticasForm.getMinJugados());
                    ep.setTarAmarilla(estadisticasForm.getTarAmarilla());
                    ep.setTarRoja(estadisticasForm.getTarRoja());
                    ep.setCalificacion(estadisticasForm.getCalificacion());
                    if (estadisticasForm instanceof EstadisticasPortero) {
                        ep.setParadas(((EstadisticasPortero) estadisticasForm).getParadas());
                        ep.setPorteriasACero(((EstadisticasPortero) estadisticasForm).getPorteriasACero());
                    }
                }
                if (estadisticasForm != null && estadisticasForm.getId() != null) {
                    ep.setId(estadisticasForm.getId());
                }
                ep.setFutbolista(f);
                f.setEstadisticas(ep);
            }
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


