package Equipo.Model;

import Equipo.dto.EquipoDto;
import Jugador.Model.Futbolista;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String Categoria;
    private List<Futbolista> listJugadores;
    private String imgEquipo;

   public EquipoDto EntityconverToDto(Equipo equipo) {
       return EquipoDto.builder()
               .id(equipo.getId())
               .nombre(equipo.getNombre())
               .categoria(equipo.getCategoria())
               .imgEquipo(equipo.getImgEquipo())
               .build();
   }


}
