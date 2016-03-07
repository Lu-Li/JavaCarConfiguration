/**
 * EditOptions
 * Description: used to edit Options for a given model in its own thread
 * 
 * @version 02/14/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
package scale;

import adapter.UpdateAuto;

public class EditOptions implements Runnable{
	/*
	 * UpdateAuto Interface is used to enable interaction
	 *  between EditOption and BuildAuto class.
	 */
	private UpdateAuto auto;
    private float price;
    // if false, set to remove synchronization, causes data corruption
    private boolean isSyn;

    public EditOptions(UpdateAuto auto, float price, boolean isSyn) {
        this.auto = auto;
        this.price = price;
        this.isSyn = isSyn;
    }
    
    /*
     * wait to easly show the data corruption
     */
    void randomWait() {
        try {
            Thread.currentThread();
			Thread.sleep((long)(3000*Math.random()));
        }
        catch(InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }

    /*
     * edit several color prices to test if the data is corrupted
     */
    public void testEdit() {
    	randomWait();
    	auto.updateOptionPrice("Focus", "Color", "Infra-Red Clearcoat", this.price);
    	randomWait();
    	auto.updateOptionPrice("Focus", "Color", "Pitch Black Clearcoat", this.price);
    	randomWait();
    	auto.updateOptionPrice("Focus", "Color", "Cloud 9 White Clearcoat", this.price);
    	randomWait();
    	auto.updateOptionPrice("Focus", "Color", "Liquid Grey Clearcoat Metallic", this.price);
    }
    
	@Override
	public void run() {
		if (isSyn) {
			synchronized (auto) {
				testEdit();
			}
		} else {
			testEdit();
		}
	}
	
}
