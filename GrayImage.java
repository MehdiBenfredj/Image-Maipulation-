/**
 * Interface pour representer un image en version noir et blanc.
 */

public interface GrayImage extends Image {
    void setPixel(GrayColor gray, int x, int y);
    GrayColor getPixelGrayColor(int x, int y);
}
