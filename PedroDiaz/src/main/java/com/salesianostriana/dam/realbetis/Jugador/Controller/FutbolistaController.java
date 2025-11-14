package com.salesianostriana.dam.realbetis.Jugador.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.realbetis.DTOs.JugadorEditDTO;
import com.salesianostriana.dam.realbetis.DTOs.PorteroEditDTO;
import com.salesianostriana.dam.realbetis.Equipo.service.EquipoService;

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

    
    //ELIMINAR

    @PostMapping("/{eqid}/eliminarFutbolista/{id}")
    public String deleteFutbolista(@PathVariable("eqid") Long eqId, @PathVariable("id") Long id){
        futbolistaService.deleteFutbolista(id);
        return "redirect:/equipos/" + eqId;
    }

    //LISTAR
    
    @GetMapping("/{id}")
    public String getFutbolista(@PathVariable Long id, Model model) {
        Futbolista futbolista = futbolistaService.findFutbolistaById(id);
        model.addAttribute("futbolista", futbolista);
        model.addAttribute("estadisticas", futbolista.getEstadisticas());
        model.addAttribute("bonus", futbolistaService.calcularBonusSalario(id));
        model.addAttribute("sueldoFinal", futbolistaService.calcularSalarioFutbolista(id));
        return "info_futbolista";

}

    //EDITAR

    /*  editar para cuando es Jugador */
    @GetMapping("/jugador/{id}/edit")
    public String editFormJugador(@PathVariable Long id, Model model) {
        Futbolista f = futbolistaService.findFutbolistaById(id);
        Jugador jugador = (Jugador) f;
        EstadisticasJugador stats = (EstadisticasJugador) jugador.getEstadisticas();     
        
        // Crear DTO con los datos actuales
        JugadorEditDTO dto = JugadorEditDTO.fromJugador(jugador, stats);

        model.addAttribute("jugador", dto);
        model.addAttribute("equipos", equipoService.findAll());
        return "edit_jugador";
    }

    @PostMapping("/jugador/{id}/edit")
        public String saveJugador(@PathVariable Long id, @ModelAttribute("jugador") JugadorEditDTO dto) {
            if(futbolistaService.updateJugador(id, dto)== null){
                return "edit_jugador";
            }else{
                
                return "redirect:/futbolistas/" + id;
            }

        }

    /*EDitar cuando es portero */
    @GetMapping("/portero/{id}/edit")
        public String editFormPortero(@PathVariable Long id, Model model) {
            Futbolista f = futbolistaService.findFutbolistaById(id);
            
            Portero portero = (Portero) f;
            EstadisticasPortero stats = (EstadisticasPortero) portero.getEstadisticas();
    
            // Crear DTO con los datos actuales
            PorteroEditDTO dto = PorteroEditDTO.fromPortero(portero, stats);
            model.addAttribute("portero", dto);
            model.addAttribute("equipos", equipoService.findAll());
            return "edit_portero";
    }

    @PostMapping("/portero/{id}/edit")
    public String savePortero(@PathVariable Long id, @ModelAttribute("portero") PorteroEditDTO dto) {
        
        if ( futbolistaService.updatePortero(id, dto)==null) {
            return "edit_portero";
            
        }
        return "redirect:/futbolistas/" + id;
    }
    

    //ANIADIR
    @PostMapping ("{id}/jugador/add")
    public String addJugador(@PathVariable Long id, @ModelAttribute("jugador") Jugador j){

        futbolistaService.createJugador(j);
        System.out.println( j );
        System.out.println( "holaaaaaaaa" );

        return "redirect:/futbolistas/" + j.getId();
    }    
    
    @GetMapping ("{id}/jugador/add")
    public String addJugador (@PathVariable Long id,Model model){
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("equipo", equipoService.findById(id));
        return "add_jugador";
    }


}
