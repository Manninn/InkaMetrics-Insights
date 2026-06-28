package pe.edu.upc.tpbackinkametrics.dtos;

public class PlanDTO {
    private int id;
    private String name;
    private Double monthlyPrice;
    private int apiLimit;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getMonthlyPrice() { return monthlyPrice; }
    public void setMonthlyPrice(Double monthlyPrice) { this.monthlyPrice = monthlyPrice; }

    public int getApiLimit() { return apiLimit; }
    public void setApiLimit(int apiLimit) { this.apiLimit = apiLimit; }
}
