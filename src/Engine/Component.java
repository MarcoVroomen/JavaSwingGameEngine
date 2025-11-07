package Engine;

public abstract class Component
{
    public GameObject MyGameObject;

    public abstract void Start();

    public abstract void Update();

    public abstract void PhysicsUpdate();

    public abstract void LateUpdate();
}
