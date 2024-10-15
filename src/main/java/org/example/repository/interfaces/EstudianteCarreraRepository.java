package org.example.repository.interfaces;
import org.example.DTO.EstudiantesCarreraDTO;
import org.example.model.Estudiante;
import org.example.model.Estudiante_Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<Estudiante_Carrera, Integer> {

    // Obtiene carreras con estudiantes inscriptos
    @Query("SELECT new org.example.DTO.EstudiantesCarreraDTO(c.nombre, COUNT(ec.estudiante)) " +
            "FROM Carrera c JOIN Estudiante_Carrera ec ON c.id = ec.carrera.id " +
            "GROUP BY c.id, c.nombre " +
            "ORDER BY COUNT(ec.estudiante) DESC")
    List<EstudiantesCarreraDTO> obtenerCarrerasConEstudiantesInscritos();

    // Obtiene estudiantes por carrera y ciudad
    @Query("SELECT e FROM Estudiante_Carrera ec JOIN ec.estudiante e " +
            "WHERE ec.carrera.id = :idCarrera AND e.ciudadResidencia = :ciudad")
    List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(@Param("idCarrera") long idCarrera, @Param("ciudad") String ciudad);


    // Genera reporte de carreras con estudiantes inscriptos y egresados por año
    @Query("SELECT c.nombre, YEAR(ec.fechaInscripcion), " +
            "COUNT(ec) AS totalInscriptos, " +
            "SUM(CASE WHEN ec.isGraduado = true THEN 1 ELSE 0 END) AS totalEgresados " +
            "FROM Carrera c " +
            "LEFT JOIN Estudiante_Carrera ec ON ec.carrera.id = c.id " +
            "GROUP BY c.nombre, YEAR(ec.fechaInscripcion) " +
            "ORDER BY c.nombre, YEAR(ec.fechaInscripcion)")
    List<Object[]> countInscriptosYEgresadosPorCarrera();

}