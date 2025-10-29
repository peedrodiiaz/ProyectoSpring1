package com.salesianostriana.dam.realbetis.Equipo.service;

import com.salesianostriana.dam.realbetis.Equipo.Model.Equipo;
import com.salesianostriana.dam.realbetis.Equipo.repository.EquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasJugador;
import com.salesianostriana.dam.realbetis.Estadisticas.service.EstadisticasService;
import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;
import com.salesianostriana.dam.realbetis.Jugador.Model.Jugador;
import com.salesianostriana.dam.realbetis.Jugador.service.FutbolistaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final FutbolistaService futbolistaService;
    private final EstadisticasService estadisticasService;

    public EquipoService(EquipoRepository equipoRepository, 
                        @Lazy FutbolistaService futbolistaService, 
                        @Lazy EstadisticasService estadisticasService) {
        this.equipoRepository = equipoRepository;
        this.futbolistaService = futbolistaService;
        this.estadisticasService = estadisticasService;
    }

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



    public List<Jugador> findMaxGoleadores(Long id) {
        
        return equipoRepository.findById(id).stream()
            .flatMap(e -> e.getListFutbolistas().stream())
            .filter(f -> f instanceof Jugador)
            .map(f -> (Jugador) f)
            .sorted((j1, j2) -> {
                int  goles1 = ((EstadisticasJugador) estadisticasService.getEstadisticasByFutbolista(j1.getId())).getGoles();
                int  goles2 = ((EstadisticasJugador) estadisticasService.getEstadisticasByFutbolista(j2.getId())).getGoles();
                return Integer.compare(goles2, goles1); 
            })
            
            .collect(Collectors.toList());
    }


    public List <Jugador> findMaxAsistentes(Long id){

        return equipoRepository.findById(id).stream()
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


