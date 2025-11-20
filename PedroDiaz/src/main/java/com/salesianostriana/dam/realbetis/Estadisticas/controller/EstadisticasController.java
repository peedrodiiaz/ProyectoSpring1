package com.salesianostriana.dam.realbetis.Estadisticas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.realbetis.Equipo.service.EquipoService;
import com.salesianostriana.dam.realbetis.Estadisticas.service.EstadisticasService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {

	private final EstadisticasService estadisticasService;
	private final EquipoService equipoService;

	@GetMapping("/equipo/{equipoId}/resumen")
	public String resumenEquipo(@PathVariable("equipoId") Long equipoId, Model model) {
		var resumen = estadisticasService.getResumenEstadisticasEquipo(equipoId);
		model.addAttribute("resumen", resumen);
        model.addAttribute("equipo",equipoService.findById(estadisticasService.getResumenEstadisticasEquipo(equipoId).getEquipoId()));
		return "info_estadisticas_equipo";
	}

}
