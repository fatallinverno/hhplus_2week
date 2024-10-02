package io.hhplus.tdd.architecture.tdd;

import io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture.LectureRepository;
import io.hhplus.tdd.architecture.tdd.validation.LectureValidation;
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

    @Test
    @DisplayName("유저 유무 체크")
    public void testUserCheck() {
        //Given
        String userId = "LKR";
        String idUser = "LKR";

        //When & Then
        assertDoesNotThrow(() -> sut.userIdCheck(userId, idUser));
    }

}
