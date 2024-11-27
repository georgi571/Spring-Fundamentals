package com.philately.model.dto;

import com.philately.model.enums.PaperType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

public class AddStampDTO implements Serializable {
    @NotEmpty(message = "{stamp.name.empty}")
    @Size(min = 5, max = 20, message = "{stamp.name.length}")
    private String name;

    @NotEmpty(message = "{stamp.description.empty}")
    @Size(min = 5, max = 25, message = "{stamp.description.length}")
    private String description;

    @NotEmpty(message = "{stamp.imageUrl.empty}")
    @URL(message = "{stamp.imageUrl.url}")
    private String imageUrl;

    @NotNull(message = "{stamp.price.empty}")
    @Min(value = 1, message = "{stamp.price.min}")
    private Integer price;

    @NotNull(message = "{stamp.paper.empty}")
    private PaperType paper;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public PaperType getPaper() {
        return paper;
    }

    public void setPaper(PaperType paper) {
        this.paper = paper;
    }
}
