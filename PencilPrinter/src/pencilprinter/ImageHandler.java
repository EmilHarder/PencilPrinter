package pencilprinter;

import java.awt.Graphics2D;
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

                /*
                For later
                BufferedImage img = ImageIO.read((ImageInputStream) output_png_image);
                output_png_image.setOutputStream(png_ostream);
                 */
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
    public BufferedImage readImage(String filepath) {
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

    /**
     * Method for resizing image. This could (should?) be a part of readImage().
     *
     * @param desRes The desired resolution for the image to print
     * @param originalImage The buffered image from readImage()
     * @return Returns the resized BufferedImage
     */
    public BufferedImage resizeImage(int desRes, BufferedImage originalImage) {

        //System.out.println(originalImage);
        double imageHeight = originalImage.getHeight();
        double imageWidth = originalImage.getWidth();
        double resizedImageHeight;
        double resizedImageWidth;
        double ratio;
        BufferedImage resized;
        if (imageHeight > imageWidth) {
            ratio = (double) desRes / imageHeight;
            resizedImageHeight = ratio * imageHeight;
            resizedImageWidth = ratio * imageWidth;
        } else {
            ratio = (double) desRes / imageWidth;
            resizedImageWidth = ratio * imageWidth;
            resizedImageHeight = ratio * imageHeight;
        }

        resized = new BufferedImage((int) resizedImageWidth, (int) resizedImageHeight, 2);

        Graphics2D g = resized.createGraphics();
        g.drawImage(originalImage, 0, 0, (int) resizedImageWidth, (int) resizedImageHeight, 0, 0, (int) imageWidth, (int) imageHeight, null);
        g.dispose();

        return resized;
    }

    /**
     * Method for storing a buffered image as a 2D boolean array.
     * 
     *
     * @param bufferedImg The buffered image to "convert"
     * @return Returns a boolean[][] with info for each pixel.
     */
    public boolean[][] img2BoolArray(int resolution, BufferedImage bufferedImg, int threshold, int alphaThreshold) {

        int imageHeight = bufferedImg.getHeight();
        int imageWidth = bufferedImg.getWidth();
        boolean[][] boolArray = new boolean[resolution][resolution];
        for (int y = 0; y < resolution; y++) {
            if (y < imageHeight) {
                if (y % 2 == 0) {
                    for (int x = 0; x < resolution; x++) { //Normal reading
                        if (x < imageWidth) {
                            int pixelRGB = bufferedImg.getRGB(x, y);
                            int alpha = pixelRGB >> 24 & 255;
                            int red = pixelRGB >> 16 & 255;
                            int green = pixelRGB >> 8 & 255;
                            int blue = pixelRGB & 255;
                            boolArray[y][x] = !(red > threshold && blue > threshold && green > threshold) && (alpha > alphaThreshold);
                            //System.out.println("op: "+y+", "+x+" - "+boolArray[y][x]);
                        } else {
                            //Doin nothing - leaving the x-index at false
                        }
                    }
                } else {
                    int xLeft = (resolution-1);
                    for (int x = 0; x < resolution; x++) { //Reverse reading
                        if (xLeft < imageWidth) {
                            int pixelRGB = bufferedImg.getRGB(xLeft, y);
                            int alpha = pixelRGB >> 24 & 255;
                            int red = pixelRGB >> 16 & 255;
                            int green = pixelRGB >> 8 & 255;
                            int blue = pixelRGB & 255;
                            boolArray[y][x] = !(red > threshold && blue > threshold && green > threshold) && (alpha > alphaThreshold);
                            //System.out.println("ned: "+y+", "+x+" - "xLeft+boolArray[y][x]);
                        } else {
                            //Doin nothing - leaving the x-index at false
                        }
                        //System.out.println(xLeft);
                        xLeft--;

                    }
                }
            } else {
                //Doin nothing - leaving the y-index at false
            }

            /*
            for (int i = 0; i <= 255; i++) {
            System.out.println("y: "+y+" x: "+i+": "+boolArray[y][i]);
            
            }*/
        }
        return boolArray;

    }
}
