package driver;
import adapter.BuildAuto;
/**
 * Driver
 * Description: main function
 * 
 * @version 02/14/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import exception.AutoException;
import scale.EditOptions;

public class Driver {
	public static void main(String [] args) throws AutoException {
		//Build Automotive Object from a file.
		BuildAuto auto = new BuildAuto();
		auto.buildAuto("input.txt");
		auto.printAuto("Focus");
		EditOptions unsyn1 = new EditOptions(auto, 1, false);
		EditOptions unsyn2 = new EditOptions(auto, 2, false);

		// test unsynchronized version
		Thread t1 = new Thread(unsyn1);
		Thread t2 = new Thread(unsyn2);
		boolean t1IsAlive = true;
        boolean t2IsAlive = true;
        System.out.println("unsynchronized test starts!");
        t1.start();
        t2.start();
        do {
            if(t1IsAlive && !t1.isAlive()) {
                t1IsAlive = false;
                System.out.println("t1 is dead.");
            }

            if(t2IsAlive && !t2.isAlive()) {
                t2IsAlive = false;
                System.out.println("t2 is dead.");
            }
        } while(t1IsAlive || t2IsAlive);
        auto.printOptionPrice("Focus", "Color", "Infra-Red Clearcoat");
		auto.printOptionPrice("Focus", "Color", "Pitch Black Clearcoat");
		auto.printOptionPrice("Focus", "Color", "Cloud 9 White Clearcoat");
		auto.printOptionPrice("Focus", "Color", "Liquid Grey Clearcoat Metallic");
		
		// test synchronized version:
        unsyn1 = new EditOptions(auto, 1, true);
        unsyn1 = new EditOptions(auto, 2, true);
        t1 = new Thread(unsyn1);
		t2 = new Thread(unsyn2);
		t1IsAlive = true;
        t2IsAlive = true;
        System.out.println("synchronized test starts!");
        t1.start();
        t2.start();
        do {
            if(t1IsAlive && !t1.isAlive()) {
                t1IsAlive = false;
                System.out.println("t1 is dead.");
            }

            if(t2IsAlive && !t2.isAlive()) {
                t2IsAlive = false;
                System.out.println("t2 is dead.");
            }
        } while(t1IsAlive || t2IsAlive);
        auto.printOptionPrice("Focus", "Color", "Infra-Red Clearcoat");
		auto.printOptionPrice("Focus", "Color", "Pitch Black Clearcoat");
		auto.printOptionPrice("Focus", "Color", "Cloud 9 White Clearcoat");
		auto.printOptionPrice("Focus", "Color", "Liquid Grey Clearcoat Metallic");
	}
}
