package database;
/**
 * CreateTable
 * Description: create database table
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
public class CreateTable {
	private DBConnection db;
//	private String autoTable = "CREATE TABLE IF NOT EXISTS automobile "
//			+ "(model VARCHAR(80) PRIMARY KEY, make VARCHAR(80), base_price FLOAT)";
//	private String setTable = "CREATE TABLE IF NOT EXISTS optionset (model VARCHAR(80), "
//			+ "setname VARCHAR(80), PRIMARY KEY (model, setname))";
//	private String optionTable = "CREATE TABLE IF NOT EXISTS options "
//			+ "(model VARCHAR(80), setname VARCHAR(80), option_name VARCHAR(80), "
//			+ "price FLOAT, PRIMARY KEY (model, setname, option_name))";
	private String autoTable;
	private String setTable;
	private String optionTable;
	
	public CreateTable() {}
	
	public CreateTable(DBConnection db, String autoTable, String setTable, String optionTable) {
		this.db = db;
		this.autoTable = autoTable;
		this.setTable = setTable;
		this.optionTable = optionTable;
	}

	public void createTable() {
		db.exeUpdate(autoTable);
		db.exeUpdate(setTable);
		db.exeUpdate(optionTable);
	}
}
