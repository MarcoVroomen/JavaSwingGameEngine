package Engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Scene {
    private List<GameObject> objects;

    public List<GameObject> GetGameObjects()
    {
        return objects;
    }

    public Scene()
    {
        objects = new ArrayList<>();
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void Start(){
        for (GameObject obj : objects) {
            obj.Start();  // Jedes Objekt in der Szene rendern
        }
    }

    public void Update(){
        for (GameObject obj : objects) {
            obj.Update();  // Jedes Objekt in der Szene rendern
        }
    }

    public void PhysicsUpdate(){
        List<Collider2D> colliders = new ArrayList<>();

        for (GameObject obj : objects) {
            obj.PhysicsUpdate();  // Jedes Objekt in der Szene rendern

            Collider2D collider = obj.GetComponent(Collider2D.class);
            if (collider != null)
            {
                colliders.add(collider);
            }
        }

        for (int i = 0; i < colliders.size(); i++)
        {
            Collider2D first = colliders.get(i);
            for (int j = i + 1; j < colliders.size(); j++)
            {
                Collider2D second = colliders.get(j);

                if (!first.CheckIntersectionInterpolated(second))
                {
                    continue;
                }

                first.OnCollision(second);
                second.OnCollision(first);

                Rigidbody2D firstBody = first.MyGameObject.GetComponent(Rigidbody2D.class);
                if (firstBody != null)
                {
                    firstBody.setVelocity(new Vector2D(0, 0));
                }

                Rigidbody2D secondBody = second.MyGameObject.GetComponent(Rigidbody2D.class);
                if (secondBody != null)
                {
                    secondBody.setVelocity(new Vector2D(0, 0));
                }
            }
        }
    }

    public void LateUpdate(){
        for (GameObject obj : objects) {
            obj.LateUpdate();  // Jedes Objekt in der Szene rendern
        }
    }

    public void render(Graphics2D g, int screenWidth, int screenHeight) {
        for (GameObject obj : objects)
        {
            Renderer renderer = obj.<Renderer>GetComponent(Renderer.class);  // Jedes renderbare Objekt in der Szene rendern
            if (renderer != null)
            {
                renderer.Render(g, screenWidth, screenHeight);
            }
        }
    }
}
