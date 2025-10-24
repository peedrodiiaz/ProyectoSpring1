package Jugador.service;

import Jugador.Model.Futbolista;
import Jugador.repository.JugadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JugadorService {
    private final JugadorRepository jugadorRepository;

    public List<Futbolista> getAllJugadores() {
        return jugadorRepository.findAll();
    }

    public Futbolista findJugadorById(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Jugador no encontrado"));
    }


    public Futbolista createJugador(Futbolista futbolista) {
        return jugadorRepository.save(futbolista);
    }

    public Futbolista updateJugador(Long id, Futbolista futbolistaDetails) {
        Futbolista futbolista = findJugadorById(id);
        futbolista.setNombre(futbolistaDetails.getNombre());
        futbolista.setApellidos(futbolistaDetails.getApellidos());
        futbolista.setImgJugador(futbolistaDetails.getImgJugador());
        futbolista.setFechaNacimiento(futbolistaDetails.getFechaNacimiento());
        futbolista.setFechaInicioContrato(futbolistaDetails.getFechaInicioContrato());
        futbolista.setNacionalidad(futbolistaDetails.getNacionalidad());
        futbolista.setNumCamiseta(futbolistaDetails.getNumCamiseta());
        futbolista.setSalarioMensualBase(futbolistaDetails.getSalarioMensualBase());
        futbolista.setEquipo(futbolistaDetails.getEquipo());
        return jugadorRepository.save(futbolista);
    }

    public void deleteJugador(Long id) {
        jugadorRepository.deleteById(id);
    }

    public List<Futbolista> findJugadoresByEquipo(Long equipoId) {
        return jugadorRepository.findByEquipoId(equipoId);
    }
    public List<Futbolista> findJugadorPorEdad(Long id){
        return jugadorRepository.findByEdad(id);
    }

    public List<Futbolista> findByPosicion(Long id){
        return jugadorRepository.findByPosicion(id);
    }

//    public double calcularExtrasSalario(Long id){}






}
