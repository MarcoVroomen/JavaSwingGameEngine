package engine;

public abstract class Collider2D extends Component {
    @Override
    public void start() {
        // Collider haben in der Regel keinen Initialisierungsbedarf.
    }

    @Override
    public void update() {
        // Collider reagieren standardmäßig nicht auf das reguläre Update.
    }

    @Override
    public void physicsUpdate() {
        // Die physikalische Verarbeitung erfolgt zentral in der Szene.
    }

    @Override
    public void lateUpdate() {
        // Collider benötigen kein LateUpdate per Standard.
    }

    public void onCollision(Collider2D other) {
        // Kann in abgeleiteten Klassen überschrieben werden, um Kollisionen zu behandeln.
    }

    public abstract boolean checkIntersectionInterpolated(Collider2D other);
}
