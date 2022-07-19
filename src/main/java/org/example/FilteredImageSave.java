package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

public class FilteredImageSave implements ImageWriter {
    private File file;
    protected FilteredImageSave(File file) {

        this.file = file;
    }


    @Override
    public void write(BufferedImage bufferedImage) {
        try {
            final FileOutputStream blurredImageOutputStream = new FileOutputStream(file);
            ImageIO.write(bufferedImage, "jpg", blurredImageOutputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}