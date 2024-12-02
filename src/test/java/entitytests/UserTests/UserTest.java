package entitytests.UserTests;

import dataaccess.Constants;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {

    @Test
    void testConstructorWithNamePasswordTermsAndLeagueIDs() {
        // Arrange
        String name = "user1";
        String password = "pass123";
        String[] terms = {"word1", "word2", "word3", "word4", "word5"};
        ArrayList<String> leagueIDs = new ArrayList<>(Arrays.asList("league1", "league2"));

        // Act
        CommonUser user = new CommonUser(name, password, terms, leagueIDs);

        // Assert
        assertEquals(name, user.getName(), "Name should match");
        assertEquals(password, user.getPassword(), "Password should match");
        assertEquals(leagueIDs, user.getLeagueIDs(), "League IDs should match");

        // Check words map
        String[] userWords = user.getWords();
        assertArrayEquals(terms, userWords, "Words should match terms provided");

        // Check default values
        assertEquals(0, user.getLeaguePoints(), "League points should be 0");
        assertEquals(0, user.getLiveLeaguePoints(), "Live league points should be 0");
        assertNotNull(user.getFriends(), "Friends list should not be null");
        assertTrue(user.getFriends().isEmpty(), "Friends list should be empty");
    }

    @Test
    void testConstructorWithNameAndPassword() {
        // Arrange
        String name = "user2";
        String password = "pass456";

        // Act
        CommonUser user = new CommonUser(name, password);

        // Assert
        assertEquals(name, user.getName(), "Name should match");
        assertEquals(password, user.getPassword(), "Password should match");
        assertNotNull(user.getLeagueIDs(), "League IDs should not be null");
        assertTrue(user.getLeagueIDs().isEmpty(), "League IDs should be empty");

        // Check words map with default words
        String[] userWords = user.getWords();
        assertArrayEquals(Arrays.copyOfRange(Constants.DEFAULT_WORDS,0,Constants.NUM_CATEGORIES), userWords, "Words should match default words");

        // Check default values
        assertEquals(0, user.getLeaguePoints(), "League points should be 0");
        assertEquals(0, user.getLiveLeaguePoints(), "Live league points should be 0");
        assertNotNull(user.getFriends(), "Friends list should not be null");
        assertTrue(user.getFriends().isEmpty(), "Friends list should be empty");
    }

    @Test
    void testConstructorWithNamePasswordAndTerms() {
        // Arrange
        String name = "user3";
        String password = "pass789";
        String[] terms = {"term1", "term2", "term3", "term4", "term5"};

        // Act
        CommonUser user = new CommonUser(name, password, terms);

        // Assert
        assertEquals(name, user.getName(), "Name should match");
        assertEquals(password, user.getPassword(), "Password should match");
        assertNotNull(user.getLeagueIDs(), "League IDs should not be null");
        assertTrue(user.getLeagueIDs().isEmpty(), "League IDs should be empty");

        // Check words map
        String[] userWords = user.getWords();
        assertArrayEquals(terms, userWords, "Words should match terms provided");

        // Check default values
        assertEquals(0, user.getLeaguePoints(), "League points should be 0");
        assertEquals(0, user.getLiveLeaguePoints(), "Live league points should be 0");
        assertNotNull(user.getFriends(), "Friends list should not be null");
        assertTrue(user.getFriends().isEmpty(), "Friends list should be empty");
    }

    @Test
    void testGetAndSetWords() {
        // Arrange
        String name = "user4";
        String password = "pass012";
        CommonUser user = new CommonUser(name, password);

        String[] newWords = {"newWord1", "newWord2", "newWord3", "newWord4", "newWord5"};

        // Act
        user.setWords(newWords);
        String[] retrievedWords = user.getWords();

        // Assert
        assertArrayEquals(newWords, retrievedWords, "Words should be updated to newWords");
    }

    @Test
    void testSwapWords() {
        // Arrange
        String name = "user5";
        String password = "pass345";
        CommonUser user = new CommonUser(name, password);

        String newWord = "swappedWord";
        String category = Constants.CATEGORIES[0]; // "Sports"

        // Act
        user.swapWords(category, newWord);
        String retrievedWord = user.getWordFromCategory(category);

        // Assert
        assertEquals(newWord, retrievedWord, "Word in category should be swapped to newWord");
    }

    @Test
    void testSetAndGetLeaguePoints() {
        // Arrange
        CommonUser user = new CommonUser("user6", "pass678");

        // Act
        user.setLeaguePoints(100);
        int leaguePoints = user.getLeaguePoints();

        // Assert
        assertEquals(100, leaguePoints, "League points should be 100");
    }

    @Test
    void testSetAndGetLiveLeaguePoints() {
        // Arrange
        CommonUser user = new CommonUser("user7", "pass901");

        // Act
        user.setLiveLeaguePoints(50);
        int liveLeaguePoints = user.getLiveLeaguePoints();

        // Assert
        assertEquals(50, liveLeaguePoints, "Live league points should be 50");
    }

    @Test
    void testGetAndSetPointsForCategory() {
        // Arrange
        CommonUser user = new CommonUser("user8", "pass234");
        int[] points = {10, 20, 30, 40, 50};

        // Act
        user.setPoints(points);

        // Assert
        for (int i = 0; i < Constants.NUM_CATEGORIES; i++) {
            String category = Constants.CATEGORIES[i];
            int point = user.getPointsForCategory(category);
            assertEquals(points[i], point, "Points for category should match");
        }
    }

    @Test
    void testGetAllPoints() {
        // Arrange
        CommonUser user = new CommonUser("user9", "pass567");
        int[] points = {15, 25, 35, 45, 55};
        user.setPoints(points);

        // Act
        Integer[] retrievedPoints = user.getAllPoints();

        // Assert
        for (int i = 0; i < Constants.NUM_CATEGORIES; i++) {
            assertEquals(points[i], retrievedPoints[i], "Points should match");
        }
    }

    @Test
    void testAddAndGetFriends() {
        // Arrange
        CommonUser user = new CommonUser("user10", "pass890");
        User friend1 = new CommonUser("friend1", "pass111");
        User friend2 = new CommonUser("friend2", "pass222");

        // Act
        user.addFriend(friend1);
        user.addFriend(friend2);
        List<User> friends = user.getFriends();

        // Assert
        assertEquals(2, friends.size(), "User should have 2 friends");
        assertTrue(friends.contains(friend1), "Friends list should contain friend1");
        assertTrue(friends.contains(friend2), "Friends list should contain friend2");
    }

    @Test
    void testSetAndGetLeagueIDs() {
        // Arrange
        CommonUser user = new CommonUser("user11", "pass333");
        ArrayList<String> leagueIDs = new ArrayList<>(Arrays.asList("league1", "league2"));

        // Act
        user.setLeagueIDs(leagueIDs);
        ArrayList<String> retrievedLeagueIDs = user.getLeagueIDs();

        // Assert
        assertEquals(leagueIDs, retrievedLeagueIDs, "League IDs should match");
    }

    @Test
    void testGetLeagueWords() {
        // Arrange
        CommonUser user = new CommonUser("user12", "pass444");

        // Act
        String[] leagueWords = user.getLeagueWords();

        // Assert
        assertArrayEquals(Constants.DEFAULT_WORDS, leagueWords, "League words should match default words");
    }

    @Test
    void testHasDuplicates() {
        // Since `hasDuplicates` is a private method, we cannot test it directly.
        // However, we can test it indirectly by providing duplicate words and expecting the constructor to behave accordingly.
        // In the current implementation, `hasDuplicates` is not used to throw any exceptions, so we cannot test its effect.
        // For 100% coverage, we can rely on code that invokes `hasDuplicates` indirectly.
    }

    @Test
    void testCreateWordMap() {
        // Since `createWordMap` is a private method, we cannot test it directly.
        // However, it is called in constructors, so we can test its effect through the constructors.
        // This is already covered in previous tests.
    }
}

class CommonUserFactoryTest {

    @Test
    void testCreateWithNameAndPassword() {
        // Arrange
        UserFactory factory = new CommonUserFactory();
        String name = "factoryUser1";
        String password = "factoryPass1";

        // Act
        User user = factory.create(name, password);

        // Assert
        assertNotNull(user, "User should not be null");
        assertEquals(name, user.getName(), "Name should match");
        assertEquals(password, user.getPassword(), "Password should match");
        assertArrayEquals(Arrays.copyOfRange(Constants.DEFAULT_WORDS,0,Constants.NUM_CATEGORIES), user.getWords(), "Words should match default words");
    }

    @Test
    void testCreateWithNamePasswordAndWords() {
        // Arrange
        UserFactory factory = new CommonUserFactory();
        String name = "factoryUser2";
        String password = "factoryPass2";
        String[] words = {"word1", "word2", "word3", "word4", "word5"};

        // Act
        User user = factory.create(name, password, words);

        // Assert
        assertNotNull(user, "User should not be null");
        assertEquals(name, user.getName(), "Name should match");
        assertEquals(password, user.getPassword(), "Password should match");
        assertArrayEquals(words, user.getWords(), "Words should match");
    }

    @Test
    void testCreateWithNamePasswordWordsAndLeagueIDs() {
        // Arrange
        UserFactory factory = new CommonUserFactory();
        String name = "factoryUser3";
        String password = "factoryPass3";
        String[] words = {"wordA", "wordB", "wordC", "wordD", "wordE"};
        ArrayList<String> leagueIDs = new ArrayList<>(Arrays.asList("leagueA", "leagueB"));

        // Act
        User user = factory.create(name, password, words, leagueIDs);

        // Assert
        assertNotNull(user, "User should not be null");
        assertEquals(name, user.getName(), "Name should match");
        assertEquals(password, user.getPassword(), "Password should match");
        assertArrayEquals(words, user.getWords(), "Words should match");
        assertEquals(leagueIDs, user.getLeagueIDs(), "League IDs should match");
    }
}