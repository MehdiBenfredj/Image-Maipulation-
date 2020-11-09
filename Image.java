import java.awt.Color;

/**
 * Interface pour representer un image.
 */

public interface Image {
    Color getPixelColor(int x, int y);
    int getWidth();
    int getHeight();
}
