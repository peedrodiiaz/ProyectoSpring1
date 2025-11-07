package com.salesianostriana.dam.realbetis.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JugadorEditDTO {
    // Datos del Jugador
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
    
    // Estadísticas del Jugador
    private Long estadisticasId;
    private double minJugados;
    private int goles;
    private int asistencias;
    private int tarAmarilla;
    private int tarRoja;
    private double calificacion;
}
