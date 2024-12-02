//package entity;
//
//import data_access.Constants;
//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LeagueTest {
//
//    @Test
//    void testDefaultConstructor() {
//        // Act
//        League league = new League();
//
//        // Assert
//        assertEquals("meow", league.getId(), "League ID should be 'meow'");
//        assertNotNull(league.getUsers(), "Users list should not be null");
//        assertEquals(1, league.getUsers().size(), "Users list should contain one user");
//        assertEquals("hiii", league.getUsers().get(0), "User should be 'hiii'");
//
//        HashMap<String, String[]> data = league.getData();
//        assertNotNull(data, "Data map should not be null");
//        assertEquals(1, data.size(), "Data map should contain one entry");
//        assertTrue(data.containsKey("hiii"), "Data should contain key 'hiii'");
//        assertArrayEquals(Constants.DEFAULT_WORDS, data.get("hiii"), "Default words should match");
//
//        ArrayList<User> userObjArr = league.getUserObjArr();
//        assertNotNull(userObjArr, "User object array should not be null");
//        assertEquals(1, userObjArr.size(), "User object array should contain one user");
//        assertEquals("hiii", userObjArr.get(0).getName(), "User name should be 'hiii'");
//    }
//
//    @Test
//    void testConstructorWithIdAndUsers() {
//        // Arrange
//        String leagueId = "league1";
//        ArrayList<String> users = new ArrayList<>(Arrays.asList("user1", "user2"));
//
//        // Act
//        League league = new League(leagueId, users);
//
//        // Assert
//        assertEquals(leagueId, league.getId(), "League ID should match");
//        assertEquals(users, league.getUsers(), "Users list should match");
//
//        HashMap<String, String[]> data = league.getData();
//        assertNotNull(data, "Data map should not be null");
//        assertEquals(2, data.size(), "Data map should contain two entries");
//        assertArrayEquals(Constants.DEFAULT_WORDS, data.get("user1"), "Default words should match for 'user1'");
//        assertArrayEquals(Constants.DEFAULT_WORDS, data.get("user2"), "Default words should match for 'user2'");
//
//        ArrayList<User> userObjArr = league.getUserObjArr();
//        assertNotNull(userObjArr, "User object array should not be null");
//        assertEquals(2, userObjArr.size(), "User object array should contain two users");
//        assertEquals("user1", userObjArr.get(0).getName(), "First user's name should be 'user1'");
//        assertEquals("user2", userObjArr.get(1).getName(), "Second user's name should be 'user2'");
//    }
//
//    @Test
//    void testConstructorWithIdUsersAndData() {
//        // Arrange
//        String leagueId = "league2";
//        ArrayList<String> users = new ArrayList<>(Arrays.asList("user1", "user2"));
//        HashMap<String, String[]> data = new HashMap<>();
//        data.put("user1", new String[]{"word1", "word2"});
//        data.put("user2", new String[]{"word3", "word4"});
//
//        // Act
//        League league = new League(leagueId, users, data);
//
//        // Assert
//        assertEquals(leagueId, league.getId(), "League ID should match");
//        assertEquals(users, league.getUsers(), "Users list should match");
//        assertEquals(data, league.getData(), "Data map should match");
//
//        ArrayList<User> userObjArr = league.getUserObjArr();
//        assertNotNull(userObjArr, "User object array should not be null");
//        assertEquals(2, userObjArr.size(), "User object array should contain two users");
//
//        User user1 = userObjArr.get(0);
//        User user2 = userObjArr.get(1);
//
//        assertEquals("user1", user1.getName(), "First user's name should be 'user1'");
//        assertArrayEquals(data.get("user1"), user1.getWords(), "First user's words should match");
//
//        assertEquals("user2", user2.getName(), "Second user's name should be 'user2'");
//        assertArrayEquals(data.get("user2"), user2.getWords(), "Second user's words should match");
//    }
//
//    @Test
//    void testSetWords() {
//        // Arrange
//        String leagueId = "league3";
//        ArrayList<String> users = new ArrayList<>(Arrays.asList("user1", "user2"));
//        League league = new League(leagueId, users);
//
//        String[] newWords = {"newWord1", "newWord2"};
//
//        // Act
//        league.setWords("user1", newWords);
//
//        // Assert
//        assertArrayEquals(newWords, league.getData().get("user1"), "User1's words should be updated");
//
//        ArrayList<User> userObjArr = league.getUserObjArr();
//        User updatedUser = null;
//        for (User user : userObjArr) {
//            if (user.getName().equals("user1")) {
//                updatedUser = user;
//                break;
//            }
//        }
//        assertNotNull(updatedUser, "Updated user should not be null");
//        assertArrayEquals(newWords, updatedUser.getWords(), "Updated user's words should match");
//    }
//
//    @Test
//    void testSetAndGetRankings() {
//        // Arrange
//        League league = new League();
//        ArrayList<User> rankings = new ArrayList<>();
//        User user1 = new CommonUser("user1", "", new String[]{"word1"});
//        User user2 = new CommonUser("user2", "", new String[]{"word2"});
//        rankings.add(user1);
//        rankings.add(user2);
//
//        // Act
//        league.setRankings(rankings);
//
//        // Assert
//        assertEquals(rankings, league.getRankings(), "Rankings should match");
//    }
//
//    @Test
//    void testSetAndGetLiveRankings() {
//        // Arrange
//        League league = new League();
//        ArrayList<User> liveRankings = new ArrayList<>();
//        User user1 = new CommonUser("user1", "", new String[]{"word1"});
//        liveRankings.add(user1);
//
//        // Act
//        league.setLiveRankings(liveRankings);
//
//        // Assert
//        assertEquals(liveRankings, league.getLiveRankings(), "Live rankings should match");
//    }
//
//    @Test
//    void testSetAndGetHistoricalRankings() {
//        // Arrange
//        League league = new League();
//        ArrayList<User> historicalRankings = new ArrayList<>();
//        User user1 = new CommonUser("user1", "", new String[]{"word1"});
//        historicalRankings.add(user1);
//
//        // Act
//        league.setHistoricalRankings(historicalRankings);
//
//        // Assert
//        assertEquals(historicalRankings, league.getHistoricalRankings(), "Historical rankings should match");
//    }
//}
//
//class LeagueFactoryTest {
//
//    @Test
//    void testCreateDefaultLeague() {
//        // Arrange
//        LeagueFactory factory = new LeagueFactory();
//
//        // Act
//        League league = factory.create();
//
//        // Assert
//        assertNotNull(league, "League should not be null");
//        assertEquals("meow", league.getId(), "League ID should be 'meow'");
//        assertEquals(1, league.getUsers().size(), "League should contain one user");
//        assertEquals("hiii", league.getUsers().get(0), "User should be 'hiii'");
//    }
//
//    @Test
//    void testCreateLeagueWithIdAndUsers() {
//        // Arrange
//        LeagueFactory factory = new LeagueFactory();
//        String leagueName = "ChampionsLeague";
//        ArrayList<String> usernames = new ArrayList<>(Arrays.asList("player1", "player2"));
//
//        // Act
//        League league = factory.create(leagueName, usernames);
//
//        // Assert
//        assertNotNull(league, "League should not be null");
//        assertEquals(leagueName, league.getId(), "League ID should match");
//        assertEquals(usernames, league.getUsers(), "Usernames should match");
//    }
//}
package entity;

import data_access.Constants;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LeagueTest {

    @Test
    void testDefaultConstructor() {
        // Act
        League league = new League();

        // Assert
        assertEquals("meow", league.getId(), "League ID should be 'meow'");
        assertNotNull(league.getUsers(), "Users list should not be null");
        assertEquals(1, league.getUsers().size(), "Users list should contain one user");
        assertEquals("hiii", league.getUsers().get(0), "User should be 'hiii'");

        HashMap<String, String[]> data = league.getData();
        assertNotNull(data, "Data map should not be null");
        assertEquals(1, data.size(), "Data map should contain one entry");
        assertTrue(data.containsKey("hiii"), "Data should contain key 'hiii'");
        assertEquals(Constants.NUM_CATEGORIES + 1, data.get("hiii").length, "Default words should have length 5" + "" +
                "1 for total league points = 6");

        ArrayList<User> userObjArr = league.getUserObjArr();
        assertNotNull(userObjArr, "User object array should not be null");
        assertEquals(1, userObjArr.size(), "User object array should contain one user");
        assertEquals("hiii", userObjArr.get(0).getName(), "User name should be 'hiii'");
    }

    @Test
    void testConstructorWithIdAndUsers() {
        // Arrange
        String leagueId = "league1";
        ArrayList<String> users = new ArrayList<>(Arrays.asList("user1", "user2"));

        // Act
        League league = new League(leagueId, users);

        // Assert
        assertEquals(leagueId, league.getId(), "League ID should match");
        assertEquals(users, league.getUsers(), "Users list should match");

        HashMap<String, String[]> data = league.getData();
        assertNotNull(data, "Data map should not be null");
        assertEquals(2, data.size(), "Data map should contain two entries");
        assertEquals(Constants.NUM_CATEGORIES + 1, data.get("user1").length, "Default words should have length 5 for 'user1'");
        assertEquals(Constants.NUM_CATEGORIES + 1, data.get("user2").length, "Default words should have length 5 for 'user2'");

        ArrayList<User> userObjArr = league.getUserObjArr();
        assertNotNull(userObjArr, "User object array should not be null");
        assertEquals(2, userObjArr.size(), "User object array should contain two users");
        assertEquals("user1", userObjArr.get(0).getName(), "First user's name should be 'user1'");
        assertEquals("user2", userObjArr.get(1).getName(), "Second user's name should be 'user2'");
    }

    @Test
    void testConstructorWithIdUsersAndData() {
        // Arrange
        String leagueId = "league2";
        ArrayList<String> users = new ArrayList<>(Arrays.asList("user1", "user2"));
        String[] wordsForUser1 = {"word1", "word2", "word3", "word4", "word5"};
        String[] wordsForUser2 = {"word6", "word7", "word8", "word9", "word10"};
        HashMap<String, String[]> data = new HashMap<>();
        data.put("user1", wordsForUser1);
        data.put("user2", wordsForUser2);

        // Act
        League league = new League(leagueId, users, data);

        // Assert
        assertEquals(leagueId, league.getId(), "League ID should match");
        assertEquals(users, league.getUsers(), "Users list should match");
        assertEquals(data, league.getData(), "Data map should match");

        ArrayList<User> userObjArr = league.getUserObjArr();
        assertNotNull(userObjArr, "User object array should not be null");
        assertEquals(2, userObjArr.size(), "User object array should contain two users");

        User user1 = userObjArr.get(0);
        User user2 = userObjArr.get(1);

        assertEquals("user1", user1.getName(), "First user's name should be 'user1'");
        assertArrayEquals(data.get("user1"), user1.getWords(), "First user's words should match");

        assertEquals("user2", user2.getName(), "Second user's name should be 'user2'");
        assertArrayEquals(data.get("user2"), user2.getWords(), "Second user's words should match");
    }

    @Test
    void testSetWords() {
        // Arrange
        String leagueId = "league3";
        ArrayList<String> users = new ArrayList<>(Arrays.asList("user1", "user2"));
        League league = new League(leagueId, users);

        String[] newWords = {"newWord1", "newWord2", "newWord3", "newWord4", "newWord5"};

        league.setWords("user1", newWords);

        assertArrayEquals(newWords, league.getData().get("user1"), "User1's words should be updated");

        ArrayList<User> userObjArr = league.getUserObjArr();
        User updatedUser = null;
        for (User user : userObjArr) {
            if (user.getName().equals("user1")) {
                updatedUser = user;
                break;
            }
        }
        assertNotNull(updatedUser, "Updated user should not be null");
        assertArrayEquals(newWords, updatedUser.getWords(), "Updated user's words should match");
    }

    @Test
    void testSetAndGetRankings() {
        // Arrange
        League league = new League();
        ArrayList<User> rankings = new ArrayList<>();
        String[] words = {"word1", "word2", "word3", "word4", "word5"};
        User user1 = new CommonUser("user1", "", words);
        User user2 = new CommonUser("user2", "", words);
        rankings.add(user1);
        rankings.add(user2);

        league.setRankings(rankings);

        assertEquals(rankings, league.getRankings(), "Rankings should match");
    }

    @Test
    void testSetAndGetLiveRankings() {
        // Arrange
        League league = new League();
        ArrayList<User> liveRankings = new ArrayList<>();
        String[] words = {"word1", "word2", "word3", "word4", "word5"};
        User user1 = new CommonUser("user1", "", words);
        liveRankings.add(user1);

        league.setLiveRankings(liveRankings);

        assertEquals(liveRankings, league.getLiveRankings(), "Live rankings should match");
    }

    @Test
    void testSetAndGetHistoricalRankings() {
        // Arrange
        League league = new League();
        ArrayList<User> historicalRankings = new ArrayList<>();
        String[] words = {"word1", "word2", "word3", "word4", "word5"};
        User user1 = new CommonUser("user1", "", words);
        historicalRankings.add(user1);

        league.setHistoricalRankings(historicalRankings);

        assertEquals(historicalRankings, league.getHistoricalRankings(), "Historical rankings should match");
    }
}

class LeagueFactoryTest {

    @Test
    void testCreateDefaultLeague() {
        // Arrange
        LeagueFactory factory = new LeagueFactory();

        League league = factory.create();

        assertNotNull(league, "League should not be null");
        assertEquals("meow", league.getId(), "League ID should be 'meow'");
        assertEquals(1, league.getUsers().size(), "League should contain one user");
        assertEquals("hiii", league.getUsers().get(0), "User should be 'hiii'");
    }

    @Test
    void testCreateLeagueWithIdAndUsers() {
        // Arrange
        LeagueFactory factory = new LeagueFactory();
        String leagueName = "ChampionsLeague";
        ArrayList<String> usernames = new ArrayList<>(Arrays.asList("player1", "player2"));

        League league = factory.create(leagueName, usernames);

        assertNotNull(league, "League should not be null");
        assertEquals(leagueName, league.getId(), "League ID should match");
        assertEquals(usernames, league.getUsers(), "Usernames should match");
    }
}

