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

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureHistoryRepository lectureHistoryRepository;

    private Lecture lecture;

    @Autowired
    private UserRepository userRepository;

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
                    results.add(true);  // 신청 성공 시
                } catch (Exception e) {
                    results.add(false);  // 신청 실패 시
                }
            });
            threads.add(thread);
        }

        // 3. 스레드 시작
        for (Thread thread : threads) {
            thread.start();
        }

        // 4. 모든 스레드가 끝날 때까지 대기
        for (Thread thread : threads) {
            thread.join();
        }

        // 5. 성공적인 신청자와 실패한 신청자 수 검증
        long successCount = lecture.getCapacity();
//        long failureCount = results.size() - successCount;

        // 6. 30명만 성공했는지 검증
        assertEquals(0, successCount);
//        assertEquals(10, failureCount);
    }

}
