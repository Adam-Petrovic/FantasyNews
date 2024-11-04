import api.GradeDataBase;
import entity.Grade;
import entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usecase.GetAverageGradeUseCase;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing get average grade across your team.
 * The idea is that we don't want to wait for the specific API call to be implemented.
 * <p>
 * This demonstrates using the Mockito library for "mocking" the GradeDataBase.
 * <p>
 * This should pass if the "to do" Task 3a is correctly implemented.
 */
public class GetAverageGradeUseCaseMockitoTest {
    @Mock
    private GradeDataBase gradeDB;

    private GetAverageGradeUseCase getAverageGradeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Use openMocks() instead of initMocks() if using Mockito 3.2+
        getAverageGradeUseCase = new GetAverageGradeUseCase(gradeDB);
    }

    @Test
    void testGetAverageGrade() {
        // Build an array of Grades.
        Grade[] expectedTeammember1Grades = new Grade[3];
        expectedTeammember1Grades[0] = Grade.builder()
                .username("t1chenpa")
                .course("CSC207")
                .grade(85)
                .build();
        expectedTeammember1Grades[1] = Grade.builder()
                .username("t1chenpa")
                .course("CSC148")
                .grade(86)
                .build();
        expectedTeammember1Grades[2] = Grade.builder()
                .username("t1chenpa")
                .course("CSC165")
                .grade(91)
                .build();

        Grade[] expectedTeammember2Grades = new Grade[2];
        expectedTeammember2Grades[0] = Grade.builder()
                .username("t2chenpa")
                .course("CSC207")
                .grade(81)
                .build();
        expectedTeammember2Grades[1] = Grade.builder()
                .username("t2chenpa")
                .course("CSC148")
                .grade(89)
                .build();

        Team expectedTeam = Team.builder()
                .name("team1")
                .members(new String[]{"t1chenpa", "t2chenpa"})
                .build();



        // Define the behavior of the mock
        when(gradeDB.getGrade("t1chenpa", "CSC207")).thenReturn(expectedTeammember1Grades[0]);
        when(gradeDB.getGrade("t2chenpa", "CSC207")).thenReturn(expectedTeammember2Grades[0]);

        when(gradeDB.getGrades("t1chenpa")).thenReturn(expectedTeammember1Grades);
        when(gradeDB.getGrades("t2chenpa")).thenReturn(expectedTeammember2Grades);
        when(gradeDB.getMyTeam()).thenReturn(expectedTeam);

        // Act
        float result = getAverageGradeUseCase.getAverageGrade("CSC207");

        // Assert
        assertEquals(83.0, result);
    }
}
