/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pencilprinter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.util.Arrays;
import javax.swing.JFileChooser;


/**
 *
 * @author emil
 */
public class PencilPrinter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    //Vars for file handling
        //filepath holds the path to the image as a String
        String filepath;
        //The input file is stored as a File
        File inputfile;
        //filetype stores the file type as a String
        String filetype;
        
    //Vars for image handling
        //resolution holds the desired resolution as a int
        int resolution = 256;
        //threshold sets the value as of when a pixel is thought of as "black" as a int
        int threshold = 50;
        //alphaThreshold sets the value as of when a "transparent" pixel is thought of as not transparent.
        int alphaThreshold = 10;
        //bufferImage holds the read image file as a BufferedImage
        BufferedImage bufferImage;
        //boolArray[][] holds the pixel-info of the processed image as array of
        //rows of pixels
        boolean[][] boolArray;
        //boolArrayRow[] holds each row of boolArray[][]
        boolean boolArrayRow[];        
        //tmpByte[] holds pixel-information of a row of pixels in bytes
        byte tmpByte[];
        
    //Vars for tcp handling
        //hostName stores the name/IP of the host to connect to as a String
        String host = "localhost";
        //hostPort stores the port number for the tcp host as a int
        int hostPort = 4444;

    //Constructors:
    
        /*
        //Creates a tmp boolean[][] for testing
        TmpBool tmpBool = new TmpBool();
        boolArray = tmpBool.tmpBoolArray();
        */  

        //Constructor for the method to connect the TCP socket
        //Setting the hostname/IP and port to use.
        TcpClient tcpClient = new TcpClient(host, hostPort);
        
        //Constructor for the method to convert the incoming boolArray,
        //to byte array.
        ByteConvert byteConvert = new ByteConvert();
        
        //Constructors for choosing file (img) to read
        JFileChooser fileChooser = new JFileChooser();
        
        //Constructor for the class that handles reading and scaling of the
        //image and converting it to a bool[][]
        ImageHandler imageHandler = new ImageHandler();
        
    //Choosing the file (img) to print. If the file is .svg, it is converted to
    //a png, and send to readImage. If not, the image is send to readImage.
    //Should'n we instead just use a buffered image?
        
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.showOpenDialog(fileChooser);
        
        inputfile = fileChooser.getSelectedFile();
        filepath = inputfile.getAbsolutePath().toLowerCase();
        filetype = filepath.substring(filepath.lastIndexOf(".")+1);
        
        if ("svg".equals(filetype)){
            String pngFilePath = imageHandler.svg2png(filepath);
            bufferImage = imageHandler.readImage(pngFilePath);
        } else {
            bufferImage = imageHandler.readImage(filepath);
        }
        
        //Now storing the buffered image in a boolean[][], values determined by
        //the vars resolution, threshold and alphaThreshold
        boolArray = imageHandler.img2BoolArray(imageHandler.resizeImage(resolution, bufferImage), threshold, alphaThreshold);
        
        /*
        //Opening the connection
        tcpClient.connect();
        
        //While loop "stalls" until the connection is open and
        //tcpClient.isconnected() returns true
        while (!tcpClient.isConnected()){
        
        //Doin nothing
        
        }*/
        
        //This for loop traverses each row in boolArray[][], converts it to
        //byte[] via byteConvert and send it with tcpClient.write()
        for (int i = 0; i < boolArray.length; i++) {
            boolArrayRow = boolArray[i];
        //Converting the boolArray
            tmpByte = byteConvert.boolToByte(boolArrayRow);
        /*
        try {
        //Now writing the returning byteArray to the output stream of the
        //tcp socket
        tcpClient.write(tmpByte);
        //System.out.println("return fra boolToByte"+Arrays.toString(tmpByte));
        } catch (IOException ex) {
        System.out.println("Could not send image to PLC. Se log for details.");
        Logger.getLogger(PencilPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        }
        
        //When done, disconnect
        //tcpClient.disconnect();
        
    }
    
}
