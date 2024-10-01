package io.hhplus.tdd.architecture.tdd.Domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String teacher;

    @Column
    private int Capasity;

    @Column
    private LocalDate lectureDate;

    public Lecture() {

    }

}
