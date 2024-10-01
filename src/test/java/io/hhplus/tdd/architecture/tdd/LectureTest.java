package io.hhplus.tdd.architecture.tdd;

import io.hhplus.tdd.architecture.tdd.Application.ServiceImpl.LectureServiceImpl;
import io.hhplus.tdd.architecture.tdd.Domain.Entity.Lecture;
import io.hhplus.tdd.architecture.tdd.Domain.Entity.LectureHistory;
import io.hhplus.tdd.architecture.tdd.Infrastructure.Repository.History.LectureHistoryRepository;
import io.hhplus.tdd.architecture.tdd.Infrastructure.Repository.Lecture.LectureRepository;
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
        lecture1.setId(1L);
        lecture1.setTitle("Java");
        lecture1.setTeacherName("자바");

        Lecture lecture2 = new Lecture();
        lecture2.setId(2L);
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
        lecture.setId(1L);
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
        lecture.setId(1L);
        lecture.setTitle("Java");
        lecture.setTeacherName("이경록");
        lecture.setUserId(3L);

        long userId = lecture.getUserId();
        Long lectureId = lecture.getId();

        when(lectureService.joinLecture(userId, lectureId)).thenReturn(lecture);

        //when
        lectureService.joinLecture(userId, lectureId);

        //then
        assertNotNull(lecture);
        assertEquals("Java", lecture.getTitle());
        assertEquals(3L, lecture.getUserId());
    }

}