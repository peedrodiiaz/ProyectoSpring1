package com.salesianostriana.dam.realbetis.Jugador.service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.realbetis.DTOs.JugadorEditDTO;
import com.salesianostriana.dam.realbetis.DTOs.PorteroEditDTO;
import com.salesianostriana.dam.realbetis.Equipo.service.EquipoService;
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
    private final EquipoService equipoService;

    public FutbolistaService(JugadorRepository jugadorRepository,
                            FutbolistaRepository futbolistaRepository,
                            EquipoService equipoService,
                            @Lazy EstadisticasService estadisticasService) {
        this.equipoService = equipoService;
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
    // Jugador
    public Futbolista updateJugador(Long id, JugadorEditDTO dto) {
        Futbolista futbolista = findFutbolistaById(id);
        boolean hacer = true;
        List <Futbolista> listaFutbolistas = futbolista.getEquipo().getListFutbolistas();
        Jugador jugador = (Jugador) futbolista;
        // Comprobar que ningún otro jugador (excepto el que editamos) tiene el mismo dorsal que el enviado en el DTO
        hacer = listaFutbolistas.stream()
            .filter(f -> f instanceof Jugador)
            .map(f -> (Jugador) f)
            .filter(f -> !f.getId().equals(id))
            .allMatch(f -> f.getNumCamiseta() != dto.getNumCamiseta());
        

        if (hacer) {
            jugador.setNombre(dto.getNombre());
            jugador.setApellidos(dto.getApellidos());
            jugador.setNumCamiseta(dto.getNumCamiseta());
            jugador.setFechaNacimiento(dto.getFechaNacimiento());
            jugador.setFechaInicioContrato(dto.getFechaInicioContrato());
            jugador.setPiernaBuena(dto.getPiernaBuena());
            jugador.setImgFutbolista(dto.getImgFutbolista());
            jugador.setSalarioMensualBase(dto.getSalarioMensualBase());
            jugador.setPosicion(dto.getPosicion());
            
            jugador.setEquipo(equipoService.findById(dto.getEquipoId()));
            
            
            // Actualizar estadísticas
            if (jugador.getEstadisticas() instanceof EstadisticasJugador) {
                EstadisticasJugador stats = (EstadisticasJugador) jugador.getEstadisticas();
                stats.setMinJugados(dto.getMinJugados());
                stats.setGoles(dto.getGoles());
                stats.setAsistencias(dto.getAsistencias());
                stats.setTarAmarilla(dto.getTarAmarilla());
                stats.setTarRoja(dto.getTarRoja());
                stats.setCalificacion(dto.getCalificacion());
            }
        }else {
            return null;
        }            
        return futbolistaRepository.save(jugador);


}

    //Portero
    public Futbolista updatePortero(Long id, PorteroEditDTO dto) {
        Futbolista futbolista = findFutbolistaById(id);
            List <Futbolista> listaFutbolistas = futbolista.getEquipo().getListFutbolistas();

        Portero portero = (Portero) futbolista;
        boolean hacer = listaFutbolistas.stream()
            .filter(f -> f instanceof Portero)
            .map(f -> (Portero) f)
            .filter(f -> !f.getId().equals(id))
            .allMatch(f -> f.getNumCamiseta() != dto.getNumCamiseta());
    
        if (hacer) {
            
        
        portero.setNombre(dto.getNombre());
        portero.setApellidos(dto.getApellidos());
        portero.setNumCamiseta(dto.getNumCamiseta());
        portero.setFechaNacimiento(dto.getFechaNacimiento());
        portero.setFechaInicioContrato(dto.getFechaInicioContrato());
        portero.setPiernaBuena(dto.getPiernaBuena());
        portero.setImgFutbolista(dto.getImgFutbolista());
        portero.setSalarioMensualBase(dto.getSalarioMensualBase());
        portero.setManoDominante(dto.getManoDominante());
        
    
        if (dto.getEquipoId() != null) {
            portero.setEquipo(equipoService.findById(dto.getEquipoId()));
        }
        
        if (portero.getEstadisticas() instanceof EstadisticasPortero) {
            EstadisticasPortero stats = (EstadisticasPortero) portero.getEstadisticas();
            stats.setMinJugados(dto.getMinJugados());
            stats.setParadas(dto.getParadas());
            stats.setPorteriasACero(dto.getPorteriasACero());
            stats.setTarAmarilla(dto.getTarAmarilla());
            stats.setTarRoja(dto.getTarRoja());
            stats.setCalificacion(dto.getCalificacion());
        }
    }else{
        return null;
    }
        
        
        return futbolistaRepository.save(portero);
}


    //Lógica de bonus
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

    
    public Futbolista createJugador(Jugador jugador) {
        if (jugador.getEstadisticas() == null) {
            EstadisticasJugador estadisticas = new EstadisticasJugador();
            estadisticas.setFutbolista(jugador);
            jugador.setEstadisticas(estadisticas);
        }
        if (jugador.getEquipo() != null && jugador.getEquipo().getId() != null) {
            jugador.setEquipo(equipoService.findById(jugador.getEquipo().getId()));
        }
        return futbolistaRepository.save(jugador);
    }
    
}













