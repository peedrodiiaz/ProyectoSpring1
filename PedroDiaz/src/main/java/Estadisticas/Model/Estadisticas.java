package Estadisticas.Model;


import Jugador.Model.Jugador;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Estadisticas {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador Jugador;

    private double minJugados;
    private int goles;
    private int asistencias;
    private int tarAmarilla;
    private  int tarRoja;
    private double calificacion;


}
