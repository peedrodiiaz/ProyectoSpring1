package com.salesianostriana.dam.realbetis.Jugador.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@DiscriminatorValue("PORTERO")
public class Portero extends Futbolista{

    private String manoDominante;

}
