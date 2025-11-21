package com.salesianostriana.dam.realbetis.Jugador.service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.realbetis.DTOs.JugadorEditDTO;
import com.salesianostriana.dam.realbetis.DTOs.PorteroEditDTO;
import com.salesianostriana.dam.realbetis.Equipo.service.EquipoService;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.Estadisticas;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasJugador;
import com.salesianostriana.dam.realbetis.Estadisticas.Model.EstadisticasPortero;
import com.salesianostriana.dam.realbetis.Estadisticas.service.EstadisticasService;
import com.salesianostriana.dam.realbetis.Jugador.Model.Futbolista;
import com.salesianostriana.dam.realbetis.Jugador.Model.Jugador;
import com.salesianostriana.dam.realbetis.Jugador.Model.Portero;
import com.salesianostriana.dam.realbetis.Jugador.repository.FutbolistaRepository;
import com.salesianostriana.dam.realbetis.Jugador.repository.JugadorRepository;

import java.util.List;

@Service
public class FutbolistaService {
    private final FutbolistaRepository futbolistaRepository;
    private final EstadisticasService estadisticasService;
    private final EquipoService equipoService;

    public FutbolistaService(JugadorRepository jugadorRepository,
                            FutbolistaRepository futbolistaRepository,
                            EquipoService equipoService,
                            @Lazy EstadisticasService estadisticasService) {
        this.equipoService = equipoService;
        this.futbolistaRepository = futbolistaRepository;
        this.estadisticasService = estadisticasService;
    }

    public List<Futbolista> getAllFutbolistas() {
        return futbolistaRepository.findAll();
    }

    public Futbolista findFutbolistaById(Long id) {
        return futbolistaRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Futbolista no encontrado"));
                
    }


    

    public void deleteFutbolista(Long id) {
        
        futbolistaRepository.deleteById(id);

    }

    public List<Futbolista> findFutbolistaByEquipoId(Long equipoId) {
        return futbolistaRepository.findByEquipoId(equipoId);
    }
    public Futbolista saveFutbolista(Futbolista f) {
        return futbolistaRepository.save(f);
        }

    //EDITAR
    // Jugador
    public Futbolista updateJugador(Long id, JugadorEditDTO dto) {
        Futbolista futbolista = findFutbolistaById(id);
        boolean hacer = true;
        List <Futbolista> listaFutbolistas = futbolista.getEquipo().getListFutbolistas();
        Jugador jugador = (Jugador) futbolista;
        // Comprobar que ningún otro jugador (excepto el que editamos) tiene el mismo dorsal que el enviado en el DTO
        hacer = listaFutbolistas.stream()

            .filter(f -> !f.getId().equals(id))
            .allMatch(f -> f.getNumCamiseta() != dto.getNumCamiseta());
            if (!hacer) {   
                throw new IllegalArgumentException("Dos jugadores no pueden tener el mismo dorsal en el mismo equipo");
            }

            jugador.setNombre(dto.getNombre());
            jugador.setApellidos(dto.getApellidos());
            jugador.setNumCamiseta(dto.getNumCamiseta());
            jugador.setFechaNacimiento(dto.getFechaNacimiento());
            jugador.setFechaInicioContrato(dto.getFechaInicioContrato());
            jugador.setPiernaBuena(dto.getPiernaBuena());
            jugador.setImgFutbolista(dto.getImgFutbolista());
            jugador.setSalarioMensualBase(dto.getSalarioMensualBase());
            jugador.setPosicion(dto.getPosicion());
            
            jugador.setEquipo(equipoService.findById(dto.getEquipoId()));
            jugador.setNacionalidad(dto.getNacionalidad());
            
            
            // Actualizar estadísticas
            if (jugador.getEstadisticas() instanceof EstadisticasJugador) {
                EstadisticasJugador stats = (EstadisticasJugador) jugador.getEstadisticas();
                stats.setMinJugados(dto.getMinJugados());
                stats.setGoles(dto.getGoles());
                stats.setAsistencias(dto.getAsistencias());
                stats.setTarAmarilla(dto.getTarAmarilla());
                stats.setTarRoja(dto.getTarRoja());
                stats.setCalificacion(dto.getCalificacion());
            }
        
        return futbolistaRepository.save(jugador);


}

    //Portero
    public Futbolista updatePortero(Long id, PorteroEditDTO dto) {
        Futbolista futbolista = findFutbolistaById(id);
            List <Futbolista> listaFutbolistas = futbolista.getEquipo().getListFutbolistas();

        Portero portero = (Portero) futbolista;
        boolean hacer = listaFutbolistas.stream()

            .filter(f -> !f.getId().equals(id))
            .allMatch(f -> f.getNumCamiseta() != dto.getNumCamiseta());
    
        if (!hacer) {
            throw new IllegalArgumentException("Dos jugadores no pueden tener el mismo dorsal en el mismo equipo");
        }
            
        
        portero.setNombre(dto.getNombre());
        portero.setApellidos(dto.getApellidos());
        portero.setNumCamiseta(dto.getNumCamiseta());
        portero.setFechaNacimiento(dto.getFechaNacimiento());
        portero.setFechaInicioContrato(dto.getFechaInicioContrato());
        portero.setPiernaBuena(dto.getPiernaBuena());
        portero.setImgFutbolista(dto.getImgFutbolista());
        portero.setSalarioMensualBase(dto.getSalarioMensualBase());
        portero.setManoDominante(dto.getManoDominante());
        portero.setNacionalidad(dto.getNacionalidad());
        
    
        if (dto.getEquipoId() != null) {
            portero.setEquipo(equipoService.findById(dto.getEquipoId()));
        }
        
        if (portero.getEstadisticas() instanceof EstadisticasPortero) {
            EstadisticasPortero stats = (EstadisticasPortero) portero.getEstadisticas();
            stats.setMinJugados(dto.getMinJugados());
            stats.setParadas(dto.getParadas());
            stats.setPorteriasACero(dto.getPorteriasACero());
            stats.setTarAmarilla(dto.getTarAmarilla());
            stats.setTarRoja(dto.getTarRoja());
            stats.setCalificacion(dto.getCalificacion());
        }
    
        
        
        return futbolistaRepository.save(portero);
}


    //Lógica de bonus
    public double calcularBonusSalario(Long id){
        int totalGoles = 0, totalAsistencias = 0;
        double extras=0.0, golesBonus=1000, asistenciasBonus=500, restarPorAmarilla=250, restarPorRoja=450;
        Futbolista futbolista = findFutbolistaById(id);
        int tarjetasAmarillas, tarjetasRojas;
        if (futbolista instanceof Jugador ){
            Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
            
            if ((estadisticas instanceof EstadisticasJugador)){
            EstadisticasJugador ej = (EstadisticasJugador) estadisticas;
            totalGoles = ej.getGoles();
            totalAsistencias = ej.getAsistencias();
            tarjetasAmarillas = ej.getTarAmarilla();
            tarjetasRojas = ej.getTarRoja();
            extras = (totalGoles * golesBonus) + (totalAsistencias * asistenciasBonus) - (tarjetasAmarillas * restarPorAmarilla) - (tarjetasRojas * restarPorRoja);
            return extras;
            }
        }
        if (futbolista instanceof Portero ){
            int totalPorteriasACero = 0;
            double porteriasImbatidasBonus = 800;
            Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
            
            if ((estadisticas instanceof EstadisticasPortero)) {
            EstadisticasPortero ep = (EstadisticasPortero) estadisticas;
            totalPorteriasACero = ep.getPorteriasACero();
            extras = totalPorteriasACero * porteriasImbatidasBonus;
            return extras;
            }
        }
        return extras;
    }


    public double calcularSalarioFutbolista(Long id){
        Futbolista  futbolista = findFutbolistaById(id);
        double extra=0.0;
        extra = calcularBonusSalario(id);

        return extra+futbolista.getSalarioMensualBase();
    }

    
    public Futbolista createJugador(JugadorEditDTO dto) {
        List <Futbolista> listaFutbolistas = equipoService.findById(dto.getEquipoId()).getListFutbolistas();
        boolean hacer = listaFutbolistas.stream()
            .allMatch(f -> f.getNumCamiseta() != dto.getNumCamiseta());
        if (!hacer) {
            throw new IllegalArgumentException("Ya existe un jugador con el dorsal " + dto.getNumCamiseta() + " en el equipo con ID " + dto.getEquipoId());
        }

        Jugador j = new Jugador();
        j.setNombre(dto.getNombre());
        j.setApellidos(dto.getApellidos());
        j.setImgFutbolista(dto.getImgFutbolista());
        j.setFechaNacimiento(dto.getFechaNacimiento());
        j.setFechaInicioContrato(dto.getFechaInicioContrato());
        j.setNacionalidad(dto.getNacionalidad());
        j.setNumCamiseta(dto.getNumCamiseta());
        j.setSalarioMensualBase(dto.getSalarioMensualBase());
        j.setPiernaBuena(dto.getPiernaBuena());
        j.setPosicion(dto.getPosicion());

        EstadisticasJugador stats = new EstadisticasJugador();
        stats.setAsistencias(dto.getAsistencias());
        stats.setGoles(dto.getGoles());
        stats.setCalificacion(dto.getCalificacion());
        stats.setMinJugados(dto.getMinJugados());
        stats.setTarAmarilla(dto.getTarAmarilla());
        stats.setTarRoja(dto.getTarRoja());

        stats.setFutbolista(j);
        j.setEstadisticas(stats);

        if (dto.getEquipoId() == null) {
            throw new IllegalArgumentException("equipoId es obligatorio al crear un Jugador");
        }
        j.setEquipo(equipoService.findById(dto.getEquipoId()));
        
        return futbolistaRepository.save(j);
    }

    public Portero createPortero (PorteroEditDTO dto){
        List <Futbolista> listaFutbolistas = equipoService.findById(dto.getEquipoId()).getListFutbolistas();
        boolean hacer = listaFutbolistas.stream()
            .allMatch(f -> f.getNumCamiseta() != dto.getNumCamiseta());
        if (!hacer) {
            throw new IllegalArgumentException("Ya existe un portero con el dorsal " + dto.getNumCamiseta() + " en el equipo con ID " + dto.getEquipoId());
        }

        Portero p = new Portero();
        p.setNombre(dto.getNombre());
        p.setApellidos(dto.getApellidos());
        p.setImgFutbolista(dto.getImgFutbolista());
        p.setFechaNacimiento(dto.getFechaNacimiento());
        p.setFechaInicioContrato(dto.getFechaInicioContrato());
        p.setNacionalidad(dto.getNacionalidad());
        p.setNumCamiseta(dto.getNumCamiseta());
        p.setSalarioMensualBase(dto.getSalarioMensualBase());
        p.setPiernaBuena(dto.getPiernaBuena());
        p.setManoDominante(dto.getManoDominante());

        EstadisticasPortero stats = new EstadisticasPortero();
        stats.setCalificacion(dto.getCalificacion());
        stats.setMinJugados(dto.getMinJugados());
        stats.setParadas(dto.getParadas());
        stats.setPorteriasACero(dto.getPorteriasACero());
        stats.setFutbolista(p);
        p.setEstadisticas(stats);
        if (dto.getEquipoId() == null) {
            throw new IllegalArgumentException("equipoId es obligatorio al crear un Portero");
        }
        p.setEquipo(equipoService.findById(dto.getEquipoId()));
        return futbolistaRepository.save(p);

    }
    //CALCULO CALIFICACION FUTBOLISTA
    // Cálculo  Jugador 
    private double calcularCalificacionJugador(Long id) {
        Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
        int goles, asistencias, tarAmarilla, tarRoja;
        double minJugados, golesPorPartidoSuma, asistenciaPorPartidoSuma, amarillasResta, rojaResta;

        EstadisticasJugador ej = (EstadisticasJugador) estadisticas;
        goles = ej.getGoles();
        asistencias = ej.getAsistencias();
        minJugados = ej.getMinJugados();
        tarAmarilla = ej.getTarAmarilla();
        tarRoja = ej.getTarRoja();

        double partidos = (minJugados > 0) ? (minJugados / 90.0) : 0.0;
        double golesPorPartido = (partidos > 0) ? (goles / partidos) : 0.0;
        double asistenciasPorPartido = (partidos > 0) ? (asistencias / partidos) : 0.0;

        golesPorPartidoSuma = 1.9;
        asistenciaPorPartidoSuma = 0.5;
        amarillasResta = 0.2;
        rojaResta = 0.5;

        return (golesPorPartido * golesPorPartidoSuma)
                + (asistenciasPorPartido * asistenciaPorPartidoSuma)
                - (tarAmarilla * amarillasResta)
                - (tarRoja * rojaResta);
    }

    // Cálculo PorteroO
    private double calcularCalificacionPortero(Long id) {
        Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
        int paradas, porteriasACero, tarAmarilla, tarRoja;
        double minJugados, partidos, paradasPorPartido, porteriasACeroPorPartido;
        double paradasPorPartidoSuma, porteriasPorPartidoSuma, amarillasResta, rojaResta;

        if (!(estadisticas instanceof EstadisticasPortero))
            return 0.0;

        EstadisticasPortero ep = (EstadisticasPortero) estadisticas;
        paradas = ep.getParadas();
        porteriasACero = ep.getPorteriasACero();
        minJugados = ep.getMinJugados();
        tarAmarilla = ep.getTarAmarilla();
        tarRoja = ep.getTarRoja();

        partidos = (minJugados > 0) ? (minJugados / 90.0) : 0.0;
        paradasPorPartido = (partidos > 0) ? (paradas / partidos) : 0.0;
        porteriasACeroPorPartido = (partidos > 0) ? (porteriasACero / partidos) : 0.0;

        paradasPorPartidoSuma = 0.5;
        porteriasPorPartidoSuma = 1.0;
        amarillasResta = 0.2;
        rojaResta = 0.5;

        return (paradasPorPartido * paradasPorPartidoSuma)
                + (porteriasACeroPorPartido * porteriasPorPartidoSuma)
                - (tarAmarilla * amarillasResta)
                - (tarRoja * rojaResta);
    }
    public double calcularCalificacionFutbolista(Long id){
        Futbolista futbolista = findFutbolistaById(id);
        if (futbolista instanceof Jugador) {
            return calcularCalificacionJugador(id);
        } else if (futbolista instanceof Portero) {
            return calcularCalificacionPortero(id);
        }
        return 0.0;
    }

    public void actualizarCalificacionFutbolista(Long id){
        double cal = calcularCalificacionFutbolista(id);
        double resul;
        Estadisticas estadisticas = estadisticasService.getEstadisticasByFutbolista(id);
        if (estadisticas != null) {
            resul = Math.min(estadisticas.getCalificacion() + cal, 10.0);
            estadisticas.setCalificacion(resul);
            estadisticasService.saveEstadistica(estadisticas);
        }
    }



}













