package org.example.controllers;

import org.example.DTO.EstudianteDTO;
import org.example.DTO.EstudiantesCarreraDTO;
import org.example.DTO.ReporteDTO;
import org.example.services.EstudianteCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/estudiantes-carreras")
public class EstudianteCarreraController {

    @Autowired
    private EstudianteCarreraService estudianteCarreraService;

    @PostMapping("/matricular")
    public ResponseEntity<String> matricularEstudiante(@RequestParam String dniEstudiante,
                                                       @RequestParam long idCarrera,
                                                       @RequestParam String fechaInscripcion) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInscripcionDate;
        try {
            fechaInscripcionDate = sdf.parse(fechaInscripcion);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Fecha de inscripción no es válida: " + fechaInscripcion);
        }
        try {
            estudianteCarreraService.matricularEstudiante(dniEstudiante, idCarrera, fechaInscripcionDate);
            return ResponseEntity.ok("Estudiante matriculado exitosamente.");
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al matricular al estudiante: " + e.getMessage());
        }
    }

    @GetMapping("/carreras-estudiantes")
    public ResponseEntity<List<EstudiantesCarreraDTO>> obtenerCarrerasConEstudiantesInscritos() {
        try {
            List<EstudiantesCarreraDTO> result = estudianteCarreraService.obtenerCarrerasConEstudiantesInscritos();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/carrera/{idCarrera}/ciudad/{ciudad}")
    public List<EstudianteDTO> obtenerEstudiantesPorCarreraYCiudad(@PathVariable long idCarrera, @PathVariable String ciudad) {
        return estudianteCarreraService.obtenerEstudiantesPorCarreraYCiudad(idCarrera, ciudad);
    }

    @GetMapping("/reportes")
    public List<ReporteDTO> obtenerReportes(){
        return estudianteCarreraService.generarReporteCarreras();
    }
}
