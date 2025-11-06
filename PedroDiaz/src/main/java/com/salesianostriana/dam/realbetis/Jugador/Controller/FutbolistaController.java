package com.salesianostriana.dam.realbetis.Jugador.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.realbetis.Equipo.service.EquipoService;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.Estadisticas;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasJugador;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasPortero;
import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;
import com.salesianostriana.dam.realbetis.Jugador.Model.Jugador;
import com.salesianostriana.dam.realbetis.Jugador.Model.Portero;
import com.salesianostriana.dam.realbetis.Jugador.service.FutbolistaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/futbolistas")
@RequiredArgsConstructor
public class FutbolistaController {

    private final FutbolistaService futbolistaService;
    private final EquipoService equipoService;

    

    @PostMapping("/{eqid}/eliminarFutbolista/{id}")
    public String deleteFutbolista(@PathVariable("eqid") Long eqId, @PathVariable("id") Long id){
        futbolistaService.deleteFutbolista(id);
        return "redirect:/equipos/" + eqId;
    }

    @GetMapping("/{id}")
    public String getFutbolista(@PathVariable Long id, Model model){
        Futbolista futbolista = futbolistaService.findFutbolistaById(id);
        double bonus = futbolistaService.calcularBonusSalario(id);
        double sueldoFinal = futbolistaService.calcularSalarioFutbolista(id);
        Estadisticas estadisticas = futbolistaService.findFutbolistaById(id).getEstadisticas();
        model.addAttribute("futbolista", futbolista);
        model.addAttribute("estadisticas", estadisticas);
        model.addAttribute("bonus", bonus);
        model.addAttribute("sueldoFinal", sueldoFinal);
        return "info_futbolista";
    }

    // @GetMapping ("{id}/edit")
    // public String formEdit (@PathVariable Long id, Model model){
    //     Futbolista f = futbolistaService.findFutbolistaById(id);
    //     model.addAttribute("futbolista",f);
    //     model.addAttribute("estadisticas", f.getEstadisticas());
    //     model.addAttribute("equipo",equipoService.findAll());
    //     return "edit_futbolista";
    // }


    // @PostMapping("{id}/edit")
    // public String editFutbolista (@PathVariable Long id, @ModelAttribute("futbolista") Futbolista f){
    //     futbolistaService.upadateFutbolista(id, f);
    //     return "redirect:/futbolistas/" + id;
    // }

    /*  editar para cuando es ugador */
    @GetMapping("/jugador/{id}/edit")
    public String editFormJugador(@PathVariable Long id, Model model){
        Futbolista f = futbolistaService.findFutbolistaById(id);
        model.addAttribute("jugador", (Jugador) f);
        model.addAttribute("estadisticas",(EstadisticasJugador) f.getEstadisticas());
        model.addAttribute("equipo", equipoService.findAll());
        return "edit_jugador";
    }

    @PostMapping("/jugador/{id}/edit")
    public String saveJugador(@PathVariable Long id, @ModelAttribute("jugador") Jugador jugador){
        futbolistaService.updateFutbolista(id, jugador);
        return "redirect:/futbolistas/" + id;
    }
    /*EDitar cuando es portero */
    @GetMapping("/portero/{id}/edit")
    public String editFormPortero(@PathVariable Long id, Model model){
        Futbolista f = futbolistaService.findFutbolistaById(id);
        
        model.addAttribute("portero", (Portero) f);
        model.addAttribute("estadisticas",(EstadisticasPortero) f.getEstadisticas());
        model.addAttribute("equipo", equipoService.findAll());
        return "edit_portero";
    }

    @PostMapping("/portero/{id}/edit")
    public String savePortero(@PathVariable Long id, @ModelAttribute("portero") Portero portero){
        futbolistaService.updateFutbolista(id, portero);
        return "redirect:/futbolistas/" + id;
    }
    


}
