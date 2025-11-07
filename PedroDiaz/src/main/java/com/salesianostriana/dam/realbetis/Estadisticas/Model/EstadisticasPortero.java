package com.salesianostriana.dam.realbetis.Estadisticas.Model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("EstadisticasPortero")
public class EstadisticasPortero extends Estadisticas{
    
    private int paradas;
    
    @Column(name = "porteriasacero")
    private int porteriasACero;





}
