package org.example.DTO;

import java.util.Map;

public class ReporteDTO {
    private String nombreCarrera;
    private Map<Integer, Long> inscriptosPorAno;
    private Map<Integer, Long> egresadosPorAno;


    public ReporteDTO(String nombre, Map<Integer, Long> inscriptos, Map<Integer, Long> egresadosPorAno) {
        this.nombreCarrera = nombre;
        this.inscriptosPorAno = inscriptos;
        this.egresadosPorAno = egresadosPorAno;
    }
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public Map<Integer, Long> getInscriptosPorAno() {
        return inscriptosPorAno;
    }

    public Map<Integer, Long> getEgresadosPorAno() {
        return egresadosPorAno;
    }


    @Override
    public String toString() {
        return "Carrera:  " + nombreCarrera + ", Inscriptos:  " + inscriptosPorAno + ", Egresados:  " + egresadosPorAno;
    }
}
