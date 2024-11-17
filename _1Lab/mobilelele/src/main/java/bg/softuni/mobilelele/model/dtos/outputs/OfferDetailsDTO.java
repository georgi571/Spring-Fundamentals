package bg.softuni.mobilelele.model.dtos.outputs;

import bg.softuni.mobilelele.model.entities.User;
import bg.softuni.mobilelele.model.enums.BrandType;
import bg.softuni.mobilelele.model.enums.EngineType;
import bg.softuni.mobilelele.model.enums.ModelType;
import bg.softuni.mobilelele.model.enums.TransmissionType;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OfferDetailsDTO implements Serializable {

    Long id;

    Integer mileage;

    Integer price;

    Integer year;

    TransmissionType transmission;

    EngineType engine;

    Instant created;

    Instant updated;

    BrandType brand;

    ModelType model;

    String imageUrl;

    User user;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public String getCreated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(created);
    }

    public void setCreated(Instant  created) {
        this.created = created;
    }

    public String  getUpdated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(updated);
    }

    public void setUpdated(Instant  updated) {
        this.updated = updated;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
