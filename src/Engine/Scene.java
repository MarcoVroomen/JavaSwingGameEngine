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
        for (GameObject obj : objects) {
            obj.PhysicsUpdate();  // Jedes Objekt in der Szene rendern
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
