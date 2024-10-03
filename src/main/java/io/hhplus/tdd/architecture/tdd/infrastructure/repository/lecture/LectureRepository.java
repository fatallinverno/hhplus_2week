package io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture;


import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    // 특정 날짜와 일치하는 모든 강의 찾기
    List<Lecture> findAllByLectureDate(LocalDate lectureDate);
}
