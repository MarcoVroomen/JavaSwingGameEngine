package engine;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    private String name;

    private final List<Component> components;
    private Transform transform;

    public GameObject(String name) {
        this.name = name;
        components = new ArrayList<>();
        this.transform = new Transform();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public <T extends Component> T addComponent(T component) {
        component.setGameObject(this);
        components.add(component);
        return component;
    }

    public void start() {
        for (Component component : components) {
            component.start();  // Jedes Objekt in der Szene starten
        }
    }

    public void update() {
        for (Component component : components) {
            component.update();  // Jedes Objekt in der Szene updaten
        }
    }

    public void physicsUpdate() {
        for (Component component : components) {
            component.physicsUpdate();  // Jedes Objekt in der Szene physikalisch updaten
        }
    }

    public void lateUpdate() {
        for (Component component : components) {
            component.lateUpdate();  // Jedes Objekt in der Szene rendern
        }
    }

    public void invokeComponents() {
        // Reserved for future use
    }

    public <T extends Component> T getComponent(Class<T> type) {
        for (Component component : components) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }
        return null; // Gibt null zurück, wenn keine passende Komponente gefunden wird
    }
}
