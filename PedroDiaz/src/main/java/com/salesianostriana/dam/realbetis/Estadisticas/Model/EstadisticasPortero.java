package com.salesianostriana.dam.realbetis.Estadisticas.Model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EstadisticasPortero extends Estadisticas{
    private int paradas;
    private int porteriasACero;





}
