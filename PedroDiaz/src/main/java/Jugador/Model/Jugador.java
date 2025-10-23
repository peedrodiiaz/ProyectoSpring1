package Jugador.Model;

import Equipo.Model.Equipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString

public class Jugador {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private  String apellidos;
    private String imgJugador;
    private LocalDateTime fechaNacimiento;
    private LocalDateTime fechaInicioContrato;
    private String nacionalidad;
    private int numCamiseta;
    private double salarioMensualBase;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo Equipo;


}
