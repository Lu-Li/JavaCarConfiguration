package database;
/**
 * DeleteAuto
 * Description: delete auto from db
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import adapter.BuildAuto;
import model.Automobile;

public class DeleteAuto {
	private DBConnection db;

	public DeleteAuto(){}
	
	public DeleteAuto(DBConnection db) {
		this.db = db;
	}
	
	public void deleteAuto(Automobile auto) {
		String model = auto.getModel();
		String deloption = "DELETE FROM options WHERE model='" + model + "'";
		String delset = "DELETE FROM optionset WHERE model='" + model + "'";
		String delauto = "DELETE FROM automobile WHERE model='" + model + "'";
		
//		System.out.println("deloption query = " + deloption);
		db.exeUpdate(deloption);
		db.exeUpdate(delset);
		db.exeUpdate(delauto);
		
		BuildAuto mobile = new BuildAuto();
		mobile.delAutoByName(model);
	}
}
