package javatcpclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emil
 */
public class tcpMngr {

    //Attributs for the server
    String hostName = "localhost";
    int hostPort = 12345;
    
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
    
    
    public boolean tcpConnect() {
       
        boolean success = false;  
        try {          
            byte bytes[] = new byte[32];
            bytes[0] = (byte) 255;
            bytes[1] = (byte) 1;
            bytes[2] = (byte) 128;
            bytes[3] = (byte) 127;
            bytes[4] = (byte) 254;
            bytes[5] = (byte) 99;
            bytes[6] = (byte) 74;
            //creating a new socket with given credentials
            plcSocket = new Socket(hostName, hostPort);
            success = true;
                DataOutputStream out = new DataOutputStream(plcSocket.getOutputStream());
                out.write(bytes);
                out.flush();
            try {
                plcSocket.close();
            }
            catch (SocketException se) {
                System.out.println(se.getMessage());
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
    
 /*   public boolean sendArray(int[][] array){
        
        DataOutputStream in = new DataOutputStream(plcSocket.getOutputStream(array));
        
        in.
        
    }*/

}
