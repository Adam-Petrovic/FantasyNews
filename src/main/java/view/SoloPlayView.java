package view;

import data_access.Constants;
import interface_adapter.login.LoginViewModel;
import interface_adapter.solo_play.SoloPlayController;
import interface_adapter.solo_play.SoloPlayViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SoloPlayView extends JPanel implements PropertyChangeListener {
    private final String viewName = Constants.SOLO_PLAY_VIEW_NAME;
    private final SoloPlayViewModel soloPlayViewModel;
    private SoloPlayController soloPlayController;


    public SoloPlayView(SoloPlayViewModel soloPlayViewModel) {
        this.soloPlayViewModel = soloPlayViewModel;
        this.soloPlayViewModel.addPropertyChangeListener(this);

        // Initializing the JTable
        JTable wordsTable =
                new JTable(soloPlayViewModel.getState().getWORDS(),
                        Constants.CATEGORIES);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.getViewport().add(wordsTable, null);

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
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setSoloPlayController(SoloPlayController soloPlayController){
        this.soloPlayController = soloPlayController;
    }

    public String getViewName(){
        return viewName;
    }
}
