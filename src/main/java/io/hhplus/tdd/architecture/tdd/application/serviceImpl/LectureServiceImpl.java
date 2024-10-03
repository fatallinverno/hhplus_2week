package io.hhplus.tdd.architecture.tdd.application.serviceImpl;

import io.hhplus.tdd.architecture.tdd.application.service.LectureService;
import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.LectureHistory;
import io.hhplus.tdd.architecture.tdd.domain.entity.User;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.history.LectureHistoryRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture.LectureRepository;
import io.hhplus.tdd.architecture.tdd.validation.LectureValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final LectureHistoryRepository lectureHistoryRepository;
    private final LectureValidation lectureValidation;

//    public LectureServiceImpl(LectureHistoryRepository lectureHistoryRepository, StudentRepository studentRepository, LectureRepository lectureRepository, LectureValidation lectureValidation) {
//        this.lectureRepository = lectureRepository;
//        this.lectureHistoryRepository = lectureHistoryRepository;
//        this.studentRepository = studentRepository;
//        this.lectureValidation = lectureValidation;
//    }

    public List<Lecture> getLectureAll(LocalDate lectureDate) {
        return lectureRepository.findAllByLectureDate(lectureDate);
    }

    @Transactional
    public Lecture createLecture(String createTitle) {
        Lecture lecture = new Lecture();
        String lectureTitle = lecture.getTitle();
        lectureValidation.lectureTitleCheck(lectureTitle, createTitle);

        lecture.setTitle(createTitle);

        return lectureRepository.save(lecture);
    }

    @Transactional
    public LectureHistory joinLecture(String userId, Long id) {
        Optional<Lecture> lecture = lectureRepository.findById(id);
        boolean isLecture = lecture.isPresent();
        lectureValidation.lectureCheck(isLecture);

        User user = new User();
        String idUser = user.getUserId();
        lectureValidation.userIdCheck(userId, idUser);

        LectureHistory lectureHistory = new LectureHistory();
        lectureHistory.setId(id);
        lectureHistory.setUserId(userId);
        lectureHistory.setJoinDate(LocalDate.now());

        return lectureHistoryRepository.save(lectureHistory);
    }

}
