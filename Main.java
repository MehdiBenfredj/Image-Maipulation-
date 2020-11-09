import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.IOException;


public class Main 
{

    public static void main(String[] args) {
    	MatrixGrayImage image = 
    			MatrixGrayImage.createImageFromPGMFile("luminy.pgm");


    	Pixelate newPixelate = new Pixelate();
    	newPixelate.applyTo(image);
    	DecreaseGrayLevels dec = new DecreaseGrayLevels();
    	


    	display(image);
	
    }

    
    /** Methode pour visulaiser l'image. NE PAS MODIFIER !! **/   
    public static void display(MatrixGrayImage image) {
    	JFrame frame = new JFrame();
        int width = image.getWidth(); 
        int height = image.getHeight();
        frame.setSize(width, height);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new ImagePanel(width, height, image));
        frame.setVisible(true);

    }
    
    private static class ImagePanel extends JPanel {
        private BufferedImage image;
        public ImagePanel(int width, int height, MatrixGrayImage myimage) {
            this.image = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY); 
            
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    image.setRGB(i, j, myimage.getPixelByteColor(i,j));
                }
            }
            repaint();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
