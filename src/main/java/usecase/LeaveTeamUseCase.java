package usecase;

import api.GradeDataBase;

/**
 * The class for the leave team use case.
 */

public final class LeaveTeamUseCase {
    private final GradeDataBase gradeDataBase;

    public LeaveTeamUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Call the database to run the action to leave a team.
     */
    public void leaveTeam() {
        gradeDataBase.leaveTeam();
    }
}
