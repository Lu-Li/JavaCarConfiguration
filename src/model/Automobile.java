package model;
/**
 * Automotive
 * Description: contains an array of optionSet
 * have setters, getters, find, update and delete function
 * synchronize all methods, used for creating, reading, 
 * updating and deleting parts of Automobile, OptionSet and Option classes
 * 
 * @version 02/14/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import model.OptionSet.Option;

@SuppressWarnings("serial")
public class Automobile implements Serializable{
	
	private String make;
	private String model;
	private float baseprice;
	private ArrayList<OptionSet> opset;
	
	
	/*
	 * Constructor
	 */
	public Automobile() {
		opset = new ArrayList<OptionSet>();
	};
	
	public Automobile (String make, String model, float baseprice, int size) {
		this.make = make;
		this.model = model;
		this.baseprice = baseprice;
		opset = new ArrayList<OptionSet>();
		for(int i=0;i<size;i++) 
			opset.add(new OptionSet());
	}

	/*
	 * getters
	 */	
	public synchronized String getMake() {
		return this.make;
	}
	
	public synchronized String getModel() {
		return this.model;
	}

	public synchronized float getBaseprice() {
		return baseprice;
	}
	
	public synchronized ArrayList<OptionSet> getOpset() throws AutoException {
		if (this.opset == null)
			throw new AutoException(3);
		return this.opset;
	}
	
	public synchronized int getSize() {
		return this.opset.size();
	}
	// get the string info of optionSet
	public synchronized String getOpsetByIndex(int index) {
		return opset.get(index).toString();
	}
	
	public synchronized ArrayList<String> getOpsetNameList() {
		ArrayList<String> optset = new ArrayList<>();
		for (int i = 0; i < opset.size(); i++) {
			optset.add(opset.get(i).getName());
		}
		return optset;
	}
	
	public synchronized ArrayList<String> getOptnameListBySetname(String opsetName) {
		ArrayList<String> optset = new ArrayList<>();
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(opsetName)) {
				return opset.get(i).getOptionList();
				
			}
		}
		return optset;
	}
	
	public synchronized String getOptionChoice(String setName) throws AutoException {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(setName)) {
				return opset.get(i).getOptionChoice().getName();
			}
		}
		throw new AutoException(4);
	}
	public synchronized float getOptionChoicePrice (String setName) throws AutoException {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(setName)) {
				return opset.get(i).getOptionChoice().getPrice();
			}
		}
		throw new AutoException(2);
	}
	public synchronized float getOptionPrice(String OptionSetName, String OptionName) throws AutoException {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(OptionSetName)) {
				return opset.get(i).getOptionPriceByName(OptionName);
			}
		}
		throw new AutoException(2);
	}
	public synchronized float getTotalPrice() {
		float price = this.baseprice;
		for (int i = 0; i < opset.size(); i++) {
			price += opset.get(i).getOptionChoice().getPrice();
		}
		return price;
	}
	/*
	 * finder
	 */
	//Use the name of the option to obtain OptionSet name. 
	//Example- providing the argument Blue you should return the OptionSet Color. 
	// Providing the argument manual return transmission. 
	public synchronized String findOptionSet(String optionName) {
		for(int i=0; i<opset.size(); i++) {
			ArrayList<Option> opt = opset.get(i).getOpt();
			for (int j = 0; j < opt.size(); j++) {
				if (opt.get(j).getName().equals(optionName)){
					return opset.get(i).getName();
				}
			}
		}
		return null;
	}
	
	public synchronized int findOptionSetIndex(String opsetName) {
		for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(opsetName))
				return i;
		}
		return -1;
	}
	// Find Option with name
	// return the index of that option in the option array
	public synchronized int findOptionIndexByName(String name) {
		for(int i=0; i<opset.size(); i++) {
			ArrayList<Option> opt = opset.get(i).getOpt();
			for (int j = 0; j < opt.size(); j++) {
				if (opt.get(j).getName().equals(name)){
					return j;
				}
			}
		}
		return -1;
	}
	
	/*
	 * setters
	 */
	public synchronized void setMake(String make) {
		this.make = make;
	}
	
	public synchronized void setModel(String name) {
		this.model = name;
	}

	public synchronized void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}

	public synchronized void setOpset(ArrayList<OptionSet> opset) {
		this.opset = opset;
	}
	
	public synchronized void setOpset(int opsetIndex, OptionSet optionSet) throws AutoException {
		if (opsetIndex < opset.size())
			this.opset.set(opsetIndex, optionSet);
		else
			throw new AutoException(5);
	}
	// set Option Set with name and size of options
    public synchronized void setOpset(int opsetIndex, String optsetName, int size) throws AutoException{
        if (opsetIndex < opset.size())
        	opset.set(opsetIndex, new OptionSet(optsetName, size));
        else
        	throw new AutoException(5);
    }
    
    public synchronized void setOption(int opsetIndex, int optIndex, String optionName, float optionPrice){
    	if (opsetIndex < opset.size())
    		this.opset.get(opsetIndex).setOption(optIndex, optionName, optionPrice);
		else
			System.err.println("Index out of bounds, set Option error");
    }
    public synchronized void setOptionChoice(String setName, String optionName) {
    	for (int i = 0; i < opset.size(); i++) {
			if (opset.get(i).getName().equals(setName)) {
				opset.get(i).setOptionChoice(optionName);
			}
		}
    }
    
    /*
     * delete
     */
    public synchronized void deleteOption(int opsetIndex, int optIndex) {
    	if (opsetIndex < 0 || optIndex < 0)
    		System.err.println("index out of bound. delete option error");
    	else
    		this.opset.get(opsetIndex).deleteOption(optIndex);
    }
    public synchronized void deleteOption(int opsetIndex, String optionName) {
    	int optIndex = findOptionIndexByName(optionName);
    	if (opsetIndex < 0 || optIndex < 0)
    		System.err.println("index out of bound. delete option error");
    	else
    		this.opset.get(opsetIndex).deleteOption(optIndex);	
    }
    
    public synchronized void deleteOptionSet(int opsetIndex) {
    	if (opsetIndex < 0)
    		System.err.println("index out of bound. delete option set error");
    	else {
    		opset.remove(opsetIndex);
    	}
    }
    
    public synchronized void deleteOptionSet(String opsetName) {
    	int opsetIndex = findOptionSetIndex(opsetName);
    	if (opsetIndex < 0)
    		System.err.println("index out of bound. delete option set error");
    	else {
    		opset.remove(opsetIndex);
    	}
    }
	/*
	 * update: find and set
	 * Update method will be used to change an originally set Option set/Option name, price etc.
	 */
    // update values of optionSet
    public synchronized void updateOptionSet (String opsetName,String newname) {
    	int opsetIndex = findOptionSetIndex(opsetName);
    	if (opsetIndex < 0) {
    		System.err.println("index out of bound. update option set error");
    		return;
    	}	
    	OptionSet element = opset.get(opsetIndex);
    	element.setName(newname);
		opset.set(opsetIndex, element);
    }
    
    // update values of options
    public synchronized void updateOption (String opsetName, String optName, String newName, float optionPrice) {
		for(int i=0; i<opset.size(); i++) {
			if (opset.get(i).getName().equals(opsetName)) {
				ArrayList<Option> opt = opset.get(i).getOpt();
				for (int j = 0; j < opt.size(); j++) {
					if (opt.get(j).getName().equals(optName)){
						opt.get(j).updateOption(newName, optionPrice);
					}
				}
			}
		}
    }
    
    /*
     * add optionset and option
     */   
    public void addOptSet(String optSetName) {
    	OptionSet os = new OptionSet(optSetName);
    	opset.add(os);
    }
    
    public void addOption(String optSetName, String optionName, float price) {
    	for(int i=0; i<opset.size(); i++) {
    		if (opset.get(i).getName().equals(optSetName)) {
    			opset.get(i).addOption(optionName, price);
    		}
		}
    }
    
	@Override
    public synchronized String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Automotive [model=");
    	sb.append(this.model);
    	sb.append(", baseprice=");
    	sb.append(this.baseprice);
    	sb.append(", opset[] when \n");
    	for (int i = 0; i < opset.size(); i++) {
    		sb.append(" -optSetIndex = ");
    		sb.append(i);
    		sb.append(", ");
    		sb.append(opset.get(i).toString());
    	}
    	sb.append("]\n");
    	return sb.toString();
    }
}
