package pe.edu.upc.tpbackinkametrics.dtos;

public class SumaPublicidadDTO {

    private String tipo;
    private Long totalDuracionSeg;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getTotalDuracionSeg() {
        return totalDuracionSeg;
    }

    public void setTotalDuracionSeg(Long totalDuracionSeg) {
        this.totalDuracionSeg = totalDuracionSeg;
    }
}
