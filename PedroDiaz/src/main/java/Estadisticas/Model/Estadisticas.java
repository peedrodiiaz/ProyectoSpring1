package Estadisticas.Model;


import Estadisticas.dto.EstadisticasDto;
import Jugador.Model.Futbolista;
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
    @ToString.Exclude
    private Futbolista Futbolista;

    private double minJugados;
    private int goles;
    private int asistencias;
    private int tarAmarilla;
    private  int tarRoja;
    private double calificacion;


    public static EstadisticasDto EntityconverToDto(Estadisticas estadisticas) {
        return EstadisticasDto.builder()
                .id(estadisticas.getId())
                .jugadorId(estadisticas.getFutbolista().getId())
                .minJugados(estadisticas.getMinJugados())
                .goles(estadisticas.getGoles())
                .asistencias(estadisticas.getAsistencias())
                .tarAmarilla(estadisticas.getTarAmarilla())
                .tarRoja(estadisticas.getTarRoja())
                .calificacion(estadisticas.getCalificacion())
                .build();
    }






}
