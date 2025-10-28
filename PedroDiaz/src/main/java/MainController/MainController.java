package MainController;

import Equipo.Model.Equipo;
import Equipo.repository.EquipoRepository;
import Jugador.repository.FutbolistaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
