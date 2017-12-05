package javatcpclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
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
        
        try {
            boolean success = false;            
            boolean toDraw[] = new boolean[250];
            byte bytes[] = new byte[250];
            bytes[0] = 1;
            bytes[1] = 0;
            toDraw[0] = true;
            toDraw[1] = true;
            String test = "112221111222535325";
            //creating a new socket with given credentials
            plcSocket = new Socket(hostName, hostPort);
            //Then it runs a while loop until the host/PLC sends a bool
            //set to 'true' back.
            //while (!success) {
                ObjectOutputStream out = new ObjectOutputStream(plcSocket.getOutputStream());
                //DataOutputStream out = new DataOutputStream(plcSocket.getOutputStream());
                //DataInputStream in = new DataInputStream(plcSocket.getInputStream());
                System.out.println("socket open");
                //out.writeBoolean(true);
                //out.write(toDraw);
                //out.writeBoolean(false);
                //out.write(test.getBytes("US-ASCII"));
                out.writeChar(1);
                out.flush();
                out.close();
                //in.close();
                System.out.println("er vi her???");
                //Changing 'success' from the default 'false' to 'true'
                //success = in.readBoolean();
                
            //}            
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
