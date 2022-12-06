package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;


public class Main {

    public static void main(final String[] args) throws IOException {

        final Scanner scanner = new Scanner(System.in);



        final Class<Main> mainClass = Main.class;
        final ClassLoader classLoader = mainClass.getClassLoader();
        final URL imageUrl = classLoader.getResource("download.jpg");

        assert null != imageUrl;
        final BufferedImage bufferedImage = ImageIO.read(imageUrl);
        final String outputPath = Objects.requireNonNull(classLoader.getResource("")).getPath();
        final File outputFIle = new File(outputPath + "/blurred.jpg");

        System.out.print("Insert color filter: ");
        final String inputColorFilter = scanner.nextLine();

        final FilteringImage colorFilterImage = new FilteringImage();
        colorFilterImage.setTypeOfFilter(inputColorFilter);

        final BlurFilter blurFilter = new BlurFilter();

        blurFilter.filter(bufferedImage);
        colorFilterImage.filter(bufferedImage);

        final FilteredImageSave filteredImageSave = new FilteredImageSave(outputFIle);
        filteredImageSave.write(colorFilterImage.filter(bufferedImage));
        final BufferedImage printImage = ImageIO.read(outputFIle);
        final PrintActionListener pal = new PrintActionListener(printImage);
        pal.run();

    }
}