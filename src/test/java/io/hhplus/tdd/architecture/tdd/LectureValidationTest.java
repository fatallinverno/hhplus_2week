package io.hhplus.tdd.architecture.tdd;

import io.hhplus.tdd.architecture.tdd.Infrastructure.Repository.Lecture.LectureRepository;
import io.hhplus.tdd.architecture.tdd.Validation.LectureValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LectureValidationTest {

    private final LectureValidation sut = new LectureValidation();

    @InjectMocks
    private LectureRepository lectureRepository;

    @Test
    @DisplayName("강의명 중복 체크")
    public void testLectureTitleCheck() {
        //Given
        String lectureTitle = "Java";
        String createTitle = "Java";

        //When & Then
        assertDoesNotThrow(() -> sut.lectureTitleCheck(lectureTitle, createTitle));

    }

    @Test
    @DisplayName("강의 유무 체크")
    public void testLectureCheck() {
        //Given
        boolean lecetureChk = true;

        //When & Then
        assertDoesNotThrow(() -> sut.lectureCheck(lecetureChk));
    }

}
