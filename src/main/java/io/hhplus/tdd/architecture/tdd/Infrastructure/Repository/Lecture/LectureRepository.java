package io.hhplus.tdd.architecture.tdd.Infrastructure.Repository.Lecture;


import io.hhplus.tdd.architecture.tdd.Domain.Entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
