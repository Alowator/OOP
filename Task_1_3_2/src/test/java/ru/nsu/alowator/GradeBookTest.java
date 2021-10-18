package ru.nsu.alowator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GradeBookTest {

    GradeBook gradeBook;

    @BeforeEach
    void gradeBook_constructor() {
        Map<GradeBook.Subject, GradeBook.Grade[]> marks = new HashMap<>();

        marks.put(GradeBook.Subject.INTRODUCTION_TO_ALGEBRA_AND_ANALYSIS,
                new GradeBook.Grade[]{GradeBook.Grade.FOUR, GradeBook.Grade.FIVE}
        );
        marks.put(GradeBook.Subject.DIGITAL_PLATFORMS,
                new GradeBook.Grade[]{GradeBook.Grade.FIVE}
        );
        marks.put(GradeBook.Subject.HISTORY,
                new GradeBook.Grade[]{GradeBook.Grade.FIVE}
        );

        gradeBook = new GradeBook(marks, GradeBook.Grade.FIVE);
    }

    @Test
    void getAverageScore() {
        assertEquals(4.75, gradeBook.getAverageScore());
    }

    @Test
    void isRedDiploma() {
        assertTrue(gradeBook.isRedDiploma());
    }

    @Test
    void isIncreasedScholarship() {
        assertTrue(gradeBook.isIncreasedScholarship());
    }

}