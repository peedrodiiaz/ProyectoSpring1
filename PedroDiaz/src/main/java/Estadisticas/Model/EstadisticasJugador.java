package Estadisticas.Model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class EstadisticasJugador extends Estadisticas{
    private int goles;
    private int asistencias;


}
