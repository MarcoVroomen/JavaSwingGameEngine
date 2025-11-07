package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

// Zentrale Eingabeverwaltung für Tastaturereignisse
public final class Input {
    // Aktueller Tastenzustand pro KeyCode
    private static boolean[] keyStates = new boolean[256];

    // Tastenzustand des vorigen Frames
    private static boolean[] prevKeyStates = new boolean[256];

    private Input() {
        // Utility class
    }

    // Überprüft, ob eine Taste (als Zeichen) in diesem Frame gedrückt wurde
    public static boolean getKeyDown(char key) {
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(key);
        if (keyCode == KeyEvent.VK_UNDEFINED) {
            return false;
        }
        return getKeyDown(keyCode);
    }

    // Überprüft, ob eine Taste (als Zeichen) in diesem Frame losgelassen wurde
    public static boolean getKeyUp(char key) {
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(key);
        if (keyCode == KeyEvent.VK_UNDEFINED) {
            return false;
        }
        return getKeyUp(keyCode);
    }

    // Überprüft, ob eine Taste (als Zeichen) gehalten wird
    public static boolean getKey(char key) {
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(key);
        if (keyCode == KeyEvent.VK_UNDEFINED) {
            return false;
        }
        return getKey(keyCode);
    }

    // Überprüft, ob eine Taste (KeyCode) in diesem Frame gedrückt wurde
    public static boolean getKeyDown(int keyCode) {
        if (keyCode < 0) {
            return false;
        }
        ensureCapacity(keyCode);
        return keyStates[keyCode] && !prevKeyStates[keyCode];
    }

    // Überprüft, ob eine Taste (KeyCode) in diesem Frame losgelassen wurde
    public static boolean getKeyUp(int keyCode) {
        if (keyCode < 0) {
            return false;
        }
        ensureCapacity(keyCode);
        return !keyStates[keyCode] && prevKeyStates[keyCode];
    }

    // Überprüft, ob eine Taste (KeyCode) gehalten wird
    public static boolean getKey(int keyCode) {
        if (keyCode < 0) {
            return false;
        }
        ensureCapacity(keyCode);
        return keyStates[keyCode];
    }

    // Listener für Tastaturereignisse
    public static class KeyInputListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if (code < 0) {
                return;
            }
            ensureCapacity(code);
            keyStates[code] = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            if (code < 0) {
                return;
            }
            ensureCapacity(code);
            keyStates[code] = false;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Nicht benötigt
        }
    }

    // Fügt den KeyListener einem Panel hinzu und stellt sicher, dass es Fokus erhält
    public static void addKeyListener(JPanel panel) {
        KeyInputListener keyListener = new KeyInputListener();
        panel.addKeyListener(keyListener);
        panel.setFocusable(true);
        panel.setFocusTraversalKeysEnabled(false);
        panel.requestFocusInWindow();
    }

    // Synchronisiert den Tastenzustand für den nächsten Frame
    public static void update() {
        if (prevKeyStates.length != keyStates.length) {
            boolean[] resized = new boolean[keyStates.length];
            System.arraycopy(prevKeyStates, 0, resized, 0, Math.min(prevKeyStates.length, keyStates.length));
            prevKeyStates = resized;
        }
        System.arraycopy(keyStates, 0, prevKeyStates, 0, keyStates.length);
    }

    // Vergrößert die Zustandsarrays bei Bedarf
    private static void ensureCapacity(int keyCode) {
        if (keyCode < 0 || keyCode < keyStates.length) {
            return;
        }
        int newLen = keyStates.length;
        while (newLen <= keyCode) {
            newLen *= 2;
            if (newLen > 1 << 16) {
                newLen = 1 << 16;
                break;
            }
        }
        boolean[] newKeyStates = new boolean[newLen];
        boolean[] newPrevKeyStates = new boolean[newLen];
        System.arraycopy(keyStates, 0, newKeyStates, 0, keyStates.length);
        System.arraycopy(prevKeyStates, 0, newPrevKeyStates, 0, prevKeyStates.length);
        keyStates = newKeyStates;
        prevKeyStates = newPrevKeyStates;
    }
}
