package api;

import org.json.JSONException;

import entity.Grade;
import entity.Team;

/**
 * GradeDB is an interface that defines the methods that the GradeDB class must implement.
 */
public interface GradeDataBase {
    /**
     * A method that returns the grade of a student in a course.
     * @param username is the username of the student.
     * @param course is the course that the student is taking.
     * @return the grade of the student in the course.
     */
    Grade getGrade(String username, String course);

    /**
     * A method that logs the grade of a student in a course.
     * @param course is the course that the student is taking.
     * @param grade is the grade of the student in the course.
     * @return the grade of the student in the course.
     * @throws JSONException if an error occurs.
     */
    Grade logGrade(String course, int grade) throws JSONException;

    /**
     * A method that returns the grades of a student in all courses.
     * @param username is the username of the student.
     * @return the grades of the student in all courses.
     * @throws JSONException if an error occurs.
     */
    Grade[] getGrades(String username) throws JSONException;

    /**
     * A method that forms a team with the given name.
     * @param name is the name of the team.
     * @return the team that was formed.
     * @throws JSONException if an error occurs.
     */
    Team formTeam(String name) throws JSONException;

    /**
     * A method that joins a team with the given name.
     * @param name is the name of the team.
     * @return the team that was joined.
     * @throws JSONException if an error occurs.
     */
    Team joinTeam(String name) throws JSONException;

    /**
     * A method that returns the team that the student is in.
     * @return the team that the student is in.
     * @throws JSONException if an error occurs.
     */
    Team getMyTeam() throws JSONException;

    /**
     * A method that leaves the team that the student is in.
     * @throws JSONException if an error occurs.
     */
    void leaveTeam() throws JSONException;

}
