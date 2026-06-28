package pe.edu.upc.tpbackinkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "trade_name", nullable = false, length = 100)
    private String tradeName;

    @Column(name = "ruc", nullable = false, length = 11)
    private String ruc;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<MonitoredChannel> monitoredChannels;

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
