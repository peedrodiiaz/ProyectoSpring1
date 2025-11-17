package com.salesianostriana.dam.realbetis.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasPortero;

import com.salesianostriana.dam.realbetis.Jugador.Model.Portero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PorteroEditDTO {
    // Datos del Portero
    private Long id;
    private String nombre;
    private String apellidos;
    private int numCamiseta;
    private LocalDate fechaNacimiento;
    private LocalDate fechaInicioContrato;
    private String manoDominante;
    private String piernaBuena;
    private String imgFutbolista;
    private double salarioMensualBase;
    private Long equipoId;
    private String nacionalidad;
    // Estadísticas del Portero
    private Long estadisticasId;
    private double minJugados;
    private int paradas;
    private int porteriasACero;
    private int tarAmarilla;
    private int tarRoja;
    private double calificacion;

    public static PorteroEditDTO fromPortero( Portero portero, EstadisticasPortero stats){
        stats = (EstadisticasPortero) portero.getEstadisticas();
        return PorteroEditDTO.builder()
            .id(portero.getId())
            .nombre(portero.getNombre())
            .apellidos(portero.getApellidos())
            .numCamiseta(portero.getNumCamiseta())
            .fechaNacimiento(portero.getFechaNacimiento())
            .fechaInicioContrato(portero.getFechaInicioContrato())
            .piernaBuena(portero.getPiernaBuena())
            .imgFutbolista(portero.getImgFutbolista())
            .salarioMensualBase(portero.getSalarioMensualBase())
            .equipoId(portero.getEquipo().getId())
            .nacionalidad(portero.getNacionalidad())
            .estadisticasId(stats.getId())
            .minJugados(stats.getMinJugados())
            .paradas(stats.getParadas())
            .porteriasACero(stats.getPorteriasACero())
            .tarAmarilla(stats.getTarAmarilla())
            .tarRoja(stats.getTarRoja())
            .calificacion(stats.getCalificacion())
            .build(); 
        
    }





}