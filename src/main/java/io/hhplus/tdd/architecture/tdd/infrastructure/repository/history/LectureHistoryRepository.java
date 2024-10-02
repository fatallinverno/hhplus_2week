package io.hhplus.tdd.architecture.tdd.infrastructure.repository.history;


import io.hhplus.tdd.architecture.tdd.domain.entity.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureHistoryRepository extends JpaRepository<LectureHistory, Long> {
}
