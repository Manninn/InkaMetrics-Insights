package pe.edu.upc.inkametrics_backend.dtos;

public class BrandDTO {

    private int idBrand;
    private String brandName;
    private String sectorBrand;

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
