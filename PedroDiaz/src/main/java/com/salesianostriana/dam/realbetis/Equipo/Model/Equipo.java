package com.salesianostriana.dam.realbetis.Equipo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String categoria;
    @OneToMany(mappedBy = "equipo")
    @ToString.Exclude
    private List<Futbolista> listFutbolistas;
    private String imgEquipo;




}
