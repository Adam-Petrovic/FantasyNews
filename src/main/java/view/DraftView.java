package view;

import data_access.Constants;
import interface_adapter.draft_words.DraftState;
import interface_adapter.draft_words.DraftViewModel;
import interface_adapter.draft_words.DraftWordsController;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.solo_play.SoloPlayState;
import interface_adapter.to_draft.ToDraftController;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DraftView extends JPanel implements ActionListener, PropertyChangeListener {
    private final DraftViewModel draftViewModel;
    private JTable wordsTable;
    private JScrollPane jScrollPane;
    private GoHomeController goHomeController;
    private DraftWordsController draftWordsController;

    public DraftView(DraftViewModel draftViewModel) {
        this.draftViewModel = draftViewModel;
        this.draftViewModel.addPropertyChangeListener(this);

        jScrollPane = new JScrollPane();
        JPanel userOptions = new JPanel();
        userOptions.setLayout(new BoxLayout(userOptions, BoxLayout.X_AXIS));

        JButton goHomeButton = new JButton("←");
        userOptions.add(goHomeButton);

        JTextField wordInput = new JTextField(10);
        userOptions.add(wordInput);

        JButton draftWordButton = new JButton("draft");
        userOptions.add(draftWordButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(jScrollPane);
        this.add(userOptions);

        draftWordButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(draftWordButton)) {
                        Integer categoryNum = wordsTable.getSelectedColumn();
                        String newWord = draftViewModel.getState().getInputtedWord();
                        String name = draftViewModel.getState().getUsername();
                        String leagueID = draftViewModel.getState().getLeagueID();
                        draftWordsController.execute(name, categoryNum, newWord, leagueID);

                        System.out.println(draftViewModel.getState().getUsername() + " pressed draft!");
                    }
                });

        goHomeButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(goHomeButton)) {
                        goHomeController.execute();
                    }
                });
        wordInput.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final DraftState currentState = draftViewModel.getState();
                currentState.setInputtedWord(wordInput.getText());
                draftViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String[] words = draftViewModel.getState().getWords();
        System.out.println(words);
        String[][] wordsArray = {words};
        //Object[][] wordsEntry = {{"Default1", "Default2", "Default3", "Default4", "Default5"}};
        wordsTable = new JTable(wordsArray, Constants.CATEGORIES);
        wordsTable.setDefaultEditor(Object.class, null);
        jScrollPane.add(wordsTable);
        jScrollPane.setViewportView(wordsTable);
    }

    public void setGoHomeController(GoHomeController goHomeController){
        this.goHomeController = goHomeController;
    }
    public void setDraftWordsController(DraftWordsController draftWordsController){
        this.draftWordsController = draftWordsController;
    }
}
