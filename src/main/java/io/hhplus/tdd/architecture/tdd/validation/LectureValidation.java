package io.hhplus.tdd.architecture.tdd.validation;

import org.springframework.stereotype.Component;

@Component
public class LectureValidation {

    public void lectureTitleCheck(String lectureTitle, String createTitle) {
        if(lectureTitle.equals(createTitle)) {
            throw new IllegalArgumentException("이미 등록된 강의 입니다.");
        }
    }

    public void joinLectureCheck(boolean optionalLectureHistory) {
        if(optionalLectureHistory) {
            throw new IllegalArgumentException("이미 신청한 강의 입니다.");
        }
    }

    public void userIdCheck(String userId, String idUser) {
        if(!userId.equals(idUser)) {
            throw new IllegalArgumentException("유저 정보가 없습니다.");
        }
    }

}
