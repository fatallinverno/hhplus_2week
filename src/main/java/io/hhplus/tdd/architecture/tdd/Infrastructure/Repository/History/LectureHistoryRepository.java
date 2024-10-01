package io.hhplus.tdd.architecture.tdd.Infrastructure.Repository.History;


import io.hhplus.tdd.architecture.tdd.Domain.Entity.LectureHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureHistoryRepository extends JpaRepository<LectureHistory, Long> {
}
