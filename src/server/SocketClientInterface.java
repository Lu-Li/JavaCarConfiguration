package server;
/**
 * SocketClientInterface
 * Description: the basic process of handling a socket connection
 * 
 * @version 02/21/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
public interface SocketClientInterface {
	boolean openConnection();
    void handleSession();
    void closeSession();
}
