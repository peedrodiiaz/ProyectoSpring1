package Equipo.service;

import Equipo.Model.Equipo;
import Equipo.repository.EquipoRepository;
import Estadisticas.Model.EstadisticasJugador;
import Estadisticas.service.EstadisticasService;
import Jugador.Model.Futbolista;
import Jugador.Model.Jugador;
import Jugador.service.FutbolistaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final FutbolistaService futbolistaService;
    private final EstadisticasService estadisticasService;

    public List<Equipo> findAll(){
        return equipoRepository.findAll();
    }


    public Equipo findById (Long id){
        return equipoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id+"No encontrado"));
    }
    public Equipo createEquipo (Equipo eq){
        return equipoRepository.save(eq);
    }

    public Equipo updateEquipo (Long id, Equipo equipoUpdate){
        Equipo eq = findById(id);
        eq.setCategoria(equipoUpdate.getCategoria());
        eq.setImgEquipo(equipoUpdate.getImgEquipo());
        eq.setId(equipoUpdate.getId());
        eq.setListFutbolistas(equipoUpdate.getListFutbolistas());
        return equipoRepository.save(eq);

    }

    public void deleteEquipo(Long id){
        equipoRepository.deleteById(id);
    }

    public double calcularSalarioTotalEquipo(Long equipoId) {
        Equipo equipo = findById(equipoId);
        
        List<Futbolista> futbolistasList = equipo.getListFutbolistas();
        return futbolistasList.stream()
            .mapToDouble(f -> futbolistaService.calcularSalarioFutbolista(f.getId()))
            .sum();
    }
    
    public double calcularGastoTotalSalarioEquipos() {

        List <Equipo>listEquipos = equipoRepository.findAll();

        return listEquipos.stream()
                .mapToDouble(e -> calcularSalarioTotalEquipo(e.getId()))
                .sum();
    }



    public List<Jugador> findMaxGoleadores() {
        
        return equipoRepository.findAll().stream()
            .flatMap(e -> e.getListFutbolistas().stream())
            .filter(f -> f instanceof Jugador)
            .map(f -> (Jugador) f)
            .sorted((j1, j2) -> {
                int  goles1 = ((EstadisticasJugador) estadisticasService.getEstadisticasByFutbolista(j1.getId())).getGoles();
                int  goles2 = ((EstadisticasJugador) estadisticasService.getEstadisticasByFutbolista(j2.getId())).getGoles();
                return Integer.compare(goles2, goles1); 
            })
            .limit(3)
            .collect(Collectors.toList());
    }


    public List <Jugador> findMaxAsistentes(){

        return equipoRepository.findAll().stream()
            .flatMap(e->e.getListFutbolistas().stream())
            .filter(f-> f instanceof Jugador)
            .map( f -> (Jugador) f)
            .sorted ((j1,j2)->{
                int asis1 = ((EstadisticasJugador)estadisticasService.getEstadisticasByFutbolista(j1.getId())).getAsistencias();
                int asis2 = ((EstadisticasJugador)estadisticasService.getEstadisticasByFutbolista(j2.getId())).getAsistencias();
                return Integer.compare(asis1, asis2);
            })
            .limit(3)
            .collect(Collectors.toList());



    }




    






}


