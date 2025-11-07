package Engine;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<String, Scene> scenes;
    private Scene currentScene;
    private boolean running;

    public Game() {
        scenes = new HashMap<>();
    }

    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String name) {
        currentScene = scenes.get(name);
    }

    public void startGame(JPanel panel) {
        running = true;

        // Starte das Spiel in einem neuen Thread, um die GUI nicht zu blockieren
        new Thread(() -> {

            Start();

            // FPS-Steuerung
            long lastTime = System.nanoTime();
            double nsPerTick = 1000000000.0 / 120.0;  // 60 FPS
            double delta = 0;

            while (running) {
                long now = System.nanoTime();
                Time.updateDeltaTime(now, lastTime);
                delta += (now - lastTime) / nsPerTick;
                lastTime = now;

                if (delta >= 1.0) {
                    update();
                    panel.repaint();
                    delta -= 1.0;
                }

                // Sleep einbauen, um CPU zu entlasten
                try {
                    Thread.sleep(10);  // Warte 10 Millisekunden
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void Start(){
        currentScene.Start();
    }

    private void update() {
        if (currentScene != null) {
            currentScene.PhysicsUpdate();
            currentScene.Update();
            currentScene.LateUpdate();
            Input.update();
        }
    }

    public void stopGame() {
        running = false;
    }
}
