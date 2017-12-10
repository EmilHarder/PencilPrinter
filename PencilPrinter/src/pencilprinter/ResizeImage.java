package pencilprinter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;


public class ResizeImage {
    
    private static int desiredResolution = 250;
    public static void main(String[]args) {
        //Use relative path for these:
        File pngOriginal = new File("C:\\Users\\Maciej\\documents\\NetbeansProjects\\Readingimages\\chessboard.png");
        File pngResized = new File("C:\\Users\\Maciej\\documents\\NetBeansProjects\\ReadingImages\\resized.png");
        resizeImage(pngOriginal,pngResized,"png");
        
    }
    
    private static void resizeImage(File originalImage, File resizedImage, String format) {
        try {
            BufferedImage original = ImageIO.read(originalImage);
            double imageHeight = original.getHeight();
            double imageWidth = original.getWidth();
            double resizedImageHeight;
            double resizedImageWidth;
            double ratio;
            System.out.println(imageHeight + " " + imageWidth);
            if (imageHeight > imageWidth)
            {
                ratio = desiredResolution / (double)imageHeight;
                resizedImageHeight =  ratio * imageHeight;
                resizedImageWidth = ratio * imageWidth;
            }
            else
            {
                ratio = desiredResolution / (double)imageWidth;
                resizedImageWidth = ratio * imageWidth;
                resizedImageHeight = ratio * imageHeight;
            }
            System.out.println("\n"+ratio);
            System.out.println("\n"+resizedImageHeight+" "+resizedImageWidth);
            System.out.println(original.getType());
            //Maybe to simply return a BufferedImage instead of writing a file?
            BufferedImage resized = new BufferedImage ((int)resizedImageWidth, (int)resizedImageHeight, original.getType());
            Graphics2D g2 = resized.createGraphics();
            g2.drawImage(original, 0, 0, (int)resizedImageWidth, (int)resizedImageHeight, null);
            g2.dispose();
            ImageIO.write(resized, format, resizedImage);
        }
        
        catch(IOException ex) {
            ex.printStackTrace(System.out);
       
        }
    }
    
}