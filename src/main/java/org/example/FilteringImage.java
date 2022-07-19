package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FilteringImage implements FilterImage {
    String typeOfFilter;

    public void setTypeOfFilter(String typeOfFilter) {
        this.typeOfFilter = typeOfFilter;
    }


    @Override
    public BufferedImage filter(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = bufferedImage.getRGB(x, y);
                Color color = new Color(bufferedImage.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int onlyRed = new Color(red, 0, 0).getRGB();
                int onlyGreen = new Color(0, green, 0).getRGB();
                int onlyBlue = new Color(0, 0, blue).getRGB();

                if(typeOfFilter.equals("blue")){
                    bufferedImage.setRGB(x, y, onlyBlue);
                }else if(typeOfFilter.equals("green")){
                    bufferedImage.setRGB(x, y, onlyGreen);
                }else if(typeOfFilter.equals("red")){
                    bufferedImage.setRGB(x,y,onlyRed);
                }

            }
        }

        return bufferedImage;
    }

}