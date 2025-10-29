package com.salesianostriana.dam.realbetis.Jugador.Model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder

public class Portero extends Futbolista{

    private String piernaBuena;

}
