package org.example.service;
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
                        e.getEdad(),
                        e.getGenero(),
                        e.getCiudadResidencia(),
                        e.getNumeroLibretaUniversitaria()))
                .collect(Collectors.toList());
    }


public List<ReporteDTO> generarReporteCarreras() {
    List<Object[]> resultados = estudianteCarreraRepository.countInscriptosYEgresadosPorCarrera();
    Map<String, Map<Integer, Long[]>> reportesMap = new HashMap<>();

    for (Object[] resultado : resultados) {
        String nombreCarrera = (String) resultado[0];
        int año = (Integer) resultado[1];
        long totalInscriptos = (Long) resultado[2];
        long totalEgresados = (Long) resultado[3];

        reportesMap
                .computeIfAbsent(nombreCarrera, k -> new HashMap<>())
                .put(año, new Long[]{totalInscriptos, totalEgresados});
    }

    List<ReporteDTO> reportes = new ArrayList<>();
    for (Map.Entry<String, Map<Integer, Long[]>> entry : reportesMap.entrySet()) {
        String nombreCarrera = entry.getKey();
        Map<Integer, Long[]> añosData = entry.getValue();
        Map<Integer, Long> inscriptosPorAno = new HashMap<>();
        Map<Integer, Long> egresadosPorAno = new HashMap<>();

        for (Map.Entry<Integer, Long[]> añoEntry : añosData.entrySet()) {
            inscriptosPorAno.put(añoEntry.getKey(), añoEntry.getValue()[0]);
            egresadosPorAno.put(añoEntry.getKey(), añoEntry.getValue()[1]);
        }
        reportes.add(new ReporteDTO(nombreCarrera, inscriptosPorAno, egresadosPorAno));
    }
    reportes.sort(Comparator.comparing(ReporteDTO::getNombreCarrera));

    return reportes;
}
}