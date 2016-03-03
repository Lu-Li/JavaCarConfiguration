/**
 * Server
 * Description: the driver that keeps listening on one port
 * 
 * @version 02/21/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
package server;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {
		// set up server
		ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("start listening on port: 4444.");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        
        // set up client
        Socket clientSocket = null;
        try {
        	while(true) {
        		clientSocket = serverSocket.accept();
        		DefaultSocketClient dsc = new DefaultSocketClient(clientSocket);
                new Thread(dsc).start();
        	}       
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        
        // close server when error occurs
        try {
            System.out.println("Stopping server...");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    
	}
}
