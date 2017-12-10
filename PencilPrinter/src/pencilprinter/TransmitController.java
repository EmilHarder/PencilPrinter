package pencilprinter;

import java.io.IOException;

/**
 *
 * @author emil
 */
public class TransmitController {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        byte tmpByte[];
        
        boolean boolArray[][];
        boolean boolArrayRow[];
        
        //CHANGE TO "RECIEVED" ARRAY WHEN MERGIN WITH IMAGEREAD
        //Use the toDraw var
        //START for midlertidigt array
        int arrayLenght = 256;
        boolArray = new boolean[arrayLenght][arrayLenght];
        
        for (int i = 0; i < arrayLenght; i++) {
        
        for (int j = 0; j < arrayLenght; j++) {
        
        boolArray[i][j] = (boolean) ((i % 2 == 0) ^ (j % 3 == 0));
        }
        //System.out.println(Arrays.toString(boolArray[i]));
        }
        //SLUT for midlertidigt array
        
        
        //Constructor for the method to convert the incoming boolArray,
        //to byte array.
        ByteConvert byteConvert = new ByteConvert();

        //Constructor for the method to connect the TCP socket
        //Setting the hostname/IP and port to use.
        TcpClient test = new TcpClient("localhost", 4444);

        //Opening the connection
        test.connect();
        
        //For loop til at traversere hver række i boolArray[][]
        for (int i = 0; i < boolArray.length; i++) {
            boolArrayRow = boolArray[i];
            //Converting the boolArray
            tmpByte = byteConvert.boolToByte(boolArrayRow);
            
        //Now writing the returning byteArray to the output stream of the
        //tcp socket
        test.write(tmpByte);
        //System.out.println("return fra boolToByte"+Arrays.toString(tmpByte));
            
        }        
        
        
        //When done, disconnecting
        test.disconnect();
    }

}
