package org.example.controller;

import org.example.model.Carrera;
import org.example.model.Estudiante;
import org.example.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/agregar")
    public List<Carrera> agregarCarreras(@RequestBody List<Carrera> carreras) {
        return carreraService.agregarCarreras(carreras);
    }

    @GetMapping
    public List<Carrera> listarCarreras() {
        return carreraService.listarCarreras();
    }


}
