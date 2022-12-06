package org.example;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintActionListener implements Runnable {

    private final BufferedImage       image;

    public PrintActionListener(final BufferedImage image) {
        this.image = image;
    }

    @Override
    public void run() {
        final PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(new ImagePrintable(printJob, image));

        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (PrinterException prt) {
                prt.printStackTrace();
            }
        }
    }

    private static class ImagePrintable implements Printable {

        private final double          x;
        private final double y;
        private final double width;

        private final int             orientation;

        private final BufferedImage   image;

        public ImagePrintable(final PrinterJob printJob, final BufferedImage image) {
            final PageFormat pageFormat = printJob.defaultPage();
            x = pageFormat.getImageableX();
            y = pageFormat.getImageableY();
            width = pageFormat.getImageableWidth();
            orientation = pageFormat.getOrientation();
            this.image = image;
        }

        @Override
        public int print(final Graphics g, final PageFormat pageFormat, final int pageIndex) {
            if (0 == pageIndex) {
                final int pWidth;
                final int pHeight;
                if (PageFormat.PORTRAIT == orientation) {
                    pWidth = (int) Math.min(width, image.getWidth());
                    pHeight = pWidth * image.getHeight() / image.getWidth();
                } else {
                    pHeight = (int) Math.min(width, image.getHeight());
                    pWidth = pHeight * image.getWidth() / image.getHeight();
                }
                g.drawImage(image, (int) x, (int) y, pWidth, pHeight, null);
                return PAGE_EXISTS;
            } else {
                return NO_SUCH_PAGE;
            }
        }

    }

}