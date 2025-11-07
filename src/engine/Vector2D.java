package engine;
public class Vector2D {
    private double x;
    private double y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Getter und Setter
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Addition von Vektoren
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    // Subtraktion von Vektoren
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    // Skalierung des Vektors
    public Vector2D scale(double factor) {
        return new Vector2D(this.x * factor, this.y * factor);
    }

    // Berechnung der Länge des Vektors
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    // Normalisierung des Vektors
    public Vector2D normalize() {
        double len = length();
        return new Vector2D(x / len, y / len);
    }
}
