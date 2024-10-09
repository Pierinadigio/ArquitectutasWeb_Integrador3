package org.example.controller;
import org.example.DTO.EstudianteDTO;
import org.example.model.Estudiante;
import org.example.service.EstudianteService;
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

    @PostMapping("/agregar")
    public List<Estudiante> agregarEstudiantes(@RequestBody List<Estudiante> estudiantes) {
        return estudianteService.agregarEstudiantes(estudiantes);
    }

   // Lista Estudiantes ORDENADOS por algún criterio: "apellido", "nombre", "libreta", "edad", "ciudad" o "dni"
   @GetMapping
   public List<EstudianteDTO> listarEstudiantesOrdenados(@RequestParam (required = false, defaultValue = "dni") String ordenPor){
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