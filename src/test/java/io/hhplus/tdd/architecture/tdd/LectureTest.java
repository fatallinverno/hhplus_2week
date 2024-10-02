package io.hhplus.tdd.architecture.tdd;

import io.hhplus.tdd.architecture.tdd.application.serviceImpl.LectureServiceImpl;
import io.hhplus.tdd.architecture.tdd.domain.entity.Lecture;
import io.hhplus.tdd.architecture.tdd.domain.entity.LectureHistory;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.history.LectureHistoryRepository;
import io.hhplus.tdd.architecture.tdd.infrastructure.repository.lecture.LectureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class LectureTest {

    @Mock
    private LectureServiceImpl lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private LectureHistoryRepository lectureHistoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("강의 목록 Test")
    public void testGetLectureAll() {
        // Given
        Lecture lecture1 = new Lecture();
        lecture1.setLectureId(1L);
        lecture1.setTitle("Java");
        lecture1.setTeacherName("자바");

        Lecture lecture2 = new Lecture();
        lecture2.setLectureId(2L);
        lecture2.setTitle("Python");
        lecture2.setTeacherName("파이썬");

        when(lectureService.getLectureAll()).thenReturn(Arrays.asList(lecture1, lecture2));

        // When
        List<Lecture> lectures = lectureService.getLectureAll();

        // Then
        assertNotNull(lectures);
        assertEquals(2, lectures.size());
        assertEquals("Java", lectures.get(0).getTitle());
        assertEquals("Python", lectures.get(1).getTitle());
    }

    @Test
    @DisplayName("강의 생성 Test")
    public void testCreateLecture() {
        // Given
        Lecture lecture = new Lecture();
        lecture.setLectureId(1L);
        lecture.setTitle("Java");
        lecture.setTeacherName("이경록");

        when(lectureService.createLecture("Java")).thenReturn(lecture);

        // When
        Lecture createdLecture = lectureService.createLecture("Java");

        // Then
        assertNotNull(createdLecture);
        assertEquals("Java", createdLecture.getTitle());
    }

    @Test
    @DisplayName("강의 신청 Test")
    public void testJoinLecture() {
        //Given
        LectureHistory lecture = new LectureHistory();
        lecture.setLectureId(1L);
        lecture.setTitle("Java");
        lecture.setTeacherName("이경록");
        lecture.setUserId("LKR");

        String userId = lecture.getUserId();
        Long lectureId = lecture.getLectureId();

        when(lectureService.joinLecture(userId, lectureId)).thenReturn(lecture);

        //when
        lectureService.joinLecture(userId, lectureId);

        //then
        assertNotNull(lecture);
        assertEquals("Java", lecture.getTitle());
        assertEquals("LKR", lecture.getUserId());
    }

}