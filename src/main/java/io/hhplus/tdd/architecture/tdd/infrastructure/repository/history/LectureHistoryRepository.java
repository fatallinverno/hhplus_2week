package io.hhplus.tdd.architecture.tdd.infrastructure.repository.history;


import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureHistoryRepository extends JpaRepository<LectureHistory, Long> {
    @Query("SELECT l FROM LectureHistory l WHERE l.userId = :userId AND l.lectureId = :lectureId")
    Optional<Lecture> findByLectureUserId(String userId, Long lectureId);
}
