package org.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.*;

@jakarta.persistence.Entity
@Data
public class Estudiante  {
    @jakarta.persistence.Id
    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    private String nombres;

    private String apellido;

    @Column(nullable = false)
    private int edad;

    private String genero;

    @Column(name = "ciudad_residencia")
    private String ciudadResidencia;

    private String numeroLibretaUniversitaria;

    private Boolean graduado= false;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estudiante_Carrera> carreras = new ArrayList<>();

    public boolean isGraduado() {
        return graduado;
    }
    @Override
    public String toString() {
        return "Estudiante" +
                ", dni='" + dni + '\'' +
                ", nombres='" + nombres + '\'' +
                "apellido='" + apellido + '\''
                ;
    }
}
