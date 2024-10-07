package org.example.DTO;

import lombok.Data;
import lombok.Getter;

import java.util.Map;
@Getter
public class ReporteDTO {
    private String nombreCarrera;
    private Map<Integer, Long> inscriptosPorAno;
    private Map<Integer, Long> egresadosPorAno;


    public ReporteDTO(String nombre, Map<Integer, Long> inscriptos, Map<Integer, Long> egresadosPorAno) {
        this.nombreCarrera = nombre;
        this.inscriptosPorAno = inscriptos;
        this.egresadosPorAno = egresadosPorAno;
    }


    @Override
    public String toString() {
        return "Carrera:  " + nombreCarrera + ", Inscriptos:  " + inscriptosPorAno + ", Egresados:  " + egresadosPorAno;
    }
}
