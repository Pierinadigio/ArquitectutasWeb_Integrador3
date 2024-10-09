package org.example.service;

import org.example.DTO.EstudianteDTO;
import org.example.model.Estudiante;
import org.example.repository.interfaces.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    public List<EstudianteDTO> listarEstudiantesOrdenados(String ordenPor) {
        List<String> criteriosValidos = Arrays.asList("apellido", "nombre", "libreta", "edad", "ciudad", "dni" );
        if (!criteriosValidos.contains(ordenPor)) {
            throw new IllegalArgumentException("Criterio de ordenación no válido: " + ordenPor);
        }
        List<Estudiante> estudiantes = estudianteRepository.listarEstudiantesOrdenados(ordenPor);
        return estudiantes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EstudianteDTO findByNumeroLibreta(String numeroLibreta) {
        Estudiante estudiante = estudianteRepository.findByNumeroLibreta(numeroLibreta);
        return (estudiante != null) ? mapToDTO(estudiante) : null;

    }

    public List<EstudianteDTO> findByGenero(String genero) {
        List<Estudiante> estudiantes = estudianteRepository.findByGenero(genero);
        return estudiantes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public Estudiante agregarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> agregarEstudiantes(List<Estudiante> estudiantes) {
        return estudianteRepository.saveAll(estudiantes);
    }


        private EstudianteDTO mapToDTO(Estudiante estudiante) {
            return new EstudianteDTO(
                    estudiante.getDni(),
                    estudiante.getNombres(),
                    estudiante.getApellido(),
                    estudiante.getEdad(),
                    estudiante.getGenero(),
                    estudiante.getCiudadResidencia(),
                    estudiante.getNumeroLibretaUniversitaria()
            );
        }

}

