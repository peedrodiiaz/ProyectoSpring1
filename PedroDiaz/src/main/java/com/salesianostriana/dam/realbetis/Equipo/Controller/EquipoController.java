package com.salesianostriana.dam.realbetis.Equipo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.realbetis.Equipo.Model.Equipo;
import com.salesianostriana.dam.realbetis.Equipo.service.EquipoService;
import com.salesianostriana.dam.realbetis.Jugador.service.FutbolistaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping ("/equipos")
public class EquipoController {

    private final EquipoService equipoService;
    private final FutbolistaService futbolistaService;


    @GetMapping ("/{id}")
    public String mostrarJugadores (@PathVariable Long id, Model m){
        m.addAttribute("futbolistas",futbolistaService.findFutbolistaByEquipoId(id));
        m.addAttribute("equipo", equipoService.findById(id));
            
        return "info_equipo";
    }

    @GetMapping ("/{id}/ordenarMaxGoles")
    public String ordenarMaxGoles (@PathVariable Long id, Model m){
        m.addAttribute("futbolistas", equipoService.findMaxGoleadores(id));
        m.addAttribute("equipo", equipoService.findById(id));

        return "info_equipo";
    }

    @GetMapping ("/{id}/ordenarMaxAsistencias")
    public String ordenarMaxAsitencias (@PathVariable Long id, Model m){
        m.addAttribute("futbolistas", equipoService.findMaxAsistentes(id));
        m.addAttribute("equipo", equipoService.findById(id));

        return "info_equipo";


    }

    @GetMapping ("/{id}/edit")
    public String formEdit(@PathVariable Long id, Model model){
        model.addAttribute("equipo", equipoService.findById(id));
        return "edit_equipo";
    }

    @PostMapping("/{id}/edit")
    public String editEquipo(@PathVariable Long id, @ModelAttribute ("equipo") Equipo e){

        equipoService.updateEquipo(id, e);

        return "redirect:/equipos/" +id ;
    }







}
