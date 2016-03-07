package adapter;
/**
 * AutoException
 * Description: five more exception that may happens when running model
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import java.util.LinkedHashMap;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public abstract class ProxyAutomobile {
	
	// <modelname, automobile>
	private static LinkedHashMap<String, Automobile> mobiles = new LinkedHashMap<String, Automobile>();
	/*
	 * build a new auto
	 */
	public void buildAuto(String filename) {
		FileIO io = new FileIO();
		Automobile auto;
		try {
			auto = io.buildAutoObject(filename);
			mobiles.put(auto.getModel(), auto);
		} catch (AutoException e) {
			e.fix();
		}
	}
	public int getSize() {
		return mobiles.size();
	}
	/*
	 * print out info of auto
	 */
	public void printAuto(String Modelname) {
		Automobile auto = mobiles.get(Modelname);
		System.out.println(auto.toString());
	}
	
	/*
	 * update option set name
	 */
	public void updateOptionSetName(String Modelname, String OptionSetname, 
			String newName) {
		Automobile auto = mobiles.get(Modelname);
		auto.updateOptionSet(OptionSetname, newName);
	}
	/*
	 * print out option set name
	 */
	public void printOptionSetName(String Modelname) {
		Automobile auto = mobiles.get(Modelname);
		for (int i = 0; i < auto.getSize(); i++) {
			System.out.println(auto.getOpsetByIndex(i));
		}
	}
	/*
	 * update option price
	 */
	public void updateOptionPrice(String Modelname, String OptionSetName, 
			String OptionName, float newprice) {
		Automobile auto = mobiles.get(Modelname);
		auto.updateOption(OptionSetName, OptionName, OptionName, newprice);		
	}
	/*
	 * print out price of option 
	 */
	public void printOptionPrice(String Modelname, String OptionSetName, 
			String OptionName) {
		Automobile auto = mobiles.get(Modelname);
		try {
			System.out.println("Price for " + OptionName + " is " + auto.getOptionPrice(OptionSetName, OptionName));
		} catch (AutoException e) {
		}
	}
	/*
	 * get option choice
	 */
	public String getOptionChoice(String Modelname, String setName) {
		Automobile auto = mobiles.get(Modelname);
		String result = null;
		try {
			result = auto.getOptionChoice(setName);
		} catch (AutoException e) {
			e.fix();
		}
		return result;
	}
	public float getOptionChoicePrice(String Modelname, String setName) {
		Automobile auto = mobiles.get(Modelname);
		float result = 0;
		try {
			result = auto.getOptionChoicePrice(setName);
		} catch (AutoException e) {
			e.fix();
		}
		return result;
	}
	public void setOptionChoice(String Modelname, String setName, String optionName) {
		Automobile auto = mobiles.get(Modelname);
		auto.setOptionChoice(setName, optionName);
	}
	
	public void addNewAuto(Automobile a) {
		mobiles.put(a.getModel(), a);
	}
	
	public void delAutoByName(String modelname) {
		mobiles.remove(modelname);
	}
	
	public String getAutoList() {
		StringBuilder sb = new StringBuilder();
		for (String auto : mobiles.keySet()) {
			sb.append(auto);
			sb.append("; ");
		}
		return sb.toString();
	}
    public Automobile getAutoByModelname(String modelname) {
    	return mobiles.get(modelname);
    }
}
