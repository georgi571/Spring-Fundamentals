package com.philately.model.dto.imports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class StampSeedDTO implements Serializable {
    @Expose
    private String description;

    @Expose
    private String imageUrl;

    @Expose
    private String name;

    @Expose
    private Integer price;

    @Expose
    private Long ownerId;

    @Expose
    private Long paperId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }
}
