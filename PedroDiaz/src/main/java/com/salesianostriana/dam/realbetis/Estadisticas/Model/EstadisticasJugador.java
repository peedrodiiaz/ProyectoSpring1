package com.salesianostriana.dam.realbetis.Estadisticas.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@DiscriminatorValue("JUGADOR")
public class EstadisticasJugador extends Estadisticas{
    private int goles;
    private int asistencias;

    
}



