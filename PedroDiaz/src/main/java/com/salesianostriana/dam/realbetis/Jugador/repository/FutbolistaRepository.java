package com.salesianostriana.dam.realbetis.Jugador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;

import java.util.List;

public interface FutbolistaRepository extends JpaRepository<Futbolista,Long> {

    List<Futbolista> findByEquipoId(Long equipoId);

}
