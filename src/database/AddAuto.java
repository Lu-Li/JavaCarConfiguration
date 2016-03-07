package database;
/**
 * AddAuto
 * Description: add auto to db
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.AutoException;
import model.Automobile;

public class AddAuto {
	private DBConnection db;

	public AddAuto() {}
	
	public AddAuto(DBConnection db) {
		this.db = db;
	}
	
	public void addAuto(Automobile auto) {
		try {
			// add to automobile table
			String query = "INSERT INTO automobile (model, make, base_price) VALUES (?,?,?)";
			PreparedStatement statement = db.getConn().prepareStatement(query);
			statement.setString(1, auto.getModel());
			statement.setString(2, auto.getMake());
			statement.setFloat(3, auto.getBaseprice());
			statement.executeUpdate();
			
			// add to optionset table
			ArrayList<String> opsetnames = auto.getOpsetNameList();
			for (int i = 0; i < opsetnames.size(); i++) {
				String setname = opsetnames.get(i);
				query = "INSERT INTO optionset (model, setname) VALUES (?,?)";
				statement = db.getConn().prepareStatement(query);
				statement.setString(1, auto.getModel());
				statement.setString(2, setname);
				statement.executeUpdate();
				ArrayList<String> optionNames = auto.getOptnameListBySetname(setname);
				for (int j = 0; j < optionNames.size(); j++) {
					// add to options table
					String optionName = optionNames.get(j);
					String sql = "INSERT INTO options (model, setname, option_name, price) VALUES (?, ?, ?, ?)";
					statement = db.getConn().prepareStatement(sql);
					statement.setString(1, auto.getModel());
					statement.setString(2, setname);
					statement.setString(3, optionName);
					statement.setFloat(4, auto.getOptionPrice(setname, optionName));
					statement.executeUpdate();
				}
			}
		} catch (SQLException | AutoException e) {
			e.printStackTrace();
		}
		
	}
}
