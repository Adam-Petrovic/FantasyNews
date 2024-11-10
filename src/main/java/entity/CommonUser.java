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



    public CommonUser(String name, String password, String[] draftedWords) throws InstantiationError {
        this.name = name;
        this.password = password;

        if (draftedWords.length > Constants.NUM_CATEGORIES) {
            throw new InstantiationError("Inputted Words are too long");
        }

        else if (hasDuplicates(draftedWords)) {
            throw new InstantiationError("Duplicated words in drafted word list");
        }

        else {
            createWordMap(draftedWords);
        }
    }

    private void createWordMap(String[] draftedWords) {
        for (int index = 0; index < Constants.NUM_CATEGORIES; index++) {
            if (draftedWords[index].isEmpty()) {
                this.words.put(Constants.CATEGORIES[index], "EMPTY");
            }
            else {
                this.words.put(Constants.CATEGORIES[index], draftedWords[index]);
            }
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
        return words.values().toArray(new String[0]);
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
