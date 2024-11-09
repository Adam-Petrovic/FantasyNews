package view;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.soloplay.SoloPlayState;
import interface_adapter.soloplay.SoloPlayViewModel;
import interface_adapter.soloplay.SoloPlayController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logged into the program.
 */
public class SoloPlayView extends JPanel implements PropertyChangeListener {

    private final String viewName = "solo play";
    private final SoloPlayViewModel soloPlayViewModel;
    private AddWordController addWordController;
    private RemoveWordController removeWordController;

    private final JTable jTable;

    private final JTextField wordInputField = new JTextField(15);
    private final JButton addWordButton = new JButton("Add Word");
    private final JButton removeWordButton = new JButton("Add Word");

    public SoloPlayView(SoloPlayViewModel soloPlayViewModel) {
        this.soloPlayViewModel = soloPlayViewModel;
        this.soloPlayViewModel.addPropertyChangeListener(this);

        jTable = new JTable();

        final JLabel title = new JLabel("Solo Play Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel word = new LabelTextPanel(
                new JLabel("Word"), wordInputField);

        final JPanel buttons = new JPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        wordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SoloPlayState currentState = SoloPlayViewModel.getState();
                currentState.word(wordInputField.getText());
                SoloPlayViewModel.setState(currentState);
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

        addWordButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(addWordButton)) {
                        final SoloPlayState currentState = SoloPlayViewModel.getState();

                        this.addWordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        removeWordButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(removeWordButton)) {
                        removeWordController.execute(removeWordButton.getState().getUsername());
                    }
                }
        );

        this.add(title);
        this.add(jTable);
        this.add(word);

        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }
}
