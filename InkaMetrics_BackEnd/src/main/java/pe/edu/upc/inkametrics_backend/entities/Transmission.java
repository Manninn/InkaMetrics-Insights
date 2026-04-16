package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Transmission")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransmission;

    @Column(name = "titleStream",nullable = false)
    private String titleStream;

    @Column(name = "dateStart", nullable = false)
    private LocalDate dateStart;

    @Column(name = "dateEnd",nullable = false)
    private LocalDate dateEnd;

    public Transmission() {
    }

    public Transmission(int idTransmission, String titleStream, LocalDate dateStart, LocalDate dateEnd) {
        this.idTransmission = idTransmission;
        this.titleStream = titleStream;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getIdTransmission() {
        return idTransmission;
    }

    public void setIdTransmission(int idTransmission) {
        this.idTransmission = idTransmission;
    }

    public String getTitleStream() {
        return titleStream;
    }

    public void setTitleStream(String titleStream) {
        this.titleStream = titleStream;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
