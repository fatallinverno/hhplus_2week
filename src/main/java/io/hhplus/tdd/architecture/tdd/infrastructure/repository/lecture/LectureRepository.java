package io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture;


import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT l.capacity FROM Lecture l WHERE l.lectureId = :lectureId")
    int findByCapacityLock(Long lectureId);

    List<Lecture> findAllByLectureDate(LocalDate lectureDate);
}
