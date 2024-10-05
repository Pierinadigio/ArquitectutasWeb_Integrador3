package org.example.services;

import org.example.DTO.EstudianteDTO;
import org.example.model.Estudiante;
import org.example.repository.interfaces.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    public List<EstudianteDTO> listarEstudiantesOrdenados(String ordenPor) {
        List<Estudiante> estudiantes = estudianteRepository.listarEstudiantesOrdenados(ordenPor);
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

    public EstudianteDTO findByNumeroLibreta(String numeroLibreta) {
        Estudiante estudiante = estudianteRepository.findByNumeroLibreta(numeroLibreta);
        if (estudiante != null) {
            return new EstudianteDTO(
                    estudiante.getDni(),
                    estudiante.getNombres(),
                    estudiante.getApellido(),
                    estudiante.getGenero(),
                    estudiante.getCiudadResidencia(),
                    estudiante.getNumeroLibretaUniversitaria()
            );
        }
        return null;
    }

    public List<EstudianteDTO> findByGenero(String genero) {
        List<Estudiante> estudiantes = estudianteRepository.findByGenero(genero);
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


    public Estudiante agregarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }
}

