package Estadisticas.Model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadisticasPortero extends Estadisticas{
    private int paradas;
    private int porteriasACero;





}
