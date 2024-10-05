package org.example.model;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@jakarta.persistence.Entity
@Data
public class Estudiante_Carrera {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int antiguedad;

    private Date fechaInscripcion;

    @Column(name = "graduado")
    private boolean isGraduado;

    @ManyToOne
    @jakarta.persistence.JoinColumn(name = "carrera_id")
    private Carrera carrera;

    @ManyToOne
    @jakarta.persistence.JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;


    public int calcularAntiguedad() {
        if (fechaInscripcion == null) {
            return 0; // o lanzar una excepción según tu caso
        }
        long diferenciaMillis = new Date().getTime() - fechaInscripcion.getTime();
        long diferenciaDias = TimeUnit.DAYS.convert(diferenciaMillis, TimeUnit.MILLISECONDS);

        return (int) (diferenciaDias / 365);
    }

    public void setAntiguedad() {
        this.antiguedad = calcularAntiguedad();
    }


}
