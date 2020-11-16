import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
    private GamePanel gamePanel;

    public TimerListener(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void actionPerformed(ActionEvent e) {
        this.gamePanel.update();
    }
}