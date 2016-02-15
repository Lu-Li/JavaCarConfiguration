package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import adapter.BuildAuto;

public class TestPartB {
	/*
	 * test set option choice function
	 */
	public void testSetOptionChoice() {
		System.out.println("-------test set option choice--------");
		BuildAuto focus = new BuildAuto();
		focus.buildAuto("input.txt");
		focus.setOptionChoice("Focus", "Transmission", "Automatic");
		System.out.println("option choice is " 
				+ focus.getOptionChoice("Focus", "Transmission"));
	}
	/*
	 * test update option set name
	 */
	public void testOptionSetName() {
		System.out.println("-------test option set name--------");
		BuildAuto auto = new BuildAuto();
		auto.buildAuto("input.txt");
		auto.printOptionSetName("Focus");
		auto.updateOptionSetName("Focus", "Color", "Premium Color");
		auto.printOptionSetName("Focus");
	}
	/*
	 * test linkedhashmap is used correctly
	 */
	public boolean testLinkedHashMap() {
		System.out.println("-------test linkedHashMap--------");
		BuildAuto auto = new BuildAuto();
		auto.buildAuto("input.txt");
		auto.buildAuto("input2.txt");
		return auto.getSize() == 2;
	}
	/*
	 * test option choice price can be update and print correctly
	 */
	public boolean testOptionChoicePrice() {
		System.out.println("-------test option choice price--------");
		BuildAuto focus = new BuildAuto();
		focus.buildAuto("input.txt");
		focus.setOptionChoice("Focus", "Transmission", "Manual");
		float origin = focus.getOptionChoicePrice("Focus", "Transmission");
		focus.updateOptionPrice("Focus", "Transmission", "Manual", -200);
		float after = focus.getOptionChoicePrice("Focus", "Transmission");
		return origin != after;
	}
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("testPartB_output.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter writer = new PrintWriter(bw);
		TestPartB test = new TestPartB();
		writer.println("-----test starts.-----");
		test.testSetOptionChoice();
		test.testOptionSetName();
		if (test.testOptionChoicePrice()) {
			System.out.println("test for update option choice price passed");
			writer.println("test for update option choice price passed");
		} else {
			System.out.println("test for update option choice price failed");
			writer.println("test for update option choice price failed");
		}
		if (test.testLinkedHashMap()) {
			System.out.println("test for add two automobile passed");
			writer.println("test for add two automobile passed");
		} else {
			System.out.println("test for add two automobile failed");
			writer.println("test for add two automobile failed");
		}
		writer.close();
	}
}
