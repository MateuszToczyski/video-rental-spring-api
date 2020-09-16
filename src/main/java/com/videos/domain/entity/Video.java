package com.videos.domain.entity;

import com.videos.exception.BusinessRuleViolationException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "videos")
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "DIRECTOR")
    private String director;

    @OneToMany(targetEntity = Copy.class, mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Copy> copies = new ArrayList<>();

    @PreRemove
    public void beforeRemoval() {
        if(copies.size() > 0) {
            throw new BusinessRuleViolationException("Cannot delete video with existing copies");
        }
    }

    public Video() {
    }

    public Video(Category category, String title, Integer year, String director) {
        this.category = category;
        this.title = title;
        this.year = year;
        this.director = director;
    }
}
