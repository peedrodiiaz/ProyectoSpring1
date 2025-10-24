package Jugador.Model;

import Equipo.Model.Equipo;
import Jugador.dto.JugadorDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString

public class Jugador {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private  String apellidos;
    private String imgJugador;
    private String posicion;
    private LocalDate fechaNacimiento;
    private LocalDate fechaInicioContrato;
    private String nacionalidad;
    private int numCamiseta;
    private double salarioMensualBase;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo Equipo;


    public static JugadorDto entityConverToDto(Jugador jugador) {
        return JugadorDto.builder()
                .id(jugador.getId())
                .nombre(jugador.getNombre())
                .apellidos(jugador.getApellidos())
                .imgJugador(jugador.getImgJugador())
                .posicion(jugador.getPosicion())
                .fechaNacimiento(jugador.getFechaNacimiento())
                .fechaInicioContrato(jugador.getFechaInicioContrato())
                .nacionalidad(jugador.getNacionalidad())
                .numCamiseta(jugador.getNumCamiseta())
                .salarioMensualBase(jugador.getSalarioMensualBase())
                .equipoId(jugador.getEquipo() != null ? jugador.getEquipo().getId() : null)
                .build();
    }



}
