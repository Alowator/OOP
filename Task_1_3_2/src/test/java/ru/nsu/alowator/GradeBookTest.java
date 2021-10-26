package ru.nsu.alowator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeBookTest {

    GradeBook gradeBook;

    @BeforeEach
    void gradeBook_constructor() {
        gradeBook = new GradeBook();
    }


    @Test
    void setCurrentSemesterNumber() {
        gradeBook.setCurrentSemesterNumber(4);
        assertEquals(4, gradeBook.getCurrentSemesterNumber());
    }

    @Test
    void setCurrentSemesterNumber_exceptionLess() {
        assertThrows(
                RuntimeException.class,
                () -> {gradeBook.setCurrentSemesterNumber(0);}
        );
    }

    @Test
    void setCurrentSemesterNumber_exceptionOver() {
        assertThrows(
                RuntimeException.class,
                () -> {gradeBook.setCurrentSemesterNumber(gradeBook.TOTAL_SEMESTERS_COUNT + 1);}
        );
    }


    @Test
    void setGrade() {
        gradeBook.setGrade(Subject.HISTORY, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.DIGITAL_PLATFORMS, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.INTRODUCTION_TO_ALGEBRA_AND_ANALYSIS, GradeBook.Grade.FOUR);
    }

    @Test
    void setQualificationWorkGrade() {
        gradeBook.setQualificationWorkGrade(GradeBook.Grade.FOUR);
        assertEquals(GradeBook.Grade.FOUR, gradeBook.getQualificationWorkGrade());
    }


    @Test
    void getAverageScore_empty() {
        assertEquals(null, gradeBook.getAverageScore());
    }

    @Test
    void getAverageScore() {
        gradeBook.setGrade(Subject.HISTORY, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.INTRODUCTION_TO_ALGEBRA_AND_ANALYSIS, GradeBook.Grade.FOUR);
        gradeBook.setGrade(Subject.DIGITAL_PLATFORMS, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.OOP, GradeBook.Grade.FIVE);
        assertEquals(4.75, gradeBook.getAverageScore());
    }


    @Test
    void isRedDiploma_empty() {
        assertFalse(gradeBook.isRedDiploma());
    }

    @Test
    void isRedDiploma_true() {
        gradeBook.setCurrentSemesterNumber(gradeBook.TOTAL_SEMESTERS_COUNT);
        gradeBook.setQualificationWorkGrade(GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.HISTORY, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.INTRODUCTION_TO_ALGEBRA_AND_ANALYSIS, GradeBook.Grade.FOUR);
        gradeBook.setGrade(Subject.DIGITAL_PLATFORMS, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.OOP, GradeBook.Grade.FIVE);
        assertTrue(gradeBook.isRedDiploma());
    }

    @Test
    void isRedDiploma_false() {
        gradeBook.setCurrentSemesterNumber(gradeBook.TOTAL_SEMESTERS_COUNT);
        gradeBook.setQualificationWorkGrade(GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.HISTORY, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.INTRODUCTION_TO_ALGEBRA_AND_ANALYSIS, GradeBook.Grade.THREE);
        gradeBook.setGrade(Subject.DIGITAL_PLATFORMS, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.OOP, GradeBook.Grade.FIVE);
        assertFalse(gradeBook.isRedDiploma());
    }


    @Test
    void isIncreasedScholarship_empty() {
        assertFalse(gradeBook.isIncreasedScholarship());
    }

    @Test
    void isIncreasedScholarship_true() {
        gradeBook.setGrade(Subject.HISTORY, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.INTRODUCTION_TO_ALGEBRA_AND_ANALYSIS, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.DIGITAL_PLATFORMS, GradeBook.Grade.FIVE);
        gradeBook.setCurrentSemesterNumber(2);
        assertTrue(gradeBook.isIncreasedScholarship());
    }

    @Test
    void isIncreasedScholarship_false() {
        gradeBook.setGrade(Subject.HISTORY, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.INTRODUCTION_TO_ALGEBRA_AND_ANALYSIS, GradeBook.Grade.FIVE);
        gradeBook.setGrade(Subject.DIGITAL_PLATFORMS, GradeBook.Grade.FOUR);
        gradeBook.setCurrentSemesterNumber(2);
        assertFalse(gradeBook.isIncreasedScholarship());
    }

}