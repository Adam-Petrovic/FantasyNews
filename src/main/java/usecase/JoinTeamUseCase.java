package usecase;

import api.GradeDataBase;
import entity.Team;

/**
 * JoinTeamUseCase class.
 */
public final class JoinTeamUseCase {
    private final GradeDataBase gradeDataBase;

    public JoinTeamUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Join a team.
     * @param name The name of the team.
     * @return The team that the student joined.
     */
    public Team joinTeam(String name) {
        return gradeDataBase.joinTeam(name);
    }
}
