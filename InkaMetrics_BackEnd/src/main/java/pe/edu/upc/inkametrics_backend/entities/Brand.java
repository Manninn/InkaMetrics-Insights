package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBrand; //one to many  detecciones publicitarias

    @Column(name = "brandName", nullable = false)
    private String brandName;
    @Column(name = "sectorbrand", nullable = false)
    private String sectorBrand;

    public Brand() {
    }

    public Brand(int idBrand, String brandName, String sectorBrand) {
        this.idBrand = idBrand;
        this.brandName = brandName;
        this.sectorBrand = sectorBrand;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSectorBrand() {
        return sectorBrand;
    }

    public void setSectorBrand(String sectorBrand) {
        this.sectorBrand = sectorBrand;
    }
}
