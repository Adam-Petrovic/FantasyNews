package entity;

/**
 * Represents a grade for a student in a course.
 */
public class Grade {

    // Refer to the API documentation for the meaning of these fields.
    private final String username;
    private final String course;
    private final int grade;

    public Grade(String username, String course, int grade) {
        this.username = username;
        this.course = course;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" + "username='" + username + '\''
                + ", course='" + course + '\''
                + ", grade=" + grade + '}';
    }

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the course of the grade.
     * @return the course of the grade.
     */
    public String getCourse() {
        return course;
    }

    /**
     * Returns the grade of the student.
     * @return the grade of the student.
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Returns a new GradeBuilder instance.
     * @return a new GradeBuilder instance.
     */
    public static GradeBuilder builder() {
        return new GradeBuilder();
    }

    /**
     * Represents a builder for creating instances of a Grade.
     */
    public static class GradeBuilder {
        private String username;
        private String course;
        private int grade;

        GradeBuilder() {
        }

        /**
         * Sets the username of the student.
         * @param usernameInput the username of the student.
         * @return the GradeBuilder instance.
         */
        public GradeBuilder username(String usernameInput) {
            this.username = usernameInput;
            return this;
        }

        /**
         * Sets the course of the grade.
         * @param courseInput the course of the grade.
         * @return the GradeBuilder instance.
         */
        public GradeBuilder course(String courseInput) {
            this.course = courseInput;
            return this;
        }

        /**
         * Sets the grade of the student.
         * @param gradeInput the grade of the student.
         * @return the GradeBuilder instance.
         */
        public GradeBuilder grade(int gradeInput) {
            this.grade = gradeInput;
            return this;
        }

        /**
         * Builds a new Grade instance.
         * @return a new Grade instance.
         */
        public Grade build() {
            return new Grade(username, course, grade);
        }
    }

}
