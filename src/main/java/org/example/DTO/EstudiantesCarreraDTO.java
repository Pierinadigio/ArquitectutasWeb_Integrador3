package org.example.DTO;

import lombok.Data;
import lombok.Getter;

@Getter
public class EstudiantesCarreraDTO {
    private String nombreCarrera;
    private long cantidadEstudiantes;

    public EstudiantesCarreraDTO(String nombreCarrera, long cantidadEstudiantes) {
        this.nombreCarrera = nombreCarrera;
        this.cantidadEstudiantes = cantidadEstudiantes;
    }


    @Override
    public String toString() {
        return "Carrera: " + nombreCarrera + ", Inscritos: " + cantidadEstudiantes;
    }
}
