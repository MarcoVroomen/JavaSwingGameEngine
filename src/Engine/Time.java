package Engine;

public class Time {
    private static float deltaTime;

    public static void updateDeltaTime(long now, long lastTime) {
        deltaTime = (now - lastTime) / 1_000_000_000.0f;  // Nanosekunden → Sekunden
    }

    public static float getDeltaTime() {
        return deltaTime;
    }
}