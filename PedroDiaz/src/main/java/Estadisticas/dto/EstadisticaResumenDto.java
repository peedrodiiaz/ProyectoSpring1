package Estadisticas.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadisticaResumenDto {
    private Long equipoId;
    private int totalGoles;
    private int totalAsistencias;
    private int totalTarAmarillas;
    private int totalTarRojas;
    private double totalMinJugados;
    private double mediaCalificacion;
    private int totalPorteriasACero;
    private int totalParadas;


}
