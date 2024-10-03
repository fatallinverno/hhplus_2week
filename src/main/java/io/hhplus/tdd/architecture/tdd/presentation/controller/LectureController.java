package io.hhplus.tdd.architecture.tdd.presentation.controller;

import io.hhplus.tdd.architecture.tdd.application.service.LectureService;
import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.LectureHistory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(("/lecture"))
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public List<Lecture> getLectures(LocalDate lectureDate) {
        return lectureService.getLectureAll(lectureDate);
    }

    public Lecture createLecture(String createTitle) {
        return lectureService.createLecture(createTitle);
    }

    @GetMapping("{id}/join")
    public LectureHistory joinLecture(String userId, Long lectureId) {
        return lectureService.joinLecture(userId, lectureId);
    }

}
