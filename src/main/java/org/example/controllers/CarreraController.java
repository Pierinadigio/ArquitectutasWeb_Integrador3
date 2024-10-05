package org.example.controllers;

import org.example.DTO.EstudiantesCarreraDTO;
import org.example.model.Carrera;
import org.example.model.Estudiante;
import org.example.services.CarreraService;
import org.example.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @PostMapping
    public Carrera agregarCarrera(@RequestBody Carrera carrera) {
         return carreraService.agregarCarrera(carrera);
    }


}
