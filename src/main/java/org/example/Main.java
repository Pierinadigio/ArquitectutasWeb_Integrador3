package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
/*
    private static void cargarEstudiantesDesdeCSV(EstudianteRepository estudianteRepository, String archivoCsv) {
        try (CSVReader csvReader = new CSVReader(new FileReader(archivoCsv))) {
            String[] fila;
            while ((fila = csvReader.readNext()) != null) {
                Estudiante estudiante = new Estudiante();
                estudiante.setDni(fila[0]);
                estudiante.setNombres(fila[1]);
                estudiante.setApellido(fila[2]);
                estudiante.setEdad(Integer.parseInt(fila[3]));
                estudiante.setGenero(fila[4]);
                estudiante.setNumeroLibretaUniversitaria(fila[5]);
                estudiante.setCiudadResidencia(fila[6]);

                estudianteRepository.insert(estudiante);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cargarCarrerasDesdeCSV(CarreraRepository carreraRepository, String archivoCsv) {
        try (CSVReader csvReader = new CSVReader(new FileReader(archivoCsv))) {
            String[] fila;
            while ((fila = csvReader.readNext()) != null) {
                Carrera carrera = new Carrera();
                carrera.setNombre(fila[1]);

                carreraRepository.insert(carrera);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}