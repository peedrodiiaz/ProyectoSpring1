package Jugador.dto;

import java.time.LocalDateTime;

public class JugadorDto {
    private Long id;

    private String nombre;

    private String apellidos;

    private String imgJugador;
    private String posicion;

    private LocalDateTime fechaNacimiento;

    private LocalDateTime fechaInicioContrato;

    private String nacionalidad;

    private Integer numCamiseta;

    private Double salarioMensualBase;

    private Long equipoId;
}
