package org.example.service;
import org.example.model.Carrera;
import org.example.model.Estudiante;
import org.example.repository.interfaces.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;


    public Carrera agregarCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    public List<Carrera> agregarCarreras(List<Carrera> carerras) {
        return carreraRepository.saveAll(carerras);
    }

    public List<Carrera> listarCarreras() {
        return carreraRepository.findAll();
    }


}
