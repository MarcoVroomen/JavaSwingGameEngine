package Engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Renderer {
    private Vector2D pivot;
    private int radius;
    private Color color;

    public Circle(double pivotX, double pivotY, int radius, Color color) {
        this.pivot = new Vector2D(pivotX, pivotY);
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void Start() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void PhysicsUpdate() {

    }

    @Override
    public void LateUpdate() {

    }

    @Override
    public void Render(Graphics2D g, int screenWidth, int screenHeight) {
        g.setColor(color);

        // Berechnung der Position des Kreises relativ zur unteren linken Ecke
        int xPos = (int) Math.round(MyGameObject.GetTransform().getPosition().getX() - 2 * radius * pivot.getX());
        int yPos = screenHeight - ((int) Math.round(MyGameObject.GetTransform().getPosition().getY() + 2 * radius * pivot.getY()));

        // Zeichnen des Kreises
        g.fillOval(xPos, yPos, radius * 2, radius * 2);
    }
}
