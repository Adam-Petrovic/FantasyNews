import api.GradeDataBase;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.GetAverageGradeUseCase;
import entity.Grade;
import entity.Team;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing get average grade across your team.
 * The idea is that we don't want to wait for the specific API call to be implemented.
 * <p>
 * This is like the other test, but doesn't use Mockito to more simply
 * demonstrate the idea of mocking (or stubbing) our GradeDataBase.
 * <p>
 * This should pass if the "to do" Task 3a is correctly implemented.
 */
public class GetAverageGradeUseCaseTest {

    private GradeDataBase gradeDB;
    private GetAverageGradeUseCase getAverageGradeUseCase;

    @BeforeEach
    void setUp() {
        gradeDB = new GradeDataBaseStub();  // Using a stub instead of a mock
        getAverageGradeUseCase = new GetAverageGradeUseCase(gradeDB);
    }

    @Test
    void testGetAverageGrade() {
        // Act
        float result = getAverageGradeUseCase.getAverageGrade("CSC207");

        // Assert
        assertEquals(83.0, result);
    }

    // Stub class for GradeDataBase
    private static class GradeDataBaseStub implements GradeDataBase {

        @Override
        public Grade getGrade(String username, String course) {
            Grade grade = null;
            if (username.equals("t1chenpa")) {
                if (course.equals("CSC207")) {
                    grade = Grade.builder()
                                .username("t1chenpa")
                                .course("CSC207")
                                .grade(85)
                                .build();

                }
            } else if (username.equals("t2chenpa")) {
                if (course.equals("CSC207")) {
                    grade = Grade.builder()
                                .username("t2chenpa")
                                .course("CSC207")
                                .grade(81)
                                .build();
                }
            }
            return grade;
        }

        @Override
        public Grade logGrade(String course, int grade) throws JSONException {
            return null;
        }

        @Override
        public Grade[] getGrades(String username) {
            if (username.equals("t1chenpa")) {
                return new Grade[]{
                        Grade.builder()
                                .username("t1chenpa")
                                .course("CSC207")
                                .grade(85)
                                .build(),
                        Grade.builder()
                                .username("t1chenpa")
                                .course("CSC148")
                                .grade(86)
                                .build(),
                        Grade.builder()
                                .username("t1chenpa")
                                .course("CSC165")
                                .grade(91)
                                .build()
                };
            } else if (username.equals("t2chenpa")) {
                return new Grade[]{
                        Grade.builder()
                                .username("t2chenpa")
                                .course("CSC207")
                                .grade(81)
                                .build(),
                        Grade.builder()
                                .username("t2chenpa")
                                .course("CSC148")
                                .grade(89)
                                .build()
                };
            }
            return new Grade[0];
        }

        @Override
        public Team formTeam(String name) throws JSONException {
            return null;
        }

        @Override
        public Team joinTeam(String name) throws JSONException {
            return null;
        }

        @Override
        public Team getMyTeam() {
            return Team.builder()
                    .name("team1")
                    .members(new String[]{"t1chenpa", "t2chenpa"})
                    .build();
        }

        @Override
        public void leaveTeam() throws JSONException {

        }
    }
}
