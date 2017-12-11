/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pencilprinter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
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
        
    //Vars
        //boolArray[][] holds the pixel-info of the processed image as array of
        //rows of pixels
        boolean[][] boolArray;
        //boolArrayRow[] holds each row of boolArray[][]
        boolean boolArrayRow[];        
        //tmpByte[] holds pixel-information of a row of pixels in bytes
        byte tmpByte[];

    //Constructors:
        
//TEMP    
        //Creates a tmp boolean[][] for testing
        TmpBool tmpBool = new TmpBool();
        boolArray = tmpBool.tmpBoolArray();
//TEMP      
        
        //Constructor for the method to convert the incoming boolArray,
        //to byte array.
        ByteConvert byteConvert = new ByteConvert();

        //Constructor for the method to connect the TCP socket
        //Setting the hostname/IP and port to use.
        TcpClient tcpClient = new TcpClient("localhost", 4444);
        
        //image cons
        ReadImage readImage = new ReadImage();
        JFileChooser fileChooser = new JFileChooser();
        
        //When reading the image
        
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.showOpenDialog(fileChooser);
        
        File inputfile = fileChooser.getSelectedFile();
        String path = inputfile.getAbsolutePath().toLowerCase();
        String filetype = path.substring(path.lastIndexOf(".")+1);
        
        if ("svg".equals(filetype))
        {
            String opp = readImage.svg2png(path);
            readImage.readBmpImage(opp);
        }else{
            readImage.readBmpImage(path);
        }
        /*
        //Opening the connection
        tcpClient.connect();
        
        //While loop "stalls" until the connection is open and
        //tcpClient.isconnected() returns true
        while (!tcpClient.isConnected()){
        
        //Doin nothing
        
        }
        
        //This for loop traverses each row in boolArray[][], converts it to
        //byte[] via byteConvert and send it with tcpClient.write()
        for (int i = 0; i < boolArray.length; i++) {
        boolArrayRow = boolArray[i];
        //Converting the boolArray
        tmpByte = byteConvert.boolToByte(boolArrayRow);
        
        try {
        //Now writing the returning byteArray to the output stream of the
        //tcp socket
        tcpClient.write(tmpByte);
        //System.out.println("return fra boolToByte"+Arrays.toString(tmpByte));
        } catch (IOException ex) {
        System.out.println("Could not send image to PLC. Se log for details.");
        Logger.getLogger(PencilPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        //When done, disconnect
        tcpClient.disconnect();*/
        
    }
    
}
