package bg.softuni.mobilelele.model.dtos.inputs;

import bg.softuni.mobilelele.model.enums.BrandType;
import bg.softuni.mobilelele.model.enums.EngineType;
import bg.softuni.mobilelele.model.enums.ModelType;
import bg.softuni.mobilelele.model.enums.TransmissionType;
import jakarta.validation.constraints.*;

import java.io.Serializable;

public class AddOfferDTO implements Serializable {

    @NotNull(message = "{add.offer.brand.type.not.empty}")
    private BrandType brand;

    @NotNull(message = "{add.offer.model.type.not.empty}")
    private ModelType model;

    @NotNull(message = "{add.offer.price.not.empty}")
    @PositiveOrZero(message = "{add.offer.price.not.negative}")
    private Integer price;

    @NotNull(message = "{add.offer.engine.type.not.empty}")
    private EngineType engine;

    @NotNull(message = "{add.offer.transmission.type.not.empty}")
    private TransmissionType transmission;

    @NotNull(message = "{add.offer.millage.not.empty}")
    @PositiveOrZero(message = "{add.offer.millage.not.negative}")
    private Integer mileage;

    @NotNull(message = "{add.offer.year.not.empty}")
    @Min(value = 1900, message = "{add.offer.year.min}")
    @Max(value = 2099, message = "{add.offer.year.max}")
    private Integer year;

    @NotNull(message = "{add.offer.horsepower.not.empty}")
    @Min(value = 70, message = "{add.offer.horsepower.min}")
    @Max(value = 1500, message = "{add.offer.horsepower.max}")
    private Integer horsepower;

    @NotEmpty(message = "{add.offer.description.not.empty}")
    @Size(min = 5, max = 500, message = "{add.offer.description.length}")
    private String description;

    @NotEmpty(message = "{add.offer.image.url.not.empty}")
    private String imageUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ModelType getModel() {
        return model;
    }

    public void setModel(ModelType model) {
        this.model = model;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }


}
