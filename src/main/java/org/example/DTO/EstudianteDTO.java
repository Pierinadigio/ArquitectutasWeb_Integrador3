package org.example.DTO;

import lombok.Data;
import lombok.Getter;

@Getter
public class EstudianteDTO {
    private String dni;
    private String nombres;
    private String apellido;
    private String genero;
    private String ciudadResidencia;
    private String numeroLibretaUniversitaria;


    public EstudianteDTO(String dni, String nombres, String apellido, String genero, String ciudadResidencia ,String numeroLibretaUniversitaria) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellido = apellido;
        this.genero = genero;
        this.ciudadResidencia = ciudadResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;

    }

    @Override
    public String toString() {
        return "Estudiante" +
                " dni='" + dni + '\'' +
                ", nombres='" + nombres + '\'' +
                "apellido='" + apellido + '\''
                ;
    }
}
