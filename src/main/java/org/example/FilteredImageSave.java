package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

public class FilteredImageSave implements ImageWriter {

    private final File file;

    protected FilteredImageSave(final File file) {

        this.file = file;
    }

    @Override
    public void write(final BufferedImage bufferedImage) {
        try {
            final FileOutputStream blurredImageOutputStream = new FileOutputStream(file);
            ImageIO.write(bufferedImage, "jpg", blurredImageOutputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}