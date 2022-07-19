package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlurFilter implements FilterImage{
    FilteringImage filteringImage=new FilteringImage();
    @Override
    public BufferedImage filter(BufferedImage bufferedImage) {

        //filteringImage.filter(bufferedImage);
        final int height = bufferedImage.getHeight();
        final int width = bufferedImage.getWidth();
        final int rgb = bufferedImage.getRGB(0, 0);
        int blurRadius = 2;
        final BufferedImage blurredImage = new BufferedImage(width, height, bufferedImage.getType());
        for (int x = blurRadius; x < width - blurRadius; x++) {
            for (int y = blurRadius; y < height - blurRadius; y++) {
                blurredImage.setRGB(x, y, getBlurredPixel(bufferedImage, x, y, blurRadius));
            }
        }
        return blurredImage;
    }
    private static int getBlurredPixel(BufferedImage image, int x, int y, int radius) {
        int side = radius * 2 + 1;
        int pixelCount = side * side - 1;
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        float brightnessSum = 0.0f;

        for (int row = -radius; row <= radius; row++) {
            for (int col = -radius; col <= radius; col++) {
                if (row == 0 && col == 0) {
                    continue;
                }
                final int rgb = image.getRGB(x + col, y + row);
                final Color color = new Color(rgb);

                final float[] hsbColor =
                        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
                final float brightness = hsbColor[2];
                brightnessSum += brightness;

            }
        }

        final float brightnessAvg = brightnessSum / pixelCount;
        final int currentPixel = image.getRGB(x, y);
        final Color currentPxColor = new Color(currentPixel);
        final float[] currentPxHsb = Color.RGBtoHSB(currentPxColor.getRed(), currentPxColor.getGreen(),
                currentPxColor.getBlue(), null);
        currentPxHsb[2] = brightnessAvg;
        final int calcuatedPixel = Color.HSBtoRGB(currentPxHsb[0], currentPxHsb[1], currentPxHsb[2]);
        return calcuatedPixel;
    }
}