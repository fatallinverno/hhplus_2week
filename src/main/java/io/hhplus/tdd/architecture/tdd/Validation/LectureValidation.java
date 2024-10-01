package io.hhplus.tdd.architecture.tdd.Validation;

import org.springframework.stereotype.Component;

@Component
public class LectureValidation {

    public void lectureTitleCheck(String lectureTitle, String createTitle) {
        if(lectureTitle.equals(createTitle)) {
            throw new IllegalArgumentException("이미 등록된 강의 입니다.");
        }
    }

    public void lectureCheck(boolean id) {
        if(!id) {
            throw new IllegalArgumentException("등록되지 않은 강의 입니다.");
        }
    }

}
