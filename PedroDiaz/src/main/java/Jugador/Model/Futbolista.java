package Jugador.Model;

import Equipo.Model.Equipo;
import Estadisticas.Model.Estadisticas;
import Estadisticas.Model.EstadisticasJugador;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "tipo")
public abstract class Futbolista {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private  String apellidos;
    private String imgFutbolista;
    private LocalDate fechaNacimiento;
    private LocalDate fechaInicioContrato;
    private String nacionalidad;
    private int numCamiseta;
    private double salarioMensualBase;

    @OneToOne
    @JoinColumn(name = "estadisticas_id")
    private Estadisticas estadisticas;
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo Equipo;


//    public static FutbolistaDto entityConverToDto(Futbolista futbolista) {
//        return FutbolistaDto.builder()
//                .id(futbolista.getId())
//                .nombre(futbolista.getNombre())
//                .apellidos(futbolista.getApellidos())
//                .imgJugador(futbolista.getImgJugador())
//                .fechaNacimiento(futbolista.getFechaNacimiento())
//                .fechaInicioContrato(futbolista.getFechaInicioContrato())
//                .nacionalidad(futbolista.getNacionalidad())
//                .numCamiseta(futbolista.getNumCamiseta())
//                .salarioMensualBase(futbolista.getSalarioMensualBase())
//                .equipoId(futbolista.getEquipo() != null ? futbolista.getEquipo().getId() : null)
//                .build();
//    }



}
