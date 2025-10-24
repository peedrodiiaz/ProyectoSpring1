package Estadisticas.Model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class EstadisticasJugador extends Estadisticas{
    private int goles;
    private int asistencias;
}
