package org.example.controllers;
import org.example.DTO.EstudianteDTO;
import org.example.model.Carrera;
import org.example.model.Estudiante;
import org.example.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping
    public Estudiante AltaEsdudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.agregarEstudiante(estudiante);
    }


    @GetMapping
    public List<EstudianteDTO> listarEstudiantesOrdenados(@RequestParam(required = false) String ordenPor) {
        return estudianteService.listarEstudiantesOrdenados(ordenPor);
    }

    @GetMapping("/{numeroLibreta}")
    public EstudianteDTO obtenerEstudiantePorLibreta(@PathVariable String numeroLibreta) {
        return estudianteService.findByNumeroLibreta(numeroLibreta);
    }

    @GetMapping("/genero/{genero}")
    public List<EstudianteDTO> obtenerEstudiantesPorGenero(@PathVariable String genero) {
        return estudianteService.findByGenero(genero);
    }


}