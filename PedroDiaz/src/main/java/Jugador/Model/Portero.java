package Jugador.Model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Portero extends Futbolista{

    private String piernaBuena;

}
