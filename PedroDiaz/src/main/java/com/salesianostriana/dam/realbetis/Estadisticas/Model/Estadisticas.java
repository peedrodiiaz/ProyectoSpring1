package com.salesianostriana.dam.realbetis.Estadisticas.Model;



import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "tipo_estadistica") 
public class Estadisticas {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "futbolista_id")
    @ToString.Exclude
    private Futbolista futbolista;

    private double minJugados;

    private int tarAmarilla;
    private  int tarRoja;
    private double calificacion;


    






}
