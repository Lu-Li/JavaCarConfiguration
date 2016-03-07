package driver;
/**
 * Unit6Driver
 * Description: driver
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import java.util.ArrayList;

import adapter.BuildAuto;
import database.AddAuto;
import database.CreateTable;
import database.DBConnection;
import database.DeleteAuto;
import database.UpdateDBAuto;
import model.Automobile;
import util.FileIO;

public class Unit6Driver {
	public static void main(String[] args) {
		DBConnection db = new DBConnection();
		//set up jdbc connection
		if (db.createConnection()){
			// read file to build auto
			BuildAuto cars = new BuildAuto();
			cars.buildAuto("Focus.txt");
			cars.buildAuto("Prius.txt");
			
			// create table from text file
			ArrayList<String> sql = FileIO.readFile("sql.txt");
			CreateTable table = new CreateTable(db, sql.get(0), sql.get(1), sql.get(2));
			table.createTable();
			
			// add new automobile to db
			System.out.println("------------- add new automobile to database ---------------");
			Automobile focus = cars.getAutoByModelname("Focus");
			Automobile prius = cars.getAutoByModelname("Prius");
			AddAuto add = new AddAuto(db);
			add.addAuto(focus);
			add.addAuto(prius);
			String[] arr1 = {"model", "make", "base_price"};
			String[] arr2 = {"model", "setname"};
			String[] arr3 = {"model", "setname", "option_name", "price"};
			
			int two = db.showAll("automobile", arr1);
			if (two == 2) {
				System.out.println("add automobile to database successfully");
			} else {
				System.out.println("test failed, add automobile error");
			}
			
			// update automobile
			System.out.println("------------- update option price & optionSet name to database---------------");
			UpdateDBAuto update = new UpdateDBAuto(db);
			// update option price
			update.updateOptionPrice("Focus", "Transmission", "Automatic", 20.0f);
			update.updateOptionsetName("Prius", "PowerMoonroof", "Power");
			db.showAll("optionset", arr2);
			db.showAll("options", arr3);
			
			// delete automobile
			System.out.println("------------- delete automobile from database ---------------");
			DeleteAuto delete = new DeleteAuto(db);
			delete.deleteAuto(focus);
			cars.delAutoByName("Focus");
			int one = db.showAll("automobile", arr1);
			if (one == 1)
				System.out.println("delete automobile from database successfully");
			else
				System.out.println("test failed, delete automobile error");
			System.out.println("");
			db.closeConnection();
		}
	}
}
