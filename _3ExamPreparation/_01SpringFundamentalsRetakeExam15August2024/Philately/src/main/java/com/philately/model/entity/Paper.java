package com.philately.model.entity;

import com.philately.model.enums.PaperType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "papers")
public class Paper extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "paper_name", unique = true, nullable = false)
    private PaperType paperName;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @OneToMany(mappedBy = "paper")
    private Set<Stamp> stamps;

    public Paper() {
        this.stamps = new HashSet<>();
    }

    public Paper(PaperType paperName) {
        this.paperName = paperName;
        this.description = paperName.getDescription();
        this.stamps = new HashSet<>();
    }

    public PaperType getPaperName() {
        return paperName;
    }

    public void setPaperName(PaperType paperName) {
        this.paperName = paperName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Stamp> getStamps() {
        return stamps;
    }

    public void setStamps(Set<Stamp> stamps) {
        this.stamps = stamps;
    }
}
