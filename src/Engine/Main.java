package Engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
            scene.addObject(createDemoObject());

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

    private static GameObject createDemoObject() {
        GameObject demo = new GameObject("Demo Sprite");
        demo.AddComponent(new DemoRenderer());
        return demo;
    }

    private static final class DemoRenderer extends Renderer {
        private float hue;

        @Override
        public void Start() {
            hue = 0f;
        }

        @Override
        public void Update() {
            hue += Time.getDeltaTime() * 0.2f;
            if (hue > 1f) {
                hue -= 1f;
            }
        }

        @Override
        public void PhysicsUpdate() {
            // Kein Physikverhalten im Demo-Renderer nötig
        }

        @Override
        public void LateUpdate() {
            // Keine Nachbearbeitung nötig
        }

        @Override
        public void Render(Graphics2D g, int screenWidth, int screenHeight) {
            int size = Math.min(screenWidth, screenHeight) / 3;
            int x = (screenWidth - size) / 2;
            int y = (screenHeight - size) / 2;

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.getHSBColor(hue, 0.6f, 0.9f));
            g.fillRoundRect(x, y, size, size, size / 4, size / 4);
            g.setColor(Color.BLACK);
            g.drawRoundRect(x, y, size, size, size / 4, size / 4);
        }
    }
}
