package io.hhplus.tdd.architecture.tdd.Domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "lecture_history")
public class LectureHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long userId;

    @Column
    private String title;

    @Column
    private String teacherName;

    @Column
    private LocalDate joinDate;

}
