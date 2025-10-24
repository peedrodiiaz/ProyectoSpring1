package Estadisticas.Model;


import Estadisticas.dto.EstadisticasDto;
import Jugador.Model.Jugador;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Estadisticas {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador Jugador;

    private double minJugados;
    private int goles;
    private int asistencias;
    private int tarAmarilla;
    private  int tarRoja;
    private double calificacion;


    public static EstadisticasDto EntityconverToDto(Estadisticas estadisticas) {
        return EstadisticasDto.builder()
                .id(estadisticas.getId())
                .jugadorId(estadisticas.getJugador().getId())
                .minJugados(estadisticas.getMinJugados())
                .goles(estadisticas.getGoles())
                .asistencias(estadisticas.getAsistencias())
                .tarAmarilla(estadisticas.getTarAmarilla())
                .tarRoja(estadisticas.getTarRoja())
                .calificacion(estadisticas.getCalificacion())
                .build();
    }






}
