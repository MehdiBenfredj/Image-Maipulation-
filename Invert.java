public class Invert implements Transform {

    @Override
    public void applyTo(GrayImage image) {
      for(int x=0; x<image.getWidth(); x++)
          for(int y=0;y<image.getHeight();y++)
              image.setPixel(image.getPixelGrayColor(x,y).invert(),x,y);
    }

}
