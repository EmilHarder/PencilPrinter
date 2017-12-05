package pencilprinter;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emil
 */
public class tcpMngr {

    //Attributs for the server
    String hostName = "localhost";
    int hostPort = 4444;
    
    Socket plcSocket;
        
     /**
     *
     * Tries to connect to a given IP/hostname and port.
     * It should be the IP:port of the plc.
     * 
     * 
     * @param hstN is the given hostname
     * @param hstP is the given port
     * @return returns the bool true if a connection is
     * successfull.
     */
    
    
    public boolean tcpConnect(String hstN, int hstP) {
        
        try {
            hostName = hstN;
            hostPort = hstP;
            boolean success = false;            
            
            //creating a new socket with given credentials
            plcSocket = new Socket(hostName, hostPort);
            
            //Then it runs a while loop until the host/PLC sends a bool
            //set to 'true' back.
            while (!success) {
                
                DataInputStream in = new DataInputStream(plcSocket.getInputStream());
                
                //Changing 'success' from the default 'false' to 'true'
                success = in.readBoolean();
                
            }            
            
            //The same var is used to return a message, stating connection is
            //established
            return success;
            
            //If the socket cannot be connected, it throws a exception
            //That exception is used to return a false
        } catch (IOException ex) {
            Logger.getLogger(tcpMngr.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
//    //This function sends the found immg size as a array.
//    //EVENTUAL
//    public boolean sendSize(int x, int y){
//        int[] imgSize = new int[2];
//        
//        imgSize[0] = x;
//        imgSize[1] = y;
//        
//    }
    
    /*
    Function to send an 2D array to the opened socket.
    */
    
    public boolean sendArray(boolean[] array){
        
        
        int arrLength = array.length;
        int boolToInt;
        
        
        for (int i = 0; i < arrLength; i++) {
        
            if (array[i] == true){
                boolToInt = 1;
            }
            else if (array[i] != true){
                boolToInt = 0;
            }
            else {
                boolToInt = 2;
            }
            
            ByteArrayOutputStream out = new ByteArrayOutputStream(boolToInt);
            
            out.writeTo(plcSocket);
            
        }
        
        
        
        
        
        //ObjectOutputStream out = new ObjectOutputStream(array);
        //DataOutputStream out = new DataOutputStream(plcSocket.getOutputStream(array));
        
        
        
    }

}
