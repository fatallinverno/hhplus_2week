package io.hhplus.tdd.architecture.tdd.Presentation.Controller;

import io.hhplus.tdd.architecture.tdd.Application.Service.LectureService;
import io.hhplus.tdd.architecture.tdd.Domain.Entity.Lecture;
import io.hhplus.tdd.architecture.tdd.Domain.Entity.LectureHistory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(("/lecture"))
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public List<Lecture> getLectures() {
        return lectureService.getLectureAll();
    }

    public Lecture createLecture(String createTitle) {
        return lectureService.createLecture(createTitle);
    }

    @GetMapping("{id}/join")
    public LectureHistory joinLecture(long userId, Long id) {
        return lectureService.joinLecture(userId, id);
    }

}
