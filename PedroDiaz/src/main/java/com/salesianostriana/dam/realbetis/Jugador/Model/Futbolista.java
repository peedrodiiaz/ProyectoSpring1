package com.salesianostriana.dam.realbetis.Jugador.Model;

import com.salesianostriana.dam.realbetis.Equipo.Model.Equipo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import com.salesianostriana.dam.realbetis.Estadisticas.Model.Estadisticas;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "tipo")
@ToString
public abstract class Futbolista {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private  String apellidos;
    private String imgFutbolista;
    private LocalDate fechaNacimiento;
    private LocalDate fechaInicioContrato;
    private String nacionalidad;
    private int numCamiseta;
    private double salarioMensualBase;
    private String piernaBuena;
                                                                 //Esto para la herencia, eliminar.
    @OneToOne(mappedBy = "futbolista", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Estadisticas estadisticas;
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;





}
