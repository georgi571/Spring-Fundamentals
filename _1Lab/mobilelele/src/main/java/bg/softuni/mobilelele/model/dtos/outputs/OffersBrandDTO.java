package bg.softuni.mobilelele.model.dtos.outputs;

import bg.softuni.mobilelele.model.enums.BrandType;
import bg.softuni.mobilelele.model.enums.ModelType;

import java.io.Serializable;

public class OffersBrandDTO implements Serializable {

    private long id;

    private BrandType brand;

    private ModelType model;

    private Integer year;

    private Integer endYear;

    private String imageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BrandType getBrand() {
        return brand;
    }

    public void setBrand(BrandType brand) {
        this.brand = brand;
    }

    public ModelType getModel() {
        return model;
    }

    public void setModel(ModelType model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
