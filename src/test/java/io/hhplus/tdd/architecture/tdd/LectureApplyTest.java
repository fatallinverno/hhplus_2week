package io.hhplus.tdd.architecture.tdd;

import io.hhplus.tdd.architecture.tdd.application.service.LectureService;
import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.User;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture.LectureRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LectureApplyTest {

    @Autowired
    private LectureService lectureService;

    private Lecture lecture;

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLectureApply() throws InterruptedException {
        //Given
        lecture = lectureRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("강의가 없습니다."));

        List<Thread> threads = new ArrayList<>();
        List<Boolean> results = Collections.synchronizedList(new ArrayList<>());

        List<User> users = userRepository.findAll();

        assertEquals(40, users.size());

        for (int i = 0; i < 40; i++) {
            final User user = users.get(i);

            Thread thread = new Thread(() -> {
                try {
                    lectureService.joinLecture(user.getUserId(), lecture.getLectureId());
                    results.add(true);
                } catch (Exception e) {
                    results.add(false);
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        lecture = lectureRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("강의가 없습니다."));

        long successCount = results.stream().filter(result -> result).count();
        long failCount = results.stream().filter(result -> !result).count();

        assertEquals(0, lecture.getCapacity());
        assertEquals(30, successCount);
        assertEquals(10, failCount);
    }

}
