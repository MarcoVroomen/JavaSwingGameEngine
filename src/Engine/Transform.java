package Engine;

public class Transform {
    private Vector2D position;
    private Vector2D scale;
    private double rotation; // Rotation in Grad

    public Transform() {
        this.position = new Vector2D();
        this.scale = new Vector2D(1, 1); // Standard-Skalierung (keine Skalierung)
        this.rotation = 0; // Standard-Rotation (keine Rotation)
    }

    // Getter und Setter
    public Vector2D getPosition() { return position; }
    public void setPosition(Vector2D position) { this.position = position; }
    public Vector2D getScale() { return scale; }
    public void setScale(Vector2D scale) { this.scale = scale; }
    public double getRotation() { return rotation; }
    public void setRotation(double rotation) { this.rotation = rotation; }

    // Berechnung der Transformationsmatrix
    public double[] getTransformationMatrix() {
        double cos = Math.cos(Math.toRadians(rotation));
        double sin = Math.sin(Math.toRadians(rotation));
        double[] matrix = {
                cos * scale.getX(), -sin * scale.getY(), position.getX(),
                sin * scale.getX(), cos * scale.getY(), position.getY(),
                0, 0, 1
        };
        return matrix;
    }
}
