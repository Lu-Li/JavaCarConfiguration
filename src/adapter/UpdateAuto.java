package adapter;
/**
 * UpdateAuto
 * Description: interface
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
public interface UpdateAuto {

	public void updateOptionSetName(String Modelname, String OptionSetname, 
				String newName);
	public void updateOptionPrice(String Modelname, String OptionSetName, 
			String OptionName, float newprice);
	public void setOptionChoice(String Modelname, String setName, String optionName);

}
