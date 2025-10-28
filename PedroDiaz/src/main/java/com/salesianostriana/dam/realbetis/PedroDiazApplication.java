package com.salesianostriana.dam.realbetis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
	"com.salesianostriana.dam.realbetis",
	"Equipo",
	"Jugador",
	"Estadisticas",
	"MainController"
})
@EntityScan(basePackages = {
	"Equipo.Model",
	"Jugador.Model",
	"Estadisticas.Model"
})
@EnableJpaRepositories(basePackages = {
	"Equipo.repository",
	"Jugador.repository",
	"Estadisticas.repository"
})
public class PedroDiazApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedroDiazApplication.class, args);
	}

}
