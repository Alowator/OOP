package ru.nsu.alowator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeBook {

    public enum Grade {
        THREE(3),
        FOUR(4),
        FIVE(5);

        public final int value;

        Grade(int value) {
            this.value = value;
        }
    }

    public final double RED_DIPLOMA_FIVE_GRADES_THRESHOLD = 0.75;
    public final int TOTAL_SEMESTERS_COUNT = 8;

    private int currentSemesterIndex = 0;
    private final List<Map<Subject, Grade>> semesterMarks;
    private Grade qualificationWorkGrade;

    /** Class constructor. */
    public GradeBook() {
        semesterMarks = new ArrayList<>();
        for (int i = 0; i < TOTAL_SEMESTERS_COUNT; i++) {
            semesterMarks.add(new HashMap<>());
        }
    }

    /**
     * Set current semester number.
     * @param semesterNumber must be not less than 1, not more than TOTAL_SEMESTERS_COUNT.
     * @exception RuntimeException if semesterNumber < 1 or semesterNumber > TOTAL_SEMESTERS_COUNT.
     */
    public void setCurrentSemesterNumber(int semesterNumber) {
        if (semesterNumber < 1 || TOTAL_SEMESTERS_COUNT < semesterNumber)
            throw new RuntimeException("Incorrect semester number.");
        currentSemesterIndex = semesterNumber - 1;
    }

    /**
     * Get current semester number.
     * @return Current semester number (starts with 1).
     */
    public int getCurrentSemesterNumber() {
        return currentSemesterIndex + 1;
    }

    /**
     * Set subject grade in current semester.
     * Every subject in semester contains only one grade.
     * @param subject Subject in current semester.
     * @param grade Grade of the subject in current semester.
     */
    public void setGrade(Subject subject, Grade grade) {
        semesterMarks.get(currentSemesterIndex).put(subject, grade);
    }

    /**
     * Set qualification work grade.
     * @param grade Grade of the qualification work.
     */
    public void setQualificationWorkGrade(Grade grade) {
        qualificationWorkGrade = grade;
    }

    /**
     * Get qualification work grade.
     * @return Grade of the qualification work. By default, is null.
     */
    public Grade getQualificationWorkGrade() {
        return qualificationWorkGrade;
    }

    /**
     * Get average score in current semester.
     * @return Double. If no grades, return null.
     */
    public Double getAverageScore() {
        double score = 0;
        int gradeCount = 0;
        for(var entry : semesterMarks.get(currentSemesterIndex).entrySet()) {
            Grade grade = entry.getValue();
            score += grade.value;
            gradeCount++;
        }
        if (gradeCount != 0)
            return score / gradeCount;
        else
            return null;
    }

    /**
     * @return true if current semester is last,
     *                 Five grades more than 75% in all semesters,
     *                 no Three grades in all semesters,
     *                 qualification work grade is Five.
     */
    public boolean isRedDiploma() {
        if (currentSemesterIndex != TOTAL_SEMESTERS_COUNT - 1)
            return false;

        int fiveCount = 0;
        int fourCount = 0;
        int threeCount = 0;

        for (var marks : semesterMarks) {
            for(var entry : marks.entrySet()) {
                Grade grade = entry.getValue();
                switch (grade) {
                    case FIVE -> fiveCount++;
                    case FOUR -> fourCount++;
                    case THREE -> threeCount++;
                }
            }
        }

        return (double) fiveCount / (fiveCount + fourCount + threeCount) >= RED_DIPLOMA_FIVE_GRADES_THRESHOLD &&
                threeCount == 0 &&
                qualificationWorkGrade == Grade.FIVE;
    }

    /**
     * @return true if current semester is not first and there are only Five grades.
     */
    public boolean isIncreasedScholarship() {
        if (currentSemesterIndex == 0)
            return false;

        int semesterIndex = currentSemesterIndex - 1;
        for(var entry : semesterMarks.get(semesterIndex).entrySet()) {
            Grade grade = entry.getValue();
            if (grade == Grade.THREE || grade == Grade.FOUR)
                return false;
        }
        return true;
    }

}

