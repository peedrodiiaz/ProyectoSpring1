package Equipo.service;

import Equipo.Model.Equipo;
import Equipo.repository.EquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        eq.setListJugadores(eq.getListJugadores());
        return equipoRepository.save(eq);

    }

    public void deleteEquipo(Long id){
        equipoRepository.deleteById(id);
    }


}
