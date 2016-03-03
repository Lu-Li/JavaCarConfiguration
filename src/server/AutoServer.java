/**
 * AutoServer
 * Description: 
 * AutoServer interface be implemented in BuildAuto
 * @version 02/21/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
package server;

import model.Automobile;

public interface AutoServer {
	public void addNewAuto(Automobile a);
	public String getAutoList();
    public Automobile getAutoByModelname(String modelname);
}
