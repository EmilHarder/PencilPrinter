/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatcpclient;

/**
 *
 * @author For eksempel Johnny
 */
public class tcpTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        tcpMngr tcpManager = new tcpMngr();
        tcpManager.tcpConnect();
    }
}
