public class DecreaseGrayLevels implements Transform {

    @Override
    public void applyTo(GrayImage image) {
        final int nbGrayLevels = 10;
        for(int x=0; x<image.getWidth(); x++)
            for(int y=0;y<image.getHeight();y++)
                image.setPixel(image.getPixelGrayColor(x,y).decreaseGrayLevel(nbGrayLevels),x,y);
    }
}
