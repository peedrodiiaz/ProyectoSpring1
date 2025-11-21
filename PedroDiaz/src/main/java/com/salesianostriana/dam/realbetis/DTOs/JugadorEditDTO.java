package com.salesianostriana.dam.realbetis.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasJugador;
import com.salesianostriana.dam.realbetis.Jugador.Model.Jugador;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JugadorEditDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private int numCamiseta;
    private LocalDate fechaNacimiento;
    private LocalDate fechaInicioContrato;
    private String posicion;
    private String piernaBuena;
    private String imgFutbolista;
    private double salarioMensualBase;
    private Long equipoId;
    private String nacionalidad;
    
    // Estadísticas del Jugador paraa poder crearlo 
    private Long estadisticasId;
    private double minJugados;
    private int goles;
    private int asistencias;
    private int tarAmarilla;
    private int tarRoja;
    private double calificacion;

    public static JugadorEditDTO fromJugador( Jugador jugador, EstadisticasJugador stats){
        stats = (EstadisticasJugador) jugador.getEstadisticas();
        return JugadorEditDTO.builder()
            .id(jugador.getId())
            .nombre(jugador.getNombre())
            .apellidos(jugador.getApellidos())
            .numCamiseta(jugador.getNumCamiseta())
            .fechaNacimiento(jugador.getFechaNacimiento())
            .fechaInicioContrato(jugador.getFechaInicioContrato())
            .posicion(jugador.getPosicion())
            .piernaBuena(jugador.getPiernaBuena())
            .imgFutbolista(jugador.getImgFutbolista())
            .salarioMensualBase(jugador.getSalarioMensualBase())
            .equipoId(jugador.getEquipo().getId())
            .nacionalidad(jugador.getNacionalidad())
            .estadisticasId(stats.getId())
            .minJugados(stats.getMinJugados())
            .goles(stats.getGoles())
            .asistencias(stats.getAsistencias())
            .tarAmarilla(stats.getTarAmarilla())
            .tarRoja(stats.getTarRoja())
            .calificacion(stats.getCalificacion())
            .build(); 
        
    }






}
