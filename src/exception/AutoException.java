package exception;
/**
 * AutoException
 * Description: five more exception that may happens when running model
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import adapter.FixAuto;

public class AutoException extends Exception implements FixAuto{
	private int errno;
	public AutoException(int errno) {
		this.errno = errno;
	}
	/*
	 * fix exception according to error number
	 */
	public void fix () {
		Fix1to100 f1 = new Fix1to100();
		switch (errno) {
		case 1:
			f1.fixPathError(errno);
			break;
		case 2:
		case 3:
		case 4:
			f1.fixMissingData(errno);
			break;
		case 5: 
			f1.fixOutOfBound(errno);
			break;
		default: f1.fixNormal(errno);
		}
	}
}
