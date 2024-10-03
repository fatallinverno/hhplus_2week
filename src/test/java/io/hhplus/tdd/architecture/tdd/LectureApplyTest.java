package io.hhplus.tdd.architecture.tdd;

import io.hhplus.tdd.architecture.tdd.application.service.LectureService;
import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.User;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.history.LectureHistoryRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture.LectureRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LectureApplyTest {

    @Autowired
    private LectureService lectureService;

    private Lecture lecture;

    @BeforeEach
    void setUp() {
        lecture = new Lecture();
    }

    @Test
    public void testLectureApply() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        List<Boolean> results = Collections.synchronizedList(new ArrayList<>());

        for (int i = 1; i <= 40; i++) {
            final String userId = "user" + i;
            Thread thread = new Thread(() -> {
                try {
                    lectureService.joinLecture(userId, 1L);
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

        long successCount = lecture.getCapacity();

        assertEquals(0, successCount);
    }

}
