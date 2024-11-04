package usecase;

import org.json.JSONException;

import api.GradeDataBase;

/**
 * LogGradeUseCase class.
 */
public final class LogGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public LogGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Log a grade.
     * @param course The course.
     * @param grade The grade.
     * @throws JSONException if an error occurs.
     */
    public void logGrade(String course, int grade) throws JSONException {
        gradeDataBase.logGrade(course, grade);
    }
}
