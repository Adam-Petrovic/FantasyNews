package entity;
import data_access.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private int points = 0;
    private final HashMap<String, String> words = new HashMap<>();



    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        createWordMap();
//        if (draftedWords.length > Constants.NUM_CATEGORIES) {
//            throw new InstantiationError("Inputted Words are too long");
//        }
//
//        else if (hasDuplicates(draftedWords)) {
//            throw new InstantiationError("Duplicated words in drafted word list");
//        }

//        else {
//            createWordMap(draftedWords);
//        }
    }

    private void createWordMap() {
        String[] terms = {"Volleyball", "Gattaca", "Green Party", "Leaves", "Jokic"};
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            this.words.put(Constants.CATEGORIES[index], terms[index]);
        }
    }

    private boolean hasDuplicates(String[] draftedWords) {
        Set<String> simplifiedWords = new HashSet<>(List.of(draftedWords));
        return draftedWords.length == simplifiedWords.size();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getWordFromCategory(String category) {
        return this.words.get(category);
    }

    @Override
    public void swapWords(String category, String word) {
        this.words.replace(category, word);
    }

    @Override
    public String[] getWords() {
        String[] words = new String[Constants.NUM_CATEGORIES];
        for(int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            words[index] = this.words.get(Constants.CATEGORIES[index]);
        }
        return words;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int getPoints() {
        return points;
    }


}
