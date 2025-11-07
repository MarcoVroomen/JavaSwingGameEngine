package engine;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Swing panel that delegates rendering to the currently active engine scene.
 */
public class GamePanel extends JPanel {
    private final Game game;

    public GamePanel(Game game) {
        this.game = game;
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Scene scene = game.getCurrentScene();
        if (scene == null) {
            return;
        }

        Graphics2D graphics2D = (Graphics2D) g.create();
        try {
            scene.render(graphics2D, getWidth(), getHeight());
        } finally {
            graphics2D.dispose();
        }
    }
}
