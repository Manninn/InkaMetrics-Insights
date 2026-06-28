package pe.edu.upc.tpbackinkametrics.dtos;

public class CompanyDTO {
    private int id;
    private String tradeName;
    private String ruc;
    private int planId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTradeName() { return tradeName; }
    public void setTradeName(String tradeName) { this.tradeName = tradeName; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public int getPlanId() { return planId; }
    public void setPlanId(int planId) { this.planId = planId; }
}
