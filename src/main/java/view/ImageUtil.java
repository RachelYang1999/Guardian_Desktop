package view;

/*
Reference
Liu Mingyao (2019), Javafx set image transparency (saturation, brightness is the same) [Source code]. https://programmersought.com/article/58761206682/
 */

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.concurrent.FutureTask;

//public class ImageUtil {
//
//    public static Image backGroundImg;
//
//    /**
//     * @Description Image transparency setting in javafx. First get the information of each pixel of the original image, and then assign it to the new image object. When creating a new image object, the transparency can be set <br>
//     * After testing the big picture, this is very slow, so here are three threads to render at the same time.
//     * @param image Need to change the transparency of the image object
//     * @param opacity transparency, between 0-1
//     * @return new writable image object
//     * @author LIu Mingyao
//     */
//    public WritableImage imgOpacity(Image image, double opacity) {
//        // Get PixelReader
//        PixelReader pixelReader = image.getPixelReader();
//
//        // Create a WritableImage
//        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
//        PixelWriter pixelWriter = wImage.getPixelWriter();
//        // Single-thread change transparency, get the color of each coordinate pixel, reset the value, give transparency, and finally set the new color to the new image object (wImage's pixelWriter)
//        for (int readY = 0; readY < image.getHeight(); readY++) {
//            for (int readX = 0; readX < image.getWidth(); readX++) {
//                Color color = pixelReader.getColor(readX, readY);
//                System.out.println("\nPixel color at coordinates (" + readX + "," +
//                        readY + ") "+ color.toString());
//                System.out.println("R = " + color.getRed());
//                System.out.println("G = " + color.getGreen());
//                System.out.println("B = " + color.getBlue());
//                System.out.println("Opacity = " + color.getOpacity());
//                System.out.println("Saturation = " + color.getSaturation());
//
//                // Now write a brighter color to PixelWriter
//                // color = color.brighter();
//
//                // darker
//                // color.darker();
//
//                // The last parameter is a transparent setting. Need to set the transparency can not change the original, can only re-create the object assignment,
//                Color c1 = new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
//
//                pixelWriter.setColor(readX, readY, c1.brighter());
//            }
//        }
//        return wImage;
//    }
//}

/**
 * @Description Image Processing
 * @author LIu Mingyao
 * @date April 7, 2019 1:10:30 PM
 */
public class ImageUtil {

    public static Image backGroundImg;

    /**
     * @Description Image transparency setting in javafx.
     *
     * @param image Need to change the transparency of the image object
     * @param opacity transparency, between 0-1
     * @return new writable image object
     * @author LIu Mingyao
     */
    public WritableImage imgOpacity(Image image, double opacity) throws Exception {

        if (opacity < 0 || opacity > 1) throw new Exception ("Transparency needs to be between 0-1, please reset the transparency!");

        // Get PixelReader
        PixelReader pixelReader = image.getPixelReader();

        // Create a WritableImage
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();
        double imgHeight = image.getHeight();
        double tempHeight = imgHeight % 3;
        // Changed the original single thread change transparency (the code below the comment) to three threads, which solved the problem of slow transparency of large image changes.
        FutureTask<Boolean> futureTask1 = new FutureTask<Boolean>(() -> {
            for (int readY = 0; readY < tempHeight; readY++) {
                for (int readX = 0; readX < image.getWidth(); readX++) {
                    Color color = pixelReader.getColor(readX, readY);
                    // The last parameter is a transparent setting. Need to set the transparency can not change the original, can only re-create the object assignment,
                    Color c1 = new Color(color.getRed(), color.getGreen(), color.getBlue(),
                            opacity);

                    pixelWriter.setColor(readX, readY, c1.brighter());
                }
            }
            return true;
        });
        new Thread(futureTask1, "first transparency rendering thread").start();

        FutureTask<Boolean> futureTask2 = new FutureTask<Boolean>(() -> {
            for (int readY = (int) tempHeight; readY < 2 * tempHeight; readY++) {
                for (int readX = 0; readX < image.getWidth(); readX++) {
                    Color color = pixelReader.getColor(readX, readY);
                    Color c1 = new Color(color.getRed(), color.getGreen(), color.getBlue(),
                            opacity);
                    pixelWriter.setColor(readX, readY, c1.brighter());
                }
            }

            return true;
        });
        new Thread(futureTask2, "Second Transparency Rendering Thread").start();
        FutureTask<Boolean> futureTask3 = new FutureTask<Boolean>(() -> {
            for (int readY = (int) (2 * tempHeight); readY < imgHeight; readY++) {
                for (int readX = 0; readX < image.getWidth(); readX++) {
                    Color color = pixelReader.getColor(readX, readY);
                    Color c1 = new Color(color.getRed(), color.getGreen(), color.getBlue(),
                            opacity);
                    pixelWriter.setColor(readX, readY, c1.brighter());
                }
            }

            return true;
        });

        new Thread(futureTask3, "third transparency rendering thread").start();
        // // Single thread changes the transparency, gets the color of each coordinate pixel, resets the value, gives the transparency, and finally sets the new color to the new image object (wImage's pixelWriter)
//		 for (int readY = 0; readY < image.getHeight(); readY++) {
//		 for (int readX = 0; readX < image.getWidth(); readX++) {
//		 Color color = pixelReader.getColor(readX, readY);
//		 System.out.println("\nPixel color at coordinates (" + readX + "," + readY + ") "
//		 + color.toString());
//		 System.out.println("R = " + color.getRed());
//		 System.out.println("G = " + color.getGreen());
//		 System.out.println("B = " + color.getBlue());
//		 System.out.println("Opacity = " + color.getOpacity());
//		 System.out.println("Saturation = " + color.getSaturation());
//
        // // Now write a brighter color to PixelWriter
//		 // color = color.brighter();
//
        // // darker
//		 // color.darker();
//
        // // The last parameter is a transparent setting. Need to set the transparency can not change the original, can only re-create the object assignment,
//		 Color c1 = new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
//
//		 pixelWriter.setColor(readX, readY, c1.brighter());
//		 }
//		 }

// / / This part of the code can be chosen independently. Use it to ensure that all the images are completely refreshed and then displayed, otherwise the image is rendered first, then the middle and lower parts.
//		try {
        // // Wait for all three threads to finish executing
//			if (futureTask1.get() && futureTask2.get() && futureTask3.get()) {
//				backGroundImg = wImage;
//			}
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        return wImage;
    }
}