package exception;
/**
 * Fix1to100
 * Description: Fix error number from 1 to 100
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
public class Fix1to100 {
	
	public void fixPathError(int errno) {
		System.err.println("File not found. Please enter correct file path and name");
	}

	public void fixMissingData(int errno) {
		switch(errno) {
		case 2:
			this.printError("price");
			break;
		case 3:
			this.printError("OptionSet");
			break;
		case 4:
			this.printError("Option");
			break;
		default:
			this.printError("");
			break;
		}
	}
	/*
	 * fix index out of bounds
	 */
	public void fixOutOfBound(int errno) {
		System.err.println("Index out of bounds exception! Please check if init array and list.");
	}

	public void fixNormal(int errno) {
		System.err.println("Exception exist. Error number is " + errno);
	}
	
	public void printError(String type) {
		System.err.println("Missing " + type +" data");
	};
}

