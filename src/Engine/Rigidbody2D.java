package Engine;

public class Rigidbody2D extends Component
{
    private Vector2D velocity = new Vector2D(0, 0);

    private float linearDrag = 1.0f;

    private float gravityScale = -981.0f;

    private boolean useGravity = true;

    public float get_gravityScale() {
        return gravityScale;
    }

    public void set_gravityScale(float _gravityScale) {
        this.gravityScale = _gravityScale;
    }

    public boolean isUseGravity() {
        return useGravity;
    }

    public void setUseGravity(boolean useGravity) {
        this.useGravity = useGravity;
    }

    public float getLinearDrag() {
        return linearDrag;
    }

    public void setLinearDrag(float linearDrag) {
        this.linearDrag = linearDrag;
    }

    @Override
    public void Start() {
        // Hier könnten Anfangswerte gesetzt oder Validierungen durchgeführt werden.
    }

    @Override
    public void Update() {
        // Möglicher Platz für benutzergesteuerte Bewegungslogik
    }

    @Override
    public void PhysicsUpdate() {
        // 1. Gravitation anwenden

        if (useGravity) {
            velocity = velocity.add(new Vector2D(0, gravityScale * Time.getDeltaTime()));
        }

        // 2. Luftwiderstand anwenden
        velocity = velocity.scale(1.0f - linearDrag * Time.getDeltaTime());

        // 3. Position aktualisieren
        Vector2D currentPosition = MyGameObject.GetTransform().getPosition();
        Vector2D deltaMovement = velocity.scale(Time.getDeltaTime());
        Vector2D newPosition = currentPosition.add(deltaMovement);
        MyGameObject.GetTransform().setPosition(newPosition);
    }

    @Override
    public void LateUpdate() {
        // Hier könnte z. B. eine Kollisionserkennung kommen
    }

    public void AddVelocity(Vector2D velocityToAdd){
        this.velocity = this.velocity.add(velocityToAdd);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
