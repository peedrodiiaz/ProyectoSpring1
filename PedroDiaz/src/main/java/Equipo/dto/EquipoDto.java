package Equipo.dto;

import Equipo.Model.Equipo;
import Futbolista.Model.Futbolista;
import Futbolista.repository.FutbolistaRepository;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDto {

    private FutbolistaRepository futbolistaRepository;
    

    private Long id;

    private String nombre;

    private String categoria;

    private String imgEquipo;

    private List<Long> jugadoresIds;


    public Equipo dtoToEntity(EquipoDto dto) {
        List<Futbolista> futbolistas = dto.getJugadoresIds() != null ?
            dto.getJugadoresIds().stream()
                .map(id -> futbolistaRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
            : null;

        return Equipo.builder()
            .id(dto.getId())
            .nombre(dto.getNombre())
            .categoria(dto.getCategoria())
            .imgEquipo(dto.getImgEquipo())
            .listFutbolistas(futbolistas)
            .build();
}



}
