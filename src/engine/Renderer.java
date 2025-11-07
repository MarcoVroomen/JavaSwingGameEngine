package engine;

import java.awt.Graphics2D;

public abstract class Renderer extends Component {
    public abstract void render(Graphics2D g, int screenWidth, int screenHeight);  // Rendert das Objekt
}