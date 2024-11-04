package entity;

/**
 * Represents a team with a name and a list of members.
 * This class provides a builder for creating instances of a Team.
 */
public class Team {

    // Refer to the API documentation for the meaning of these fields.
    private final String name;
    private final String[] members;

    public Team(String name, String[] members) {
        this.name = name;
        this.members = members;
    }

    @Override
    public String toString() {
        return "Team{" + "name='" + name + '\''
                + ", memberLength='" + members.length + '\'' + '}';
    }

    /**
     * Returns the name of the team.
     * @return the name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the members of the team.
     * @return the members of the team.
     */
    public String[] getMembers() {
        return members;
    }

    /**
     * Returns a new TeamBuilder instance.
     * @return a new TeamBuilder instance.
     */
    public static TeamBuilder builder() {
        return new TeamBuilder();
    }

    /**
     * Represents a builder for creating instances of a Team.
     */
    public static class TeamBuilder {
        private String name;
        private String[] members;

        TeamBuilder() {
        }

        /**
         * Sets the name of the team.
         * @param nameInput the name of the team.
         * @return the TeamBuilder instance.
         */
        public TeamBuilder name(String nameInput) {
            this.name = nameInput;
            return this;
        }

        /**
         * Sets the members of the team.
         * @param membersInput the members of the team.
         * @return the TeamBuilder instance.
         */
        public TeamBuilder members(String[] membersInput) {
            this.members = membersInput;
            return this;
        }

        /**
         * Builds an instance of a Team.
         * @return an instance of a Team.
         */
        public Team build() {
            return new Team(name, members);
        }
    }
}
