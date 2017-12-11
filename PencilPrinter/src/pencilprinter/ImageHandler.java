package pencilprinter;

import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageHandler {

    /**
     * Method for converting svg to png
     * 
     * @param filepath Path to the image file to convert
     * @return Returns a String with the file path to the new png
     */
    public String svg2png(String filepath) {
        //Step -1: We read the input SVG document into Transcoder Input
        //We use Java NIO for this purpose
        Path outputPath = Paths.get("convertedImage.png");
        try {
            Files.delete(outputPath);
        } catch (IOException x) {
            System.err.format("%s: no such" + " file or directory%n", outputPath);
        }
        try {
            String svg_URI_input = Paths.get(filepath).toUri().toURL().toString();
            //String svg_URI_input = filepath;
            TranscoderInput input_svg_image = new TranscoderInput(svg_URI_input);
            
            try ( //Step-2: Define OutputStream to PNG Image and attach to TranscoderOutput
                    OutputStream png_ostream = new FileOutputStream("convertedImage.png")) {
                TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);
                // Step-3: Create PNGTranscoder and define hints if required
                PNGTranscoder my_converter = new PNGTranscoder();
                // Step-4: Convert and Write output
                my_converter.transcode(input_svg_image, output_png_image);
                // Step 5- close / flush Output Stream
                png_ostream.flush();
            }
        } catch (IOException | TranscoderException e) {
            System.out.println(e.toString());
        }
        return "convertedImage.png";
}
    
    /**
     * Method for reading a image file to a buffered image
     * 
     * @param filepath Path to the image file to convert
     * @return Returns a buffered image
     */
    public BufferedImage readImage(String filepath){
        File loadedImage = new File(filepath);
        BufferedImage bufferedImg = null;
        try {
            bufferedImg = ImageIO.read(loadedImage);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(ImageHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bufferedImg;
    }
    
    public void resizeImage(int desRes){

        int desiredResolution = desRes;

        File pngOriginal = new File("C:\\Users\\Maciej\\documents\\NetbeansProjects\\Readingimages\\aminosyrer-ConvertImage.tiff");
        File pngResized = new File("C:\\Users\\Maciej\\documents\\NetBeansProjects\\ReadingImages\\resized.png");
        String originalFilePath = pngOriginal.getAbsolutePath().toLowerCase();
        System.out.println(originalFilePath);
        String originalFormat = originalFilePath.substring(originalFilePath.lastIndexOf(".") + 1);
        System.out.println(originalFormat);
        resizeImage(pngOriginal, pngResized, originalFormat);
        
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
            if (imageHeight > imageWidth) {
                ratio = desiredResolution / (double) imageHeight;
                resizedImageHeight = ratio * imageHeight;
                resizedImageWidth = ratio * imageWidth;
            } else {
                ratio = desiredResolution / (double) imageWidth;
                resizedImageWidth = ratio * imageWidth;
                resizedImageHeight = ratio * imageHeight;
            }
            System.out.println("\n" + ratio);
            System.out.println("\n" + resizedImageHeight + " " + resizedImageWidth);
            System.out.println(original.getType());
            BufferedImage resized;
            if (original.getType() != 0) {
                resized = new BufferedImage((int) resizedImageWidth, (int) resizedImageHeight, original.getType());
            } else {
                resized = new BufferedImage((int) resizedImageWidth, (int) resizedImageHeight, 2);
            }
            Graphics2D g2 = resized.createGraphics();
            g2.drawImage(original, 0, 0, (int) resizedImageWidth, (int) resizedImageHeight, null);
            g2.dispose();
            ImageIO.write(resized, format, resizedImage);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);

        }
    }

    /**
     * Method for storing a buffered image as a 2D boolean array.
     * @param bufferedImg The buffered image to "convert"
     * @return Returns a boolean[][] with info for each pixel.
     */
    public boolean[][] img2BoolArray(BufferedImage bufferedImg) {
        
        int threshold = 50;
        int alphaThreshold = 10;
            
            int imageHeight = bufferedImg.getHeight();
            int imageWidth = bufferedImg.getWidth();
            boolean[][] booArray = new boolean[imageHeight][imageWidth];
            for (int y = 0; y < imageHeight; y++) {
                for (int x = 0; x < imageWidth; x++) {
                    int pixelRGB = bufferedImg.getRGB(x, y);
                    int alpha = pixelRGB >> 24 & 255;
                    int red = pixelRGB >> 16 & 255;
                    int green = pixelRGB >> 8 & 255;
                    int blue = pixelRGB & 255;
                    booArray[y][x] = !(red > threshold && blue > threshold && green > threshold) && (alpha > alphaThreshold);
                }
            }
        return booArray;

    }
}