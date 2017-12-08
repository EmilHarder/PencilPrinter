package pencilprinter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ancla
 */
public class RobotClient {

    private String hostname;
    private int port;
    private Socket connection;
    private DataOutputStream out;

    /**
     *
     * @param hostname Hostname of the robot
     * @param port Port of the robot
     */
    
        public RobotClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Method which connects to the robot, using the parameters provided to the constructor.
     */
    public void connect() {
        try {
            connection = new Socket(hostname, port);
            out = new DataOutputStream(connection.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(RobotClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connecting to robot: " + ex.getMessage());
        }
    }

    /**
     * This method is used to determine if a connection has been established to
     * the robot.
     *
     * @return COnnection state to see if connection is established (true) or
     * not (false).
     */
    public boolean isConnected() {
        return connection.isConnected();
    }

    /**
     * This method writes a message to the robot iff a connection to the robot
     * is established.
     *
     * @param message The message to write to the robot.
     */
    public void write(byte[] message) throws IOException {
        if (isConnected()) {
            out.write(message);
            out.flush();
        }
    }

    /**
     * Method to close connection to the robot.
     */
    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (IOException ex) {
                Logger.getLogger(RobotClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}