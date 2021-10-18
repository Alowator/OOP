package ru.nsu.alowator;

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

    public enum Subject {
        INTRODUCTION_TO_ALGEBRA_AND_ANALYSIS,
        DIGITAL_PLATFORMS,
        HISTORY,
    }

    private Map<Subject, Grade[]> marks;
    private Grade qualificationWorkGrade;

    private int threeCount = 0;
    private int fourCount = 0;
    private int fiveCount = 0;
    private double averageScore = 0;

    public GradeBook(Map<Subject, Grade[]> marks, Grade qualificationWorkGrade) {
        this.marks = marks;
        this.qualificationWorkGrade = qualificationWorkGrade;

        double score = 0;
        int gradeCount = 0;
        for(Map.Entry<Subject, Grade[]> entry : marks.entrySet()) {
            Grade[] grades = entry.getValue();
            for (Grade x : grades) {
                if (x.value == 3) {
                    threeCount++;
                } else if (x.value == 4) {
                    fourCount++;
                } else if (x.value == 5) {
                    fiveCount++;
                }
                gradeCount++;
                score += x.value;
            }

        }

        averageScore = score / gradeCount;

    }

    public double getAverageScore() {
        return averageScore;
    }

    public boolean isRedDiploma() {
        return (double) fiveCount / (fiveCount + fourCount + threeCount) >= 0.75 && threeCount == 0 && qualificationWorkGrade == Grade.FIVE;
    }

    public boolean isIncreasedScholarship() {
        return threeCount == 0;
    }

}
