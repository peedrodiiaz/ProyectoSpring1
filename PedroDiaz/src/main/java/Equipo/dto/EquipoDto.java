package Equipo.dto;

import Equipo.Model.Equipo;
import Jugador.Model.Jugador;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDto {

    private Long id;


    private String nombre;

    private String categoria;

    private String imgEquipo;

    private List<Long> jugadoresIds;

    public Equipo dtoToEntity(EquipoDto dto) {
        return Equipo.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .Categoria(dto.getCategoria())
                .imgEquipo(dto.getImgEquipo())
                .listJugadores(dto.getJugadoresIds() != null ?
                        dto.getJugadoresIds().stream()
                                .map(id -> {
                                    Jugador jugador = new Jugador();
                                    jugador.setId(id);
                                    return jugador;
                                })
                                .collect(Collectors.toList()) : null)
                .build();
    }



}
