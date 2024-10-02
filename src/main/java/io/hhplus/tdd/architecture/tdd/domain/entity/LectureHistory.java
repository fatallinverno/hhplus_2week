package io.hhplus.tdd.architecture.tdd.domain.entity;

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
    private Long lectureId;

    @Column
    private String userId;

    @Column
    private String title;

    @Column
    private String teacherName;

    @Column
    private LocalDate joinDate;

}
