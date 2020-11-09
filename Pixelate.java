public class Pixelate  implements Transform {
    @Override
    public void applyTo(GrayImage image) {
        // Iterer sur chaque groupe de 10*10 pixels
        //On devra modifier les pixels pour chaque grouoe ( one by one ), dans la classe ByteGrayColor
        ByteGrayColor bgc = new ByteGrayColor();
        for(int row=0; row<image.getWidth()-3; row=row+10){
            for (int column=0; column<image.getHeight()-3; column=column+10){
                bgc.pixelate(image,row,column);
            }
        }
    }
}


