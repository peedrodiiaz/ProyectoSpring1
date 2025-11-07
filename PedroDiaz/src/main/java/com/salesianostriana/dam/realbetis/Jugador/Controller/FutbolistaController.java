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
        Estadisticas estadisticas = futbolista.getEstadisticas();
        
        model.addAttribute("futbolista", futbolista);
        model.addAttribute("estadisticas", estadisticas);
        
    
        boolean esJugador = estadisticas instanceof EstadisticasJugador;
        boolean esPortero = estadisticas instanceof EstadisticasPortero;
        
        model.addAttribute("esJugador", esJugador);
        model.addAttribute("esPortero", esPortero);
        

        if (esJugador) {
            EstadisticasJugador ej = (EstadisticasJugador) estadisticas;
            model.addAttribute("goles", ej.getGoles());
            model.addAttribute("asistencias", ej.getAsistencias());
        }
        
        if (esPortero) {
            EstadisticasPortero ep = (EstadisticasPortero) estadisticas;
            model.addAttribute("paradas", ep.getParadas());
            model.addAttribute("porteriasACero", ep.getPorteriasACero());
        }
        
        model.addAttribute("bonus", bonus);
        model.addAttribute("sueldoFinal", sueldoFinal);
        return "info_futbolista";
}


    /*  editar para cuando es ugador */
    @GetMapping("/jugador/{id}/edit")
    public String editFormJugador(@PathVariable Long id, Model model) {
        Futbolista f = futbolistaService.findFutbolistaById(id);

        
        Jugador jugador = (Jugador) f;
        EstadisticasJugador stats = (EstadisticasJugador) jugador.getEstadisticas();
        
        // Crear DTO con los datos actuales
        JugadorEditDTO dto = JugadorEditDTO.builder()
            .id(jugador.getId())
            .nombre(jugador.getNombre())
            .apellidos(jugador.getApellidos())
            .numCamiseta(jugador.getNumCamiseta())
            .fechaNacimiento(jugador.getFechaNacimiento())
            .fechaInicioContrato(jugador.getFechaInicioContrato())
            .posicion(jugador.getPosicion())
            .piernaBuena(jugador.getPiernaBuena())
            .imgFutbolista(jugador.getImgFutbolista())
            .salarioMensualBase(jugador.getSalarioMensualBase())
            .equipoId(jugador.getEquipo().getId())
            .estadisticasId(stats.getId())
            .minJugados(stats.getMinJugados())
            .goles(stats.getGoles())
            .asistencias(stats.getAsistencias())
            .tarAmarilla(stats.getTarAmarilla())
            .tarRoja(stats.getTarRoja())
            .calificacion(stats.getCalificacion())
            .build();

        model.addAttribute("jugador", dto);
        model.addAttribute("equipos", equipoService.findAll());
        return "edit_jugador";
    }

    @PostMapping("/jugador/{id}/edit")
        public String saveJugador(@PathVariable Long id, @ModelAttribute("jugador") JugadorEditDTO dto) {
            futbolistaService.updateJugador(id, dto);
            return "redirect:/futbolistas/" + id;
    }
    /*EDitar cuando es portero */
    @GetMapping("/portero/{id}/edit")
        public String editFormPortero(@PathVariable Long id, Model model) {
            Futbolista f = futbolistaService.findFutbolistaById(id);
            
            Portero portero = (Portero) f;
            EstadisticasPortero stats = (EstadisticasPortero) portero.getEstadisticas();
            
            // Crear DTO con los datos actuales
            PorteroEditDTO dto = PorteroEditDTO.builder()
                .id(portero.getId())
                .nombre(portero.getNombre())
                .apellidos(portero.getApellidos())
                .numCamiseta(portero.getNumCamiseta())
                .fechaNacimiento(portero.getFechaNacimiento())
                .fechaInicioContrato(portero.getFechaInicioContrato())
                .manoDominante(portero.getManoDominante())
                .piernaBuena(portero.getPiernaBuena())
                .imgFutbolista(portero.getImgFutbolista())
                .salarioMensualBase(portero.getSalarioMensualBase())
                .equipoId(portero.getEquipo().getId())
                .estadisticasId(stats.getId())
                .minJugados(stats.getMinJugados())
                .paradas(stats.getParadas())
                .porteriasACero(stats.getPorteriasACero())
                .tarAmarilla(stats.getTarAmarilla())
                .tarRoja(stats.getTarRoja())
                .calificacion(stats.getCalificacion())
                .build();
            
            model.addAttribute("portero", dto);
            model.addAttribute("equipos", equipoService.findAll());
            return "edit_portero";
    }

    @PostMapping("/portero/{id}/edit")
    public String savePortero(@PathVariable Long id, @ModelAttribute("portero") PorteroEditDTO dto) {
        futbolistaService.updatePortero(id, dto);
        return "redirect:/futbolistas/" + id;
    }
    
    


}
