package engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends Renderer {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Color color;

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void start() {
        // Kein spezieller Initialisierungsbedarf
    }

    @Override
    public void update() {
        // Kein reguläres Update notwendig
    }

    @Override
    public void physicsUpdate() {
        // Keine physikalische Verarbeitung notwendig
    }

    @Override
    public void lateUpdate() {
        // Kein LateUpdate benötigt
    }

    @Override
    public void render(Graphics2D g, int screenWidth, int screenHeight) {
        GameObject owner = getGameObject();
        if (owner == null || owner.getTransform() == null) {
            return;
        }

        g.setColor(color);

        Vector2D position = owner.getTransform().getPosition();
        int screenX = (int) Math.round(position.getX() + x);
        int screenY = screenHeight - (int) Math.round(position.getY() + y + height);

        g.fillRect(screenX, screenY, width, height);  // Zeichnet das Rechteck basierend auf dem Transform
    }
}
