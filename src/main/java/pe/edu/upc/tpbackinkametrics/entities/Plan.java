package pe.edu.upc.tpbackinkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "monthly_price", nullable = false)
    private Double monthlyPrice;

    @Column(name = "api_limit", nullable = false)
    private int apiLimit;

    @JsonIgnore
    @OneToMany(mappedBy = "plan")
    private List<Company> companies;

    public Plan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(Double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public Integer getApiLimit() {
        return apiLimit;
    }

    public void setApiLimit(Integer apiLimit) {
        this.apiLimit = apiLimit;
    }
}
