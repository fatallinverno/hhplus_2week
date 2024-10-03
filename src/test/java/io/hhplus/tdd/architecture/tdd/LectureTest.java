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

import java.time.LocalDate;
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
        LocalDate today = LocalDate.now();

        Lecture lecture1 = new Lecture();
        lecture1.setId(1L);
        lecture1.setTitle("Java");
        lecture1.setTeacherName("자바");
        lecture1.setLectureDate(today);

        Lecture lecture2 = new Lecture();
        lecture2.setId(2L);
        lecture2.setTitle("Python");
        lecture2.setTeacherName("파이썬");
        lecture2.setLectureDate(today);

        when(lectureService.getLectureAll(today)).thenReturn(Arrays.asList(lecture1, lecture2));

        // When
        List<Lecture> lectures = lectureService.getLectureAll(today);

        // Then
        assertNotNull(lectures);
        assertEquals(2, lectures.size());
        assertEquals("Java", lectures.get(0).getTitle());
        assertEquals("Python", lectures.get(1).getTitle());
        assertEquals(today, lectures.get(0).getLectureDate());
        assertEquals(today, lectures.get(1).getLectureDate());
    }

    @Test
    @DisplayName("강의 신청 Test")
    public void testJoinLecture() {
        //Given
        LectureHistory lecture = new LectureHistory();
        lecture.setId(1L);
        lecture.setTitle("Java");
        lecture.setTeacherName("이경록");
        lecture.setUserId("LKR");

        String userId = lecture.getUserId();
        Long lectureId = lecture.getId();

        when(lectureService.joinLecture(userId, lectureId)).thenReturn(lecture);

        //when
        lectureService.joinLecture(userId, lectureId);

        //then
        assertNotNull(lecture);
        assertEquals("Java", lecture.getTitle());
        assertEquals("LKR", lecture.getUserId());
    }

}