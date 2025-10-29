package com.salesianostriana.dam.realbetis.Estadisticas.Model;


import com.salesianostriana.dam.realbetis.Estadisticas.dto.EstadisticasDto;
import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;
import com.salesianostriana.dam.realbetis.Jugador.Model.Jugador;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Entity
public class Estadisticas {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "futbolista_id")
    @ToString.Exclude
    private Futbolista futbolista;

    private double minJugados;

    private int tarAmarilla;
    private  int tarRoja;
    private double calificacion;


    public static EstadisticasDto EntityconverToDto(Estadisticas estadisticas) {
        return EstadisticasDto.builder()
                .id(estadisticas.getId())
                .jugadorId(estadisticas.getFutbolista().getId())
                .minJugados(estadisticas.getMinJugados())
                .tarAmarilla(estadisticas.getTarAmarilla())
                .tarRoja(estadisticas.getTarRoja())
                .calificacion(estadisticas.getCalificacion())
                .build();
    }






}
