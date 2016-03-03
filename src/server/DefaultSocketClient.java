/**
 * DefaultSocketClient
 * Description: handling a socket connection
 * 
 * @version 02/21/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
package server;

import java.io.*;

import java.net.Socket;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

public class DefaultSocketClient extends Thread implements SocketClientInterface,
                          SocketClientConstants {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket sock;
	private AutoServer server;
	
	public DefaultSocketClient(Socket clientSocket) {
		this.sock = clientSocket;
		server = new BuildAuto();
	}

	public void run(){
	   if (openConnection()){
	      handleSession();
	      closeSession();
	   }
	}//run
	
	public boolean openConnection(){
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject("accept client connection");
			ois = new ObjectInputStream(sock.getInputStream());
			ois.readObject();
		} catch (Exception e) {
			if (DEBUG) System.err.println
		       ("Unable to obtain stream to/from " + sock.getLocalAddress());
		     return false;
		}
		return true;
	}            
	
	// input: Upload Properties file; 
	public void handleSession() {
		String input = "";
		boolean running = true;
		while (running) {
			try {
				input = (String) ois.readObject();
				input = input.toLowerCase();
				if (DEBUG)
					System.out.println("in handle session, input = " + input);
				switch (input) {
				case "quit":
					running = false;
					break;
				case "upload":
					Properties props = (Properties) ois.readObject();
					Automobile auto = BuildCarModelOptions.createAutoFromProp(props);
					if (DEBUG)
						System.out.println("upload success, auto model = " + auto.getModel());
					server.addNewAuto(auto);
					break;
				case "list":
					String list = server.getAutoList();
					if (list == null) {
						oos.writeObject("");
					} else {
						oos.writeObject(list);
					}
					if (DEBUG)
						System.out.println("auto list = " + list);
					break;
				case "select":
					String  modelname = (String) ois.readObject();
					if (DEBUG)
						System.out.println("select " + modelname);
					Automobile a = server.getAutoByModelname(modelname);
					oos.writeObject(a);
					break;
				default:
					break;
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
    public void closeSession(){
       try {
          ois.close();
          oos.close();
          sock.close();
       }
       catch (IOException e){
         if (DEBUG) System.err.println
          ("Error closing socket to " + sock.getLocalAddress());
       }       
    }


}// class DefaultSocketClient


