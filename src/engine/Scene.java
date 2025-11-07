package engine;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final List<GameObject> objects;

    public Scene() {
        objects = new ArrayList<>();
    }

    public List<GameObject> getGameObjects() {
        return objects;
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void start() {
        for (GameObject obj : objects) {
            obj.start();
        }
    }

    public void update() {
        for (GameObject obj : objects) {
            obj.update();
        }
    }

    public void physicsUpdate() {
        List<Collider2D> colliders = new ArrayList<>();

        for (GameObject obj : objects) {
            obj.physicsUpdate();

            Collider2D collider = obj.getComponent(Collider2D.class);
            if (collider != null) {
                colliders.add(collider);
            }
        }

        for (int i = 0; i < colliders.size(); i++) {
            Collider2D first = colliders.get(i);
            for (int j = i + 1; j < colliders.size(); j++) {
                Collider2D second = colliders.get(j);

                if (!first.checkIntersectionInterpolated(second)) {
                    continue;
                }

                first.onCollision(second);
                second.onCollision(first);

                GameObject firstObject = first.getGameObject();
                if (firstObject != null) {
                    Rigidbody2D firstBody = firstObject.getComponent(Rigidbody2D.class);
                    if (firstBody != null) {
                        firstBody.setVelocity(new Vector2D(0, 0));
                    }
                }

                GameObject secondObject = second.getGameObject();
                if (secondObject != null) {
                    Rigidbody2D secondBody = secondObject.getComponent(Rigidbody2D.class);
                    if (secondBody != null) {
                        secondBody.setVelocity(new Vector2D(0, 0));
                    }
                }
            }
        }
    }

    public void lateUpdate() {
        for (GameObject obj : objects) {
            obj.lateUpdate();
        }
    }

    public void render(Graphics2D g, int screenWidth, int screenHeight) {
        for (GameObject obj : objects) {
            Renderer renderer = obj.getComponent(Renderer.class);
            if (renderer != null) {
                renderer.render(g, screenWidth, screenHeight);
            }
        }
    }
}
