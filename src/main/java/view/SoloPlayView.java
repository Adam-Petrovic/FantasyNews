package view;

import data_access.Constants;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.solo_play.SoloPlayController;
import interface_adapter.solo_play.SoloPlayState;
import interface_adapter.solo_play.SoloPlayViewModel;
import interface_adapter.add_word.AddWordController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SoloPlayView extends JPanel implements PropertyChangeListener {
    private final String viewName = Constants.SOLO_PLAY_VIEW_NAME;
    private final SoloPlayViewModel soloPlayViewModel;
    private SoloPlayController soloPlayController;
    private AddWordController addWordController;
    private JScrollPane jScrollPane;
    private JTable wordsTable;

    public SoloPlayView(SoloPlayViewModel soloPlayViewModel) {
        this.soloPlayViewModel = soloPlayViewModel;
        this.soloPlayViewModel.addPropertyChangeListener(this);

        // Initializing the JTable
        wordsTable =
                new JTable(soloPlayViewModel.getState().getWORDS(),
                        Constants.CATEGORIES);

        jScrollPane = new JScrollPane();
//        jScrollPane.getViewport().add(wordsTable, null);

        JPanel userOptions = new JPanel();
        userOptions.setLayout(new BoxLayout(userOptions, BoxLayout.X_AXIS));

        JTextField wordInput = new JTextField(10);
        userOptions.add(wordInput);

        JButton addWord = new JButton("Add");
        userOptions.add(addWord);

        JButton removeWord = new JButton("Remove");
        userOptions.add(removeWord);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(jScrollPane);
        this.add(userOptions);

        addWord.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addWord)) {
                        System.out.println("Add Button Pressed");
                        addWordController.execute(soloPlayViewModel.getState().getWord());
                        //addWordController.refreshSoloPlayView();
                        soloPlayViewModel.firePropertyChanged();
                    }
                });

        wordInput.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SoloPlayState currentState = soloPlayViewModel.getState();
                currentState.setWord(new String(wordInput.getText()));
                System.out.println(currentState.getWord());
                soloPlayViewModel.setState(currentState);
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
    public void propertyChange(PropertyChangeEvent evt) {
        jScrollPane.getViewport().add(wordsTable, null);
    }

    public void setSoloPlayController(SoloPlayController soloPlayController){
        this.soloPlayController = soloPlayController;
    }

    public String getViewName(){
        return viewName;
    }

    public void setAddWordController(AddWordController controller) {
        this.addWordController = controller;
    }
}
