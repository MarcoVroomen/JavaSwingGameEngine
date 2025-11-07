package engine;

public class CircleCollider2D extends Collider2D {
    private double radius;

    public CircleCollider2D() {
        this(0.0);
    }

    public CircleCollider2D(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public boolean checkIntersectionInterpolated(Collider2D other) {
        if (!(other instanceof CircleCollider2D)) {
            return false;
        }

        CircleCollider2D otherCircle = (CircleCollider2D) other;

        if (getGameObject() == null || getGameObject().getTransform() == null
                || otherCircle.getGameObject() == null || otherCircle.getGameObject().getTransform() == null) {
            return false;
        }

        Vector2D myPosition = getGameObject().getTransform().getPosition();
        Vector2D otherPosition = otherCircle.getGameObject().getTransform().getPosition();

        double distance = myPosition.subtract(otherPosition).length();

        return distance <= (radius + otherCircle.radius);
    }
}
