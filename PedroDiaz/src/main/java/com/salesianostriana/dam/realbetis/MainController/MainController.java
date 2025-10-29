package com.salesianostriana.dam.realbetis.MainController;

import com.salesianostriana.dam.realbetis.Equipo.Model.Equipo;
import com.salesianostriana.dam.realbetis.Equipo.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.realbetis.Jugador.repository.FutbolistaRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    
    private final EquipoRepository equipoRepository;
    
    
    private final  FutbolistaRepository futbolistaRepository;

    @GetMapping("/")
    public String inicio(Model model) {
        long totalEquipos = equipoRepository.count();
        long totalFutbolistas = futbolistaRepository.count();
        
        List<Equipo> equipos = equipoRepository.findAll();
        
        model.addAttribute("totalEquipos", totalEquipos);
        model.addAttribute("totalFutbolistas", totalFutbolistas);
        model.addAttribute("equipos", equipos);
        
        return "inicio";
    }
}
