package Equipo.service;

import Equipo.Model.Equipo;
import Equipo.repository.EquipoRepository;
import Jugador.Model.Futbolista;
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
            if (equipo.getListFutbolistas() == null) return 0.0;
            return equipo.getListFutbolistas().stream()
                    .mapToDouble(Futbolista::getSalarioMensualBase)
                    .sum();
        }





}
