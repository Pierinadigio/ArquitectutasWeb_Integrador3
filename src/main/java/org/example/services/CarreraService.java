package org.example.services;
import org.example.model.Carrera;
import org.example.repository.interfaces.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;


    public Carrera agregarCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }


}
