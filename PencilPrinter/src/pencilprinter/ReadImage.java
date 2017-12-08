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

public class ReadImage {

    public ReadImage() {
    }

    public void readBmpImage(String filepath) {
        File loadedImage = new File(filepath);
        BufferedImage bi;
        int threshold = 50;
        int alphaThreshold = 10;
        try {
            bi = ImageIO.read(loadedImage);
            int imageHeight = bi.getHeight();
            int imageWidth = bi.getWidth();
            boolean[][] toDraw = new boolean[imageHeight][imageWidth];
            for (int y = 0; y < imageHeight; y++) {
                for (int x = 0; x < imageWidth; x++) {
                    int pixelRGB = bi.getRGB(x, y);
                    int alpha = pixelRGB >> 24 & 255;
                    int red = pixelRGB >> 16 & 255;
                    int green = pixelRGB >> 8 & 255;
                    int blue = pixelRGB & 255;
                    toDraw[y][x] = !(red > threshold && blue > threshold && green > threshold) && (alpha > alphaThreshold);
                    /*System.out.println("x=" + x + " y=" + y + " rbg=" + pixelRGB + "    alpha:"+alpha+"     red:"+red+"     "+
                    "green:"+green+"    blue:"+blue);*/
                    //System.out.println("To draw or not to draw? " + toDraw[y][x]);
                }
            }
            //resetDrawing(toDraw,imageHeight,imageWidth);
            testDraw(toDraw, imageHeight, imageWidth);
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }

    }

    public void testDraw(boolean[][] pixelsToDraw, int imageHeight, int imageWidth) {
        BufferedImage outputBi;
        File outputImage = new File("C:\\Users\\Maciej\\Desktop\\test\\1234.jpg");
        try {
            outputBi = ImageIO.read(outputImage);

            for (int y1 = 0; y1 < imageHeight; y1++) {
                for (int x1 = 0; x1 < imageWidth; x1++) {
                    if (pixelsToDraw[y1][x1] == true) {
                        outputBi.setRGB(x1, y1, 0);
                    }
                }
            }
            ImageIO.write(outputBi, "png", outputImage);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

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
}