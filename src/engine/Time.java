package engine;

public final class Time {
    private static float deltaTime;

    private Time() {
        // Utility class
    }

    public static void updateDeltaTime(long now, long lastTime) {
        deltaTime = (now - lastTime) / 1_000_000_000.0f;  // Nanosekunden → Sekunden
    }

    public static float getDeltaTime() {
        return deltaTime;
    }
}