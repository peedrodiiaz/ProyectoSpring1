package com.salesianostriana.dam.realbetis.Jugador.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;
import com.salesianostriana.dam.realbetis.Jugador.service.FutbolistaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/futbolistas")
@RequiredArgsConstructor
public class FutbolistaController {

    private final FutbolistaService futbolistaService;

    

    @PostMapping("/{eqid}/eliminarFutbolista/{id}")
    public String deleteFutbolista(@PathVariable("eqid") Long eqId, @PathVariable("id") Long id){
        futbolistaService.deleteFutbolista(id);
        return "redirect:/equipos/" + eqId;
    }

    @GetMapping("/{id}")
    public String getFutbolista(@PathVariable Long id, Model model){
        Futbolista futbolista = futbolistaService.findFutbolistaById(id);
        model.addAttribute("futbolista", futbolista);
        return "info_futbolista";
    }

}
