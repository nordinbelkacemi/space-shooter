package views;

import game.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
    /**
     * A játékpanel
     * 
     */
    private GamePanel gamePanel;

    /**
     * A játék objektum
     * 
     */
    private Game game;

    /**
     * Az idözitö hallgato konstruktora
     * 
     * Inicializálja játékpanel és játékpanel attribútumait.
     * @param gamePanel a játékpanel
     * @param game a játék
     * 
     */
    public TimerListener(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        this.game = game;
    }

    /**
     * Az ActionListener osztály actionPerformed függvény implementálása.
     * 
     * A játékpanel idözítöje idöközönként létrehoz egy eseményt, arra pedig ennek a függvénynek a
     * meghívásával válaszol a timer listener. Ha a játék nem ért még véget, akkor frissíti
     * (update-eli) a játékot.
     * 
     */
    public void actionPerformed(ActionEvent e) {
        if (!game.isOver()) {
            gamePanel.update();
        }
        gamePanel.handleGameEnd();
    }
}
