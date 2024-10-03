package io.hhplus.tdd.architecture.tdd.application.service;

import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.LectureHistory;

import java.time.LocalDate;
import java.util.List;

public interface LectureService {

    List<Lecture> getLectureAll(LocalDate lectureDate);
    Lecture createLecture(String createTitle);
    LectureHistory joinLecture(String userId, Long lectureId);

}
