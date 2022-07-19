package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);


        try {
            final Class<Main> mainClass = Main.class;
            final ClassLoader classLoader = mainClass.getClassLoader();
            final URL imageUrl = classLoader.getResource(args[0]);

            final BufferedImage bufferedImage = ImageIO.read(imageUrl);
            final String outputPath = classLoader.getResource("").getPath();
            final File outputFIle = new File(outputPath + "/blurred.jpg");

            System.out.print("Insert color filter: ");
            String inputColorFilter=scanner.nextLine();

            final FilteringImage colorFilterImage=new FilteringImage();
            colorFilterImage.setTypeOfFilter(inputColorFilter);

            final BlurFilter blurFilter =new BlurFilter();

            blurFilter.filter(bufferedImage);
            colorFilterImage.filter(bufferedImage);

            final FilteredImageSave filteredImageSave = new FilteredImageSave(outputFIle);
            filteredImageSave.write(colorFilterImage.filter(bufferedImage));
            BufferedImage printImage=ImageIO.read(outputFIle);
            final PrintActionListener pal=new PrintActionListener(printImage);
            pal.run();

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}