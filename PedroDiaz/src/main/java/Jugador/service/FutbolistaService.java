package Jugador.service;

import Jugador.Model.Futbolista;
import Jugador.repository.FutbolistaRepository;
import Jugador.repository.JugadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FutbolistaService {
    private final JugadorRepository jugadorRepository;
    private final FutbolistaRepository futbolistaRepository;

    public List<Futbolista> getAllFutbolistas() {
        return futbolistaRepository.findAll();
    }

    public Futbolista findFutbolistaById(Long id) {
        return futbolistaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Futbolista no encontrado"));
    }


    public Futbolista createFutbolista(Futbolista futbolista) {
        return futbolistaRepository.save(futbolista);
    }

    public Futbolista updateFutbolista(Long id, Futbolista futbolistaDetails) {
        Futbolista futbolista = findFutbolistaById(id);
        futbolista.setNombre(futbolistaDetails.getNombre());
        futbolista.setApellidos(futbolistaDetails.getApellidos());
        futbolista.setImgFutbolista(futbolistaDetails.getImgFutbolista());
        futbolista.setFechaNacimiento(futbolistaDetails.getFechaNacimiento());
        futbolista.setFechaInicioContrato(futbolistaDetails.getFechaInicioContrato());
        futbolista.setNacionalidad(futbolistaDetails.getNacionalidad());
        futbolista.setNumCamiseta(futbolistaDetails.getNumCamiseta());
        futbolista.setSalarioMensualBase(futbolistaDetails.getSalarioMensualBase());
        futbolista.setEquipo(futbolistaDetails.getEquipo());
        return jugadorRepository.save(futbolista);
    }

    public void deleteFutbolista(Long id) {
        futbolistaRepository.deleteById(id);
    }

    public List<Futbolista> findFutbolistaByEquipoId(Long equipoId) {
        return futbolistaRepository.findByEquipoId(equipoId);
    }
    public List<Futbolista> findJugadorPorEdad(Long id){
        return futbolistaRepository.findByEdad(id);
    }

    public List<Futbolista> findByPosicion(Long id){
        return futbolistaRepository.findByPosicion(id);
    }

//    public double calcularExtrasSalario(Long id){}






}
