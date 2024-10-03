package io.hhplus.tdd.architecture.tdd.application.serviceImpl;

import io.hhplus.tdd.architecture.tdd.application.service.LectureService;
import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.LectureHistory;
import io.hhplus.tdd.architecture.tdd.domain.entity.User;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.history.LectureHistoryRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture.LectureRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.user.UserRepository;
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
    private final UserRepository userRepository;

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
    public LectureHistory joinLecture(String userId, Long lectureId) {
        //강의 유무
        Optional<Lecture> optionalLecture = Optional.ofNullable(lectureRepository.findByIdWithLock(lectureId).orElseThrow(() -> new IllegalArgumentException("강의 정보가 없습니다.")));
        Lecture lecture = optionalLecture.get();

        //유저 유무
        userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));

        //유저 강의 신청 여부
        Optional<LectureHistory> optionalLectureHistory = lectureHistoryRepository.findByLectureUserId(userId, lectureId);
        lectureValidation.joinLectureCheck(optionalLectureHistory.isPresent());

        //강의 잔여석 유무
        if (lecture.getCapacity() <= 0) {
            throw new IllegalArgumentException("해당 강의에는 잔여석이 없어 신청할 수가 없습니다.");
        }

        lecture.setLectureId(lectureId);
        lecture.setCapacity(lecture.getCapacity() - 1);
        lectureRepository.save(lecture);

        LectureHistory lectureHistory = new LectureHistory();
        lectureHistory.setLectureId(lectureId);
        lectureHistory.setUserId(userId);
        lectureHistory.setJoinDate(LocalDate.now());

        return lectureHistoryRepository.save(lectureHistory);
    }

}
