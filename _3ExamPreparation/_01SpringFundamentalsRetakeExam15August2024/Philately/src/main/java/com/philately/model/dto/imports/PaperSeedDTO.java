package com.philately.model.dto.imports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PaperSeedDTO implements Serializable {
    @Expose
    private String description;

    @Expose
    private String paperName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
}
