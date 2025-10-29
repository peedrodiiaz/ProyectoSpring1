package com.salesianostriana.dam.realbetis.Estadisticas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.realbetis.Estadisticas.Model.Estadisticas;
import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;


public interface EstadisticasRepository extends JpaRepository<Estadisticas , Long> {
    Estadisticas findByFutbolista(Futbolista futbolista);
}
