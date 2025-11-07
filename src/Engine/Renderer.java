package Engine;

import java.awt.Graphics2D;

public abstract class Renderer extends Component {
    public abstract void Render(Graphics2D g, int screenWidth, int screenHeight);  // Rendert das Objekt
}