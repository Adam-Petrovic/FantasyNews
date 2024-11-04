package usecase;

import api.GradeDataBase;
import entity.Team;

/**
 * FormTeamUseCase class.
 */
public final class FormTeamUseCase {
    private final GradeDataBase gradeDataBase;

    public FormTeamUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Form a team.
     * @param name The name of the team.
     * @return The team that was formed. Note that if there is error, the team will not be formed.
     */
    public Team formTeam(String name) {
        return gradeDataBase.formTeam(name);
        // Need to calculate the average. every grade has a field called grade (which is an int).
        // We need to sum all the grades and divide by the number of grades.
    }
}
