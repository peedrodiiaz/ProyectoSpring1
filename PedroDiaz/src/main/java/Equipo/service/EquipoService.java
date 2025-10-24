package Equipo.service;

import Equipo.Model.Equipo;
import Equipo.repository.EquipoRepository;
import Jugador.Model.Jugador;
import Jugador.dto.JugadorDto;
import Jugador.repository.JugadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;

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
        eq.setListJugadores(eq.getListJugadores());
        return equipoRepository.save(eq);

    }

    public void deleteEquipo(Long id){
        equipoRepository.deleteById(id);
    }


    public List <JugadorDto> getJugadoresByEquipoId(Long id){
        List <Jugador>jugadoresDto= jugadorRepository.findByEquipoId(id);
        return jugadoresDto.stream()
                .map(Jugador::entityConverToDto )
                .collect(Collectors.toList());
    }


}
