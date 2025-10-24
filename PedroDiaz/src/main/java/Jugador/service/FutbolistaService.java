package Jugador.service;

import Estadisticas.Model.Estadisticas;
import Estadisticas.Model.EstadisticasJugador;
import Estadisticas.Model.EstadisticasPortero;
import Estadisticas.service.EstadisticasService;
import Jugador.Model.Futbolista;
import Jugador.Model.Jugador;
import Jugador.Model.Portero;
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
    private final EstadisticasService estadisticasService;

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

    public double calcularSalarioConExtra(Long id){
        int totalGoles = 0, totalAsistencias = 0;
        double extras=0.0, golesBonus=1000, asistenciasBonus=500;
        Futbolista futbolista = findFutbolistaById(id);
        if (futbolista instanceof Jugador jugador){
            Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
            totalGoles = ((EstadisticasJugador)estadisticas).getGoles();
            totalAsistencias = ((EstadisticasJugador)estadisticas).getAsistencias();
             extras = (totalGoles * golesBonus) + (totalAsistencias * asistenciasBonus);
            return extras;

        }
        if (futbolista instanceof Portero portero){
            int totalPorteriasACero = 0;
            double porteriasImbatidasBonus = 800;
            Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
            totalPorteriasACero = ((EstadisticasPortero)estadisticas).getPorteriasACero();
            extras = totalPorteriasACero * porteriasImbatidasBonus;
            return extras;
        }
        return extras+futbolista.getSalarioMensualBase();
    }






}
