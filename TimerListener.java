import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
    private GamePanel gamePanel;
    private Game game;

    public TimerListener(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {
        if (!game.isOver()) {
            gamePanel.update();
        }
    }
}
