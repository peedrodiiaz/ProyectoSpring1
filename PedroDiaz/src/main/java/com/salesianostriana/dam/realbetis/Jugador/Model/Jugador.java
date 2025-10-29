package com.salesianostriana.dam.realbetis.Jugador.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@DiscriminatorValue("JUGADOR")
public class Jugador extends Futbolista{
    private String posicion;



}
