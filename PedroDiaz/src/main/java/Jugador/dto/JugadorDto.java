package Jugador.dto;

import Jugador.Model.Jugador;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDto {
    private Long id;

    private String nombre;

    private String apellidos;

    private String imgJugador;
    private String posicion;

    private LocalDate fechaNacimiento;

    private LocalDate fechaInicioContrato;

    private String nacionalidad;

    private Integer numCamiseta;

    private Double salarioMensualBase;

    private Long equipoId;




    public Jugador converToEntity(JugadorDto dto) {
        return Jugador.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .imgJugador(dto.getImgJugador())
                .posicion(dto.getPosicion())
                .fechaNacimiento(dto.getFechaNacimiento())
                .fechaInicioContrato(dto.getFechaInicioContrato())
                .nacionalidad(dto.getNacionalidad())
                .numCamiseta(dto.getNumCamiseta())
                .salarioMensualBase(dto.getSalarioMensualBase())
                .build();
    }
}
