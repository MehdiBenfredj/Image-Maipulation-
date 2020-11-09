import java.awt.Color;
import java.util.Iterator;

/**
 * Classe ByteGrayColor: Pour manipuler le coleur d'un pixel en niveau de gris (grey level).
 */

public class ByteGrayColor implements GrayColor {

    private static final int MINIMUM_GRAY_LEVEL = 0;
    private static final int MAXIMUM_GRAY_LEVEL = 255;
    private static final int OPACITY = 1;

    public static final ByteGrayColor WHITE = new ByteGrayColor(MAXIMUM_GRAY_LEVEL);
    public static final ByteGrayColor BLACK = new ByteGrayColor(MINIMUM_GRAY_LEVEL);

    private int grayLevel;


    public ByteGrayColor(){
       this.grayLevel = MINIMUM_GRAY_LEVEL;
    }

    public ByteGrayColor(int grayLevel) {
        this.grayLevel = grayLevel;
    }

    public ByteGrayColor(double luminosity) {
        this.grayLevel = (int) (luminosity*255);
    }

    @Override
    public int getGrayLevel(){
    	return grayLevel;
    }

    @Override
    public double getLuminosity() {
        return (double) grayLevel/255;
    }

    @Override
    public Color getColor(){
        float component = (float)getLuminosity();
        return new Color(component, component, component);
    }

    @Override
    public GrayColor invert() {
        ByteGrayColor newGray = new ByteGrayColor();
        newGray.grayLevel = MAXIMUM_GRAY_LEVEL  - grayLevel;
        return newGray;
    }

    public GrayColor decreaseGrayLevel(int nbGrayLevels){
       ByteGrayColor newGray = new ByteGrayColor();
       int nbIntervalles = nbGrayLevels-1;
       float intervalle = MAXIMUM_GRAY_LEVEL/nbIntervalles;
       float[] limitesInf = new float[nbIntervalles];
       limitesInf[0] = 0;
       for(int i=1;i<nbIntervalles;i++){
           limitesInf[i]= limitesInf[i-1] + intervalle;
       }
       int i=0;
       while (i<(nbIntervalles) && grayLevel>limitesInf[i] ){
           newGray.grayLevel = (int) limitesInf[i];
           i++;
       }
        return newGray;
    }

    @Override
    public void pixelate(GrayImage image, int i, int j) {
        ByteGrayColor newGray = new ByteGrayColor();
        int totalGRayLevel=0 ;
        int averageGrayLevel ;
        final int  NUMBER_PIXELS = 10*10;
        //Getting Loop : Calculer pour chaque groupe le total de grayLevel
        for(int row=i; row<i+10; row++ ) {
            for (int column = j; column < j + 10; column++) {
                totalGRayLevel += image.getPixelGrayColor(row, column).getGrayLevel();
            }
        }
        averageGrayLevel = totalGRayLevel/NUMBER_PIXELS;
        newGray.grayLevel = averageGrayLevel;
        // Setting loop :
        for(int row=i; row<i+10; row++ ) {
            for (int column = j; column < j + 10; column++) {
                image.setPixel(newGray,row,column);
            }
        }

    }


    @Override
    public int compareTo(GrayColor o) {
        return getGrayLevel() - o.getGrayLevel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        ByteGrayColor color = (ByteGrayColor) o;
        return this.compareTo(color) == 0;
    }

}
