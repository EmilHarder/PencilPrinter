package pencilprinter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author emil
 */
public class Pencilprinter {

    public static void main(String[] args) throws IOException {

        //Attributs for the server
        String hostName = "localhost";
        int hostPort = 4444;
        
        //Temp String for data to send to PLC/JAVA-server
        int[] message = new int[10];
        
        for (int i = 0; i < 10; i++) {
            
            message[i] = i;
            System.out.println(i);
            
        }

            Socket echoSocket = new Socket(hostName, hostPort);
            
            ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());
            
            out.writeObject(message);
            
            //echoSocket.getOutputStream().write(message.getBytes("UTF-8"));

    }

}
