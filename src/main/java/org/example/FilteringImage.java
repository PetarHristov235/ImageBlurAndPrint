package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FilteringImage implements FilterImage {

    String typeOfFilter;

    public void setTypeOfFilter(final String typeOfFilter) {
        this.typeOfFilter = typeOfFilter;
    }

    @Override
    public BufferedImage filter(final BufferedImage bufferedImage) {
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                final Color color = new Color(bufferedImage.getRGB(x, y));
                final int red = color.getRed();
                final int green = color.getGreen();
                final int blue = color.getBlue();
                final int onlyRed = new Color(red, 0, 0).getRGB();
                final int onlyGreen = new Color(0, green, 0).getRGB();
                final int onlyBlue = new Color(0, 0, blue).getRGB();

                switch (typeOfFilter) {
                    case "blue":
                        bufferedImage.setRGB(x, y, onlyBlue);
                        break;
                    case "green":
                        bufferedImage.setRGB(x, y, onlyGreen);
                        break;
                    case "red":
                        bufferedImage.setRGB(x, y, onlyRed);
                        break;
                }

            }
        }

        return bufferedImage;
    }

}