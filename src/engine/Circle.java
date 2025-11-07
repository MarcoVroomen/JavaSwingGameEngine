package engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Renderer {
    private final Vector2D pivot;
    private final int radius;
    private final Color color;

    public Circle(double pivotX, double pivotY, int radius, Color color) {
        this.pivot = new Vector2D(pivotX, pivotY);
        this.radius = radius;
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

        // Berechnung der Position des Kreises relativ zur unteren linken Ecke
        int xPos = (int) Math.round(owner.getTransform().getPosition().getX() - 2 * radius * pivot.getX());
        int yPos = screenHeight - ((int) Math.round(owner.getTransform().getPosition().getY() + 2 * radius * pivot.getY()));

        // Zeichnen des Kreises
        g.fillOval(xPos, yPos, radius * 2, radius * 2);
    }
}
