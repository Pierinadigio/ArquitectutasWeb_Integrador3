package org.example.repository.interfaces;

import org.example.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    // Lista Estudiantes ORDENADOS por algún criterio
    @Query("SELECT e FROM Estudiante e " +
            "ORDER BY CASE " +
            "WHEN :ordenPor = 'apellido' THEN e.apellido " +
            "WHEN :ordenPor = 'nombre' THEN e.nombres " +
            "WHEN :ordenPor = 'libreta' THEN e.numeroLibretaUniversitaria " +
            "WHEN :ordenPor = 'edad' THEN e.edad " +
            "WHEN :ordenPor = 'ciudad' THEN e.ciudadResidencia " +
            "END")
    List<Estudiante> listarEstudiantesOrdenados(@Param("ordenPor")String ordenPor);

    // Recupera estudiante por número de libreta
    @Query("SELECT e FROM Estudiante e WHERE e.numeroLibretaUniversitaria = :numeroLibreta")
    Estudiante findByNumeroLibreta(@Param("numeroLibreta")String numeroLibreta);

    // Recupera todos los estudiantes en base a su género
    @Query("SELECT e FROM Estudiante e WHERE e.genero = :genero ORDER BY e.apellido, e.nombres")
    List<Estudiante> findByGenero(@Param("genero")String genero);



}
