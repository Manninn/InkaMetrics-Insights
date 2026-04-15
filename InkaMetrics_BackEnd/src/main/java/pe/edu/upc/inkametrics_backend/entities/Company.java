package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompany;

    @Column(name = "nameCompany", nullable = false)
    private String nameCompany;

    @Column(name = "rucCompany", nullable = false)
    private String rucCompany;

    public Company() {
    }

    public Company(int idCompany, String nameCompany, String rucCompany) {
        this.idCompany = idCompany;
        this.nameCompany = nameCompany;
        this.rucCompany = rucCompany;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getRucCompany() {
        return rucCompany;
    }

    public void setRucCompany(String rucCompany) {
        this.rucCompany = rucCompany;
    }
}
