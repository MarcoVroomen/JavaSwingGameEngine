package Engine;

import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends Renderer {
    private int x, y, width, height;
    private Color color;

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        g.fillRect(x, y, width, height);  // Zeichnet das Rechteck
    }
}
