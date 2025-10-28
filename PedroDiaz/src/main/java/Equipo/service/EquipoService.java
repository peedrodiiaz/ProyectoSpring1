package Equipo.service;

import Equipo.Model.Equipo;
import Equipo.repository.EquipoRepository;
import Jugador.Model.Futbolista;
import Jugador.Model.Jugador;
import Jugador.repository.JugadorRepository;
import Jugador.service.FutbolistaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final FutbolistaService futbolistaService;

    private List<Equipo> findAll(){
        return equipoRepository.findAll();
    }


    private Equipo findById (Long id){
        return equipoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id+"No encontrado"));
    }
    private Equipo createEquipo (Equipo eq){
        return equipoRepository.save(eq);
    }

    private Equipo updateEquipo (Long id, Equipo equipoUpdate){
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

    public List <Jugador> findMaxGoleadores(){
        
    }




    






}


