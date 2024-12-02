package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data_access.Constants;
import interface_adapter.draft_words.DraftState;
import interface_adapter.draft_words.DraftViewModel;
import interface_adapter.draft_words.DraftWordsController;
import interface_adapter.go_home.GoHomeController;

/**
 * Draft View for draft use case.
 */
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

        JTextField wordInput = new JTextField(Constants.TEXT_FIELD_LENGTH);
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
                extracted(draftViewModel, wordInput);
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

    private static void extracted(DraftViewModel draftViewModel, JTextField wordInput) {
        final DraftState currentState = draftViewModel.getState();
        currentState.setInputtedWord(wordInput.getText());
        draftViewModel.setState(currentState);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String[] words = draftViewModel.getState().getWords();
        System.out.println(words);
        String[][] wordsArray = {words};
        wordsTable = new JTable(wordsArray, Constants.CATEGORIES);
        wordsTable.setDefaultEditor(Object.class, null);
        jScrollPane.add(wordsTable);
        jScrollPane.setViewportView(wordsTable);
    }
    /**
     * Controller for go home button.
     * @param goHomeController Controller for go home.
     */

    public void setGoHomeController(GoHomeController goHomeController) {
        this.goHomeController = goHomeController;
    }

    /**
     * Controller for draft words button.
     * @param draftWordsController Controller for draft words.
     */
    public void setDraftWordsController(DraftWordsController draftWordsController) {
        this.draftWordsController = draftWordsController;
    }
}
