package Engine;

import java.util.ArrayList;
import java.util.List;

public class GameObject
{
    public String Name;

    private final List<Component> components;
    private Transform transform;

    public void SetTransform(Transform value)
    {
        this.transform = value;
    }

    public Transform GetTransform()
    {
        return transform;
    }

    public GameObject(String name)
    {
        Name = name;
        components = new ArrayList<>();
        this.transform = new Transform();
    }

    public <T extends Component> void AddComponent(Component component)
    {
        component.MyGameObject = this;
        components.add(component);
    }

    public void Start()
    {
        for (Component component : components)
        {
            component.Start();  // Jedes Objekt in der Szene starten
        }
    }

    public void Update()
    {
        for (Component component : components)
        {
            component.Update();  // Jedes Objekt in der Szene updaten
        }
    }

    public void PhysicsUpdate()
    {
        for (Component component : components)
        {
            component.PhysicsUpdate();  // Jedes Objekt in der Szene physikalisch updaten
        }
    }

    public void LateUpdate()
    {
        for (Component component : components)
        {
            component.LateUpdate();  // Jedes Objekt in der Szene rendern
        }
    }

    public void InvokeComponents()
    {

    }

    public <T extends Component> T GetComponent(Class<T> type)
    {
        for (Component component : components)
        {
            if (type.isInstance(component))
            {
                return type.cast(component);
            }
        }
        return null; // Gibt null zurück, wenn kein passendes Engine.Component gefunden wird
    }
}
