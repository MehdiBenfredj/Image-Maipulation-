import java.awt.Color;

/**
 * Interface correspondant à une couleur de gris.
 */

public interface GrayColor extends Comparable<GrayColor> {
    int getGrayLevel();
    double getLuminosity();
    Color getColor();
    GrayColor invert();
    GrayColor decreaseGrayLevel(int nbGrayLevels);
    void pixelate(GrayImage image, int row, int column);
    
   // Add methode Invert()
}
