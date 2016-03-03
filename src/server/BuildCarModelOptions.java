/**
 * BuildCarModelOptions
 * Description: 
 *  Accept properties object from client socket over an ObjectStream 
 *  and create an Automobile.
 *  add that created Automobile to the LinkedHashMap
 * @version 02/21/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
package server;

import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

public class BuildCarModelOptions {
	
	public static Automobile createAutoFromProp(Properties props) {
		Automobile a = new Automobile();
        String carMake = props.getProperty("CarMake");
        a.setMake(carMake);
        if (!carMake.equals(null)) {
        	String carModel = props.getProperty("CarModel");
            float carPrice = Float.parseFloat(props.getProperty("CarPrice"));
            String optSetName1 = props.getProperty("optSetName1");
            String option1a = props.getProperty("Option1a");
            float optionPrice1a = Float.parseFloat(props.getProperty("OptionPrice1a"));
            String option1b = props.getProperty("Option1b");
            float optionPrice1b = Float.parseFloat(props.getProperty("OptionPrice1b"));
            String optSetName2 = props.getProperty("optSetName2");
            String option2a = props.getProperty("Option2a");
            float optionPrice2a = Float.parseFloat(props.getProperty("OptionPrice2a"));
            String option2b = props.getProperty("Option2b");
            float optionPrice2b = Float.parseFloat(props.getProperty("OptionPrice2b"));
            String optSetName3 = props.getProperty("optSetName3");
            String option3a = props.getProperty("Option3a");
            float optionPrice3a = Float.parseFloat(props.getProperty("OptionPrice3a"));
            String option3b = props.getProperty("Option3b");
            float optionPrice3b = Float.parseFloat(props.getProperty("OptionPrice3b"));
            String optSetName4 = props.getProperty("optSetName4");
            String option4a = props.getProperty("Option4a");
            float optionPrice4a = Float.parseFloat(props.getProperty("OptionPrice4a"));
            String option4b = props.getProperty("Option4b");
            float optionPrice4b = Float.parseFloat(props.getProperty("OptionPrice4b"));
            String optSetName5 = props.getProperty("optSetName5");
            String option5a = props.getProperty("Option5a");
            float optionPrice5a = Float.parseFloat(props.getProperty("OptionPrice5a"));
            String option5b = props.getProperty("Option5b");
            float optionPrice5b = Float.parseFloat(props.getProperty("OptionPrice5b"));
            
            a.setModel(carModel);
            a.setBaseprice(carPrice);
            a.addOptSet(optSetName1);
            a.addOption(optSetName1, option1a, optionPrice1a);
            a.addOption(optSetName1, option1b, optionPrice1b);
            a.addOptSet(optSetName2);
            a.addOption(optSetName2, option2a, optionPrice2a);
            a.addOption(optSetName2, option2b, optionPrice2b);
            a.addOptSet(optSetName3);
            a.addOption(optSetName3, option3a, optionPrice3a);
            a.addOption(optSetName3, option3b, optionPrice3b);
            a.addOptSet(optSetName4);
            a.addOption(optSetName4, option4a, optionPrice4a);
            a.addOption(optSetName4, option4b, optionPrice4b);
            a.addOptSet(optSetName5);
            a.addOption(optSetName5, option5a, optionPrice5a);
            a.addOption(optSetName5, option5b, optionPrice5b);
        }
		return a;
	}
	
	public static void addAuto(Automobile a){
        AutoServer as = new BuildAuto();
        as.addNewAuto(a);
    }
}
