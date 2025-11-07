package com.salesianostriana.dam.realbetis.MainController;


import com.salesianostriana.dam.realbetis.Equipo.repository.EquipoRepository;
import com.salesianostriana.dam.realbetis.Equipo.service.EquipoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.realbetis.Jugador.repository.FutbolistaRepository;


@RequiredArgsConstructor
@Controller
public class MainController {

    
    private final EquipoRepository equipoRepository;
    
    private final  FutbolistaRepository futbolistaRepository;
    private final EquipoService equipoService;

    @GetMapping("/")
    public String inicio(Model model) {
        long totalEquipos = equipoRepository.count();
        long totalFutbolistas = futbolistaRepository.count();
        
        
        
        model.addAttribute("totalEquipos", totalEquipos);
        model.addAttribute("totalFutbolistas", totalFutbolistas);
        model.addAttribute("equipos", equipoService.findAll());
        
        return "inicio";
    }

}
