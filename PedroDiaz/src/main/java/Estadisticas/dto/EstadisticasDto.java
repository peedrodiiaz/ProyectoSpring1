package Estadisticas.dto;

import Estadisticas.Model.Estadisticas;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class EstadisticasDto {
    private Long id;

    private Long jugadorId;

    private Double minJugados;

    private Integer goles;

    private Integer asistencias;

    private Integer tarAmarilla;

    private Integer tarRoja;

    private Double calificacion;


    public Estadisticas dtoToEntity(EstadisticasDto dto) {
        return Estadisticas.builder()
                .id(dto.getId())
                .minJugados(dto.getMinJugados())
                .goles(dto.getGoles())
                .asistencias(dto.getAsistencias())
                .tarAmarilla(dto.getTarAmarilla())
                .tarRoja(dto.getTarRoja())
                .calificacion(dto.getCalificacion())
                .build();

    }
}
