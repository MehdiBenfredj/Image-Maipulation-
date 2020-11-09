import java.awt.Color;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Classe pour representer un image comme un 2D tableau de pixels ou chaque pixel est un niveau de gris.
 */
public class MatrixGrayImage implements GrayImage {

    private final GrayColor[][] pixels ;
    private final int width;
    private final int height;


    @Override
    public void setPixel(GrayColor gray, int x, int y) {
        pixels[x][y] = gray;
    }

    @Override
    public GrayColor getPixelGrayColor(int x, int y) {
        return pixels[x][y];
    }

    @Override
    public Color getPixelColor(int x, int y) {
        return pixels[x][y].getColor();
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public MatrixGrayImage(int width, int height){
        pixels = new GrayColor[width][height];
        this.width = width;
        this.height = height;
        for (int x=0; x<width; x++){
            for (int y=0; y<height; y++){
                pixels[x][y] = new ByteGrayColor(255);
            }
        }
    }

    public int getPixelByteColor(int x, int y) {
        return (int)(getPixelGrayColor(x, y).getGrayLevel()*PGM_MAXIMUM_CODE);
    }

    public static MatrixGrayImage createImageFromPGMFile(String fileName) {
        // NE PAS MODIFIER !
        InputStream file = ClassLoader.getSystemResourceAsStream(fileName);
        Scanner scan = new Scanner(file);
        scan.nextLine();
        scan.nextLine();

        int width = scan.nextInt();
        int height = scan.nextInt();

        MatrixGrayImage result = new MatrixGrayImage(width, height);

        scan.nextInt();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {
                GrayColor color = new ByteGrayColor(scan.nextInt());
                result.setPixel(color, x, y);
            }
        }

        return result;
    }

    public void writeIntoPGMFormat(String fileName){
        // NE PAS MODIFIER !
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("P2");
            printWriter.printf("%d %d\n",this.width, this.height);

            printWriter.println(pgmCodeOfGrayColor(pixels[0][0]));

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++) {
                    printWriter.println(pgmCodeOfGrayColor(getPixelGrayColor(x,y)));
                }
            }
            printWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static final int PGM_MAXIMUM_CODE = 255;

    private int pgmCodeOfGrayColor(GrayColor pixelGrayColor) {
        return (int) (pixelGrayColor.getLuminosity() * (double) PGM_MAXIMUM_CODE);
    }
}
