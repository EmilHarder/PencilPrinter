/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pencilprinter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 *
 * @author emil
 */
public class Pencilprinter {

    public static void main(String[] args) throws IOException {

        //Attributs for the server
        String hostName = "localhost";
        int hostPort = 4444;
        
        //Temp String for data to send to PLC
        String message = "tester2";

            Socket echoSocket = new Socket(hostName, hostPort);
            
            echoSocket.getOutputStream().write(message.getBytes("UTF-8"));

    }

}
