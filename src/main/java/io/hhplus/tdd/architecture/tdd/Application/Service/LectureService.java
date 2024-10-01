package io.hhplus.tdd.architecture.tdd.Application.Service;

import io.hhplus.tdd.architecture.tdd.Domain.Entity.Lecture;
import io.hhplus.tdd.architecture.tdd.Domain.Entity.LectureHistory;

import java.util.List;

public interface LectureService {

    List<Lecture> getLectureAll();
    Lecture createLecture(String createTitle);
    LectureHistory joinLecture(long userId, Long id);

}
