package com.salesianostriana.dam.realbetis.Jugador.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.realbetis.Jugador.service.FutbolistaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/futbolistas")
@RequiredArgsConstructor
public class FutbolistaController {

    private final FutbolistaService futbolistaService;

    


}
