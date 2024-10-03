package io.hhplus.tdd.architecture.tdd;

import io.hhplus.tdd.architecture.tdd.application.service.LectureService;
import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.LectureHistory;
import io.hhplus.tdd.architecture.tdd.domain.entity.User;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.history.LectureHistoryRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture.LectureRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LectureSameApplyTest {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureHistoryRepository lectureHistoryRepository;

    @Test
    void testSameJoinLecture() {
        //Given
        User user = userRepository.findAll().get(0);
        Lecture lecture = lectureRepository.findAll().get(0);

        //When
        for (int i = 0; i < 5; i++) {
            try {
                lectureService.joinLecture(user.getUserId(), lecture.getLectureId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        //Then
        List<Optional<LectureHistory>> historyList = Collections.singletonList(lectureHistoryRepository.findByLectureUserId(user.getUserId(), lecture.getLectureId()));
        assertEquals(1, historyList.size());
    }

}
