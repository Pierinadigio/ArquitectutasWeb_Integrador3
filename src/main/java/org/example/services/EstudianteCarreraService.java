package org.example.services;
import org.example.DTO.EstudianteDTO;
import org.example.DTO.EstudiantesCarreraDTO;
import org.example.DTO.ReporteDTO;
import org.example.model.Carrera;
import org.example.model.Estudiante;
import org.example.model.Estudiante_Carrera;
import org.example.repository.interfaces.CarreraRepository;
import org.example.repository.interfaces.EstudianteCarreraRepository;
import org.example.repository.interfaces.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository carreraRepository;


    public void matricularEstudiante(String dniEstudiante, long idCarrera, Date fechaInscripcion) {
        Estudiante estudiante = estudianteRepository.findById(dniEstudiante)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con DNI: " + dniEstudiante));

        Carrera carrera = carreraRepository.findById(idCarrera)
                .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada con ID: " + idCarrera));

        Estudiante_Carrera estudianteCarrera = new Estudiante_Carrera();
        estudianteCarrera.setEstudiante(estudiante);
        estudianteCarrera.setCarrera(carrera);
        estudianteCarrera.setFechaInscripcion(fechaInscripcion);
        estudianteCarrera.setAntiguedad();
        estudianteCarrera.setGraduado(estudiante.isGraduado());

        estudianteCarreraRepository.save(estudianteCarrera);
    }


    public List<EstudiantesCarreraDTO> obtenerCarrerasConEstudiantesInscritos() {
        try {
            return estudianteCarreraRepository.obtenerCarrerasConEstudiantesInscritos();
        } catch (Exception e) {
           e.printStackTrace();
            throw e;
        }
    }


    public List<EstudianteDTO> obtenerEstudiantesPorCarreraYCiudad(long idCarrera, String ciudad) {
        List<Estudiante> estudiantes = estudianteCarreraRepository.obtenerEstudiantesPorCarreraYCiudad(idCarrera, ciudad);

        return estudiantes.stream()
                .map(e -> new EstudianteDTO(
                        e.getDni(),
                        e.getNombres(),
                        e.getApellido(),
                        e.getGenero(),
                        e.getCiudadResidencia(),
                        e.getNumeroLibretaUniversitaria()))
                .collect(Collectors.toList());
    }



    public List<ReporteDTO> generarReporteCarreras() {
        List<Carrera> carreras = carreraRepository.findAll();
        List<ReporteDTO> reportes = new ArrayList<>();

        for (Carrera carrera : carreras) {
           List<Object[]> resultados = estudianteCarreraRepository.countInscriptosYEgresadosPorAnio(carrera.getId());

           Map<Integer, Long> inscriptosPorAno = resultados.stream()
                    .collect(Collectors.toMap(
                            e -> (Integer) e[0],
                            e -> (Long) e[1]
                    ));

            Map<Integer, Long> egresadosPorAno = resultados.stream()
                    .collect(Collectors.toMap(
                            e -> (Integer) e[0],
                            e -> (Long) e[2]
                    ));
            reportes.add(new ReporteDTO(carrera.getNombre(), inscriptosPorAno, egresadosPorAno));
        }

        return reportes;
    }

}