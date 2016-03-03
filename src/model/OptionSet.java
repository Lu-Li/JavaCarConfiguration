package model;
/**
 * OptionSet
 * Description: contains optionSet name and an array of options
 * make Option class an Inner class of OptionSet
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
class OptionSet implements Serializable {		
		private String name;
		private ArrayList<Option> opt;
		private Option choice;
		/*
		 * constructor
		 */
		protected OptionSet() {
			this.opt = new ArrayList<Option>();
		};
		protected OptionSet(String name) {
			this.name = name;
			this.opt = new ArrayList<Option>();
		}
		protected OptionSet(String n, int size) {
			opt = new ArrayList<Option>();
			for(int i=0;i<size;i++)
				opt.add(new Option());
			name = n; 
		}
		
		/*
		 * getters
		 */
		protected String getName() {
			return name;
		}
		protected ArrayList<Option> getOpt() {
			return opt;
		}
		protected int getSize() {
			return opt.size();
		}
		protected String getOption(int index) {
			return opt.get(index).toString();
		}
		protected Option getOptionChoice() {
			return this.choice;
		}
		protected Option getOptionByName(String optionName) {
			for (int i = 0; i < opt.size(); i++) {
				if (opt.get(i).getName().equals(optionName)) {
					return opt.get(i);
				}
			}
			return null;
		}
		/*
		 * finder
		 */
		protected int findOptionIndex(String optionName) {
			for (int i = 0; i < opt.size(); i++) {
				if (opt.get(i).getName().equals(optionName))
					return i;
			}
			return -1;
		}
		
		/*
		 * setters
		 */
		protected void setName(String name) {
			this.name = name;
		}
		protected void setOpt(ArrayList<Option> opt) {
			this.opt = opt;
		}
		protected void setOption(int index, String optionName, float optionPrice) {
			this.opt.set(index,new Option(optionName, optionPrice));
		}
		protected void setOption(int index, Option option) {
			this.opt.set(index, option);
		}
		protected void setOptionChoice(String optionName) {
			Option option = this.getOptionByName(optionName);
			this.choice = option;
		}
		protected void setOptionSet(String optionSetName, OptionSet optionSet) {
			
		}
		/*
		 * delete an option
		 */
		protected void deleteOption(int index) {
			if (index < 0)
				System.err.println("index out of bound, delete option error");
			else {
				opt.remove(index);
			}
		}
		
		protected void deleteOption(String optionName) {
			int index = findOptionIndex(optionName);
			if (index < 0)
				System.err.println("index out of bound, delete option error");
			else {
				opt.remove(index);
			}
		}
		/*
		 * update
		 */
		protected void updateOption(String optName, String newName, float optionPrice) {
			int index = findOptionIndex(optName);
			if (index < 0)
				System.err.println("can not find option with this name. Update option error");
			else
				this.opt.set(index, new Option(newName, optionPrice));
		}
		
		public void addOption(String optionName, float price) {
			opt.add(new Option(optionName, price));
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
	    	sb.append("OptionSet [name=");
	    	sb.append(this.name);
	    	sb.append(",\n");
	    	for (int i = 0; i < opt.size(); i++) {
	    		sb.append("  --optionIndex = ");
	    		sb.append(i);
	    		sb.append(", ");
	    		sb.append(opt.get(i).toString());
	    	}
	    	sb.append(" ]\n");
	    	return sb.toString();
		}
		
		/*
		 * Option: inner class
		 */
		protected class Option implements Serializable{
			String name;
			float price;
			
			/*
			 * constructor
			 */
			protected Option () {
				name = null;
				price = 0;
			};
			protected Option(String name, float price) {
				this.name = name;
				this.price = price;
			}
			/*
			 * getters
			 */
			public String getName() {
				return name;
			}
			public float getPrice() {
				return price;
			}
			/*
			 * setters
			 */
			public void setName(String name) {
				this.name = name;
			}
			public void setPrice(float price) {
				this.price = price;
			}
			public void updateOption(String name, float price) {
				this.name = name;
				this.price = price;
			}
			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder();
		    	sb.append("Option [name=");
		    	sb.append(this.name);
		    	sb.append(", price=");
		    	sb.append(this.price);
		    	sb.append("]\n");
		    	return sb.toString();
			}
		}

		public float getOptionPriceByName(String optionName) {
			for (int i = 0; i < opt.size(); i++) {
				if (opt.get(i).getName().equals(optionName)) {
					return opt.get(i).getPrice();
				}
			}
			return 0;
		}
}
