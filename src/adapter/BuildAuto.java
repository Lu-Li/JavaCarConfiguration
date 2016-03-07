package adapter;

import model.Automobile;
import server.AutoServer;

/**
 * BuildAuto
 * extends method from father object
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
public class BuildAuto 
	extends ProxyAutomobile implements CreateAuto, UpdateAuto, PrintAuto, AutoServer{

}
