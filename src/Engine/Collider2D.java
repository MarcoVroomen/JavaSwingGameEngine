package Engine;

public abstract class Collider2D extends Component
{
    @Override
    public void Start()
    {
        // Collider haben in der Regel keinen Initialisierungsbedarf.
    }

    @Override
    public void Update()
    {
        // Collider reagieren standardmäßig nicht auf das reguläre Update.
    }

    @Override
    public void PhysicsUpdate()
    {
        // Die physikalische Verarbeitung erfolgt zentral in der Szene.
    }

    @Override
    public void LateUpdate()
    {
        // Collider benötigen kein LateUpdate per Standard.
    }

    public void OnCollision(Collider2D other)
    {
        // Kann in abgeleiteten Klassen überschrieben werden, um Kollisionen zu behandeln.
    }

    public abstract boolean CheckIntersectionInterpolated(Collider2D other);
}
