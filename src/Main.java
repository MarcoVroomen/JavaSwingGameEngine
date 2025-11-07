import Engine.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Minimal bootstrap that wires the engine into a Swing window so the
 * application can be launched with a standard {@code public static void main}.
 */
public final class Main {
    private static final String DEFAULT_SCENE = "main";

    private Main() {
        // Utility class
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();

            Scene scene = new Scene();

            game.addScene(DEFAULT_SCENE, scene);
            game.setCurrentScene(DEFAULT_SCENE);

            GamePanel panel = new GamePanel(game);
            Input.addKeyListener(panel);

            JFrame frame = new JFrame("Java Swing Game Engine Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    game.stopGame();
                }
            });
            frame.setVisible(true);

            panel.requestFocusInWindow();
            game.startGame(panel);
        });
    }
}
