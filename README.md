# JavaSwingGameEngine

Eine kompakte, komponentenbasierte 2D-Game-Engine auf Basis von Java Swing. Die Engine kümmert sich um Game-Loop, Eingabe, Rendering sowie einfache Physik und erlaubt es dir, eigene Spiele auf Szenen- und GameObject-Ebene zu strukturieren.

## Hauptmerkmale
- **Szenenverwaltung** – Mehrere `Scene`-Instanzen lassen sich registrieren und über den `Game`-Controller austauschen.
- **Komponentenbasiertes Design** – `GameObject`-Instanzen können mit beliebigen Komponenten (z. B. `Renderer`, `Rigidbody2D`, Collider) erweitert werden.
- **Rendering mit Swing** – Die Zeichenlogik läuft über `GamePanel` und `Renderer`-Komponenten, die auf `Graphics2D` zeichnen.
- **Eingabeverarbeitung** – Die statische `Input`-Klasse kapselt Keyboard-Events und stellt framegenaue Abfragen wie `getKeyDown` bereit.
- **Zeit- und Physik-Hilfen** – `Time` liefert Delta-Zeiten; `Rigidbody2D` und Collider-Klassen unterstützen einfache Bewegungen und Kollisionsprüfungen.
- **Erweiterbare Utilities** – Vektorrechnungen (`Vector2D`), Transformationsverwaltung (`Transform`) und Logging (`Logger`) erleichtern das Entwickeln eigener Spielmechaniken.

## Architekturüberblick
### Game-Loop
`Game.startGame` startet in einem separaten Thread einen festen Update-Loop (Standard: 120 Ticks). Pro Tick werden `physicsUpdate`, `update`, `lateUpdate` sowie ein `repaint` des `GamePanel` ausgelöst. Gleichzeitig synchronisiert `Time.updateDeltaTime` den `deltaTime`-Wert für frameabhängige Berechnungen.

### Szenen und GameObjects
Jede `Scene` hält eine Liste an `GameObject`-Instanzen. Beim Start und in jedem Frame ruft die Szene die entsprechenden Lebenszyklus-Methoden (`start`, `update`, `physicsUpdate`, `lateUpdate`) für alle Objekte auf. So lassen sich Spiellogiken modular zusammenstellen.

### Komponenten & Rendering
`GameObject.addComponent` verknüpft Komponenten dynamisch mit Objekten. Alle Komponenten erben von `Component` und erhalten über `Component.getGameObject()` Zugriff auf Transform und weitere Komponenten. Render-Komponenten wie `Renderer` oder `SpriteRenderer2D` zeichnen im `lateUpdate` auf die Swing-Leinwand (`Graphics2D`).

### Physik & Kollision
`Rigidbody2D` implementiert einfache Physik mit Schwerkraft, linearem Drag und Positionsaktualisierung anhand von `Time.getDeltaTime()`. Collider (`Rectangle`, `Circle`, `Collider2D`, `CircleCollider2D`) unterstützen rudimentäre Kollisionsabfragen, die du in eigenen Komponenten auswerten kannst.

### Eingabe & Zeitverwaltung
Der `Input.KeyInputListener` hängt am `GamePanel` und puffert Tastaturzustände. Dadurch sind Abfragen wie „Taste wurde in diesem Frame gedrückt“ möglich. `Time` stellt `deltaTime` und `elapsedTime` zur Verfügung, sodass deine Updates frameunabhängig bleiben.

## Schnellstart
1. **Projekt kompilieren:**
   ```bash
   javac -d out $(find src -name "*.java")
   ```
2. **Demo starten:**
   ```bash
   java -cp out Main
   ```
   Dadurch öffnet sich ein Fenster mit dem vordefinierten `GamePanel`, das direkt den Game-Loop startet.

## Eigene Spiele erstellen
1. **Eigene Szene aufbauen:** Lege eine neue Klasse an, instanziiere `GameObject`-Objekte und registriere sie in einer `Scene`.
2. **Komponenten hinzufügen:** Erstelle neue Komponenten, indem du von `Component` erbst und die Lifecycle-Methoden überschreibst. Binde die Komponenten über `GameObject.addComponent` ein.
3. **Renderer implementieren:** Zeichne Sprites oder primitive Formen in einer Renderer-Komponente, indem du `render(Graphics2D g, …)` implementierst.
4. **Eingaben verarbeiten:** Nutze die statischen Methoden der `Input`-Klasse, um auf Tastendrücke zu reagieren, und kombiniere sie mit `Rigidbody2D` oder eigenen Bewegungslogiken.

Mit diesen Bausteinen kannst du schnell prototypische 2D-Spiele auf Basis von Java Swing entwickeln und bei Bedarf gezielt erweitern.
