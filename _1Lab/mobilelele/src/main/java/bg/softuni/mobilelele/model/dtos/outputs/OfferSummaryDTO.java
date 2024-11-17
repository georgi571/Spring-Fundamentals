package bg.softuni.mobilelele.model.dtos.outputs;

import bg.softuni.mobilelele.model.enums.BrandType;
import bg.softuni.mobilelele.model.enums.EngineType;
import bg.softuni.mobilelele.model.enums.ModelType;
import bg.softuni.mobilelele.model.enums.TransmissionType;

import java.io.Serializable;

public class OfferSummaryDTO implements Serializable {
    private Long id;

    private Integer mileage;

    private EngineType engine;

    private Integer price;

    private TransmissionType transmission;

    private String imageUrl;

    private BrandType brand;

    private ModelType model;

    private Integer year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
}
