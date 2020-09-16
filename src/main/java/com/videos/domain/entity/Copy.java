package com.videos.domain.entity;

import com.videos.exception.BusinessRuleViolationException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "copies")
@Getter
@Setter
public class Copy {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "VIDEO_ID")
    private Video video;

    @Column(name="AVAILABLE")
    private boolean available = true;

    @OneToMany(targetEntity = Rental.class, mappedBy = "copy", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Rental> rentals = new ArrayList<>();

    @PreRemove
    public void beforeRemoval() {
        if(rentals.size() > 0) {
            throw new BusinessRuleViolationException("Cannot delete copy with rental history");
        }
    }

    public Copy() {
    }

    public Copy(Video video) {
        this.video = video;
    }
}
