package database;
/**
 * UpdateDBAuto
 * Description: update price and setname
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDBAuto {
		private DBConnection db;

		
		public UpdateDBAuto(){}
		
		public UpdateDBAuto(DBConnection db) {
			this.db = db;
		}
		
		public void updateOptionPrice(String model, String setname, String option, float newPrice) {
			try {
				String query = "UPDATE options SET price = ? WHERE model = ? AND setname = ? AND option_name = ?";
				PreparedStatement statement = db.getConn().prepareStatement(query);
				statement.setFloat(1, newPrice);
				statement.setString(2, model);
				statement.setString(3, setname);
				statement.setString(4, option);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void updateOptionsetName(String modelName, String OptionSetname, 
				String newName) {
			try {
				// update optionset table
				String query = "UPDATE optionset SET setname = ? WHERE model = ? AND setname = ?";
				PreparedStatement statement = db.getConn().prepareStatement(query);
				statement.setString(1, newName);
				statement.setString(2, modelName);
				statement.setString(3, OptionSetname);
				statement.executeUpdate();
				
				//update options table
				query = "UPDATE options SET setname = ? WHERE model = ? AND setname = ?";
				statement = db.getConn().prepareStatement(query);
				statement.setString(1, newName);
				statement.setString(2, modelName);
				statement.setString(3, OptionSetname);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
