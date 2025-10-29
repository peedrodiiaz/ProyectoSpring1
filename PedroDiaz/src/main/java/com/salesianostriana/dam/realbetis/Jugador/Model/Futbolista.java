package com.salesianostriana.dam.realbetis.Jugador.Model;

import com.salesianostriana.dam.realbetis.Equipo.Model.Equipo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import com.salesianostriana.dam.realbetis.Estadisticas.Model.Estadisticas;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
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

    @OneToOne(mappedBy = "futbolista", fetch = FetchType.LAZY)
    private Estadisticas estadisticas;
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;


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
