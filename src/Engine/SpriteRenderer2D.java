package Engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Zeichnet ein zugewiesenes Bild (Sprite) auf ein Graphics2D.
 * Position und optionale Zielgröße sind in "Tile"-Einheiten angegeben und
 * werden beim Rendern mit tileSize multipliziert, sodass alles am Raster ausgerichtet ist.
 */
public class SpriteRenderer2D extends Renderer
{
    // --- Raster-Konfiguration ---
    private int tileSize = 32; // Größe einer Kachel in Pixeln (konfigurierbar)

    // --- Sprite-Daten (alle Werte in Tile-Einheiten, außer image selbst) ---
    private Image image;               // Das zu zeichnende Bild
    private int x = 0;                 // Ziel-Position (links/oben) in Tiles
    private int y = 0;                 // in Tiles
    private Integer drawWidth = null;  // Zielbreite in Tiles; null => aus Bildgröße abgeleitet & ans Raster angepasst
    private Integer drawHeight = null; // Zielhöhe in Tiles;  null => aus Bildgröße abgeleitet & ans Raster angepasst
    private float opacity = 1.0f;      // 0..1 (Transparenz)
    private boolean visible = true;    // Sichtbarkeit umschaltbar

    // ------------------ Lebenszyklus-Hooks ------------------

    @Override
    public void Start() { /* nichts nötig */ }

    @Override
    public void Update() { /* nichts nötig */ }

    @Override
    public void PhysicsUpdate() { /* nichts nötig */ }

    @Override
    public void LateUpdate() { /* nichts nötig */ }

    @Override
    public void Render(Graphics2D g, int screenWidth, int screenHeight)
    {
        if (!visible || image == null) return;

        final int ts = Math.max(1, tileSize);

        // Zielgröße (in Pixel) ermitteln
        int targetW, targetH;
        if (drawWidth != null && drawHeight != null) {
            targetW = drawWidth * ts;
            targetH = drawHeight * ts;
        } else {
            int iw = image.getWidth(null);
            int ih = image.getHeight(null);
            if (iw <= 0 || ih <= 0) {
                targetW = ts;
                targetH = ts;
            } else {
                int tilesW = Math.max(1, (int)Math.ceil(iw / (double)ts));
                int tilesH = Math.max(1, (int)Math.ceil(ih / (double)ts));
                targetW = tilesW * ts;
                targetH = tilesH * ts;
            }
        }

        // Koordinaten in Pixel (Ursprung unten links!)
        final int px = x * ts;
        final int py = screenHeight - (y * ts) - targetH;

        // Alpha setzen, zeichnen, Alpha zurücksetzen
        Composite old = g.getComposite();
        if (opacity < 1.0f) {
            g.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER,
                    Math.max(0f, Math.min(opacity, 1f))
            ));
        }

        g.drawImage(image, px, py, targetW, targetH, null);

        g.setComposite(old);
    }


    // ------------------ Konfiguration / API ------------------

    /** Setzt ein bereits geladenes Bild. */
    public void setImage(Image img) {
        this.image = img;
    }

    /** Lädt ein Bild aus einer Datei (absoluter oder relativer Pfad). */
    public void setImageFromFile(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        this.image = img;
    }

    /** Lädt ein Bild aus dem Klassenpfad (z. B. resources/textures/player.png). */
    public void setImageFromResource(String resourcePath) throws IOException {
        var url = getClass().getClassLoader().getResource(resourcePath);
        if (url == null) throw new IOException("Resource not found: " + resourcePath);
        BufferedImage img = ImageIO.read(url);
        this.image = img;
    }

    /** Setzt die linke obere Zeichenposition in Tiles. */
    public void setPosition(int tileX, int tileY) {
        this.x = tileX;
        this.y = tileY;
    }

    /** Setzt die Zielgröße in Tiles (skaliert das Bild auf ein Rastermaß). */
    public void setSize(int tilesWide, int tilesHigh) {
        this.drawWidth = tilesWide;
        this.drawHeight = tilesHigh;
    }

    /** Setzt die Sichtbarkeit. */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /** Setzt die Deckkraft (0.0 = unsichtbar, 1.0 = voll sichtbar). */
    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    /** Setzt die Größe einer Kachel in Pixeln. */
    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getTileSize() { return tileSize; }

    // Optionale Getter
    public Image getImage() { return image; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Integer getDrawWidth() { return drawWidth; }
    public Integer getDrawHeight() { return drawHeight; }
    public boolean isVisible() { return visible; }
    public float getOpacity() { return opacity; }
}
