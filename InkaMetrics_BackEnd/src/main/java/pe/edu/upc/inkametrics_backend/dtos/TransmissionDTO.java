package pe.edu.upc.inkametrics_backend.dtos;

import java.time.LocalDate;

public class TransmissionDTO {
    private int idTransmission;
    private String titleStream;
    private LocalDate dateStart;
    private LocalDate dateEnd;

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
