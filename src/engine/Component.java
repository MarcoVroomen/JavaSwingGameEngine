package engine;

/**
 * Base type for all components that can be attached to a {@link GameObject}.
 * Implementations receive lifecycle callbacks (start/update/physics/late)
 * from the engine.
 */
public abstract class Component {
    private GameObject gameObject;

    public GameObject getGameObject() {
        return gameObject;
    }

    void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract void start();

    public abstract void update();

    public abstract void physicsUpdate();

    public abstract void lateUpdate();
}
