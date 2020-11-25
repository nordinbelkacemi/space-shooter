package views;

import game.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

public class LeaderBoardPanel extends JPanel {

    private static final long serialVersionUID = 3359344461279206275L;

    private Window window;
    private SavedGamesData data;

    public LeaderBoardPanel(Window window) {
        this.window = window;
        data = new SavedGamesData();
        reload();
    }

    public void reload() {
        data.loadSavedGames();
        data.sortAndReduce(10);

        removeAll();

        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);
        table.setFillsViewportHeight(true);
        JScrollPane scrollpane = new JScrollPane(table);
        add(scrollpane, BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        top.add(backButton, FlowLayout.LEFT);
        add(top, BorderLayout.NORTH);

        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                window.showMenu();
            }
        });

    }
}
