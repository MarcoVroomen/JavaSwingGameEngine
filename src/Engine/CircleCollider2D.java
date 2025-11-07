package Engine;

public class CircleCollider2D extends Collider2D
{
    private double radius;

    public CircleCollider2D()
    {
        this(0.0);
    }

    public CircleCollider2D(double radius)
    {
        this.radius = radius;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    @Override
    public boolean CheckIntersectionInterpolated(Collider2D other)
    {
        if (!(other instanceof CircleCollider2D))
        {
            return false;
        }

        CircleCollider2D otherCircle = (CircleCollider2D) other;

        if (MyGameObject == null || MyGameObject.GetTransform() == null ||
            otherCircle.MyGameObject == null || otherCircle.MyGameObject.GetTransform() == null)
        {
            return false;
        }

        Vector2D myPosition = MyGameObject.GetTransform().getPosition();
        Vector2D otherPosition = otherCircle.MyGameObject.GetTransform().getPosition();

        double distance = myPosition.subtract(otherPosition).length();

        return distance <= (radius + otherCircle.radius);
    }
}
