package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import adapter.BuildAuto;

/**
 * Test
 * Description: test case
 * 
 * @version 02/05/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
public class TestPartA {
	/*
	 * test the function of build auto
	 */
	public void testBuildPrintAuto() {
		System.out.println("------test build auto------");
		BuildAuto focus = new BuildAuto();
		focus.buildAuto("input.txt");
		focus.printAuto("Focus");
	}
	/*
	 * test function of update set name
	 */
	public void testUpdateOptionSetName() {
		System.out.println("-------test update option set name--------");
		BuildAuto focus = new BuildAuto();
		focus.buildAuto("input.txt");
		focus.printOptionSetName("Focus");
		focus.updateOptionSetName("Focus", "Color", "Premium Color");
		focus.printOptionSetName("Focus");
	}
	/*
	 * test function of update option price
	 */
	public void testUpdateOptionPrice() {
		System.out.println("--------test update option price---------");
		BuildAuto focus = new BuildAuto();
		focus.buildAuto("input.txt");
		focus.printOptionPrice("Focus", "Transmission", "Manual");
		focus.updateOptionPrice("Focus", "Transmission", "Manual", -200);
		focus.printOptionPrice("Focus", "Transmission", "Manual");
	}
	/*
	 * test exception handling of missing data
	 */
	public void testMissingData() {
		System.out.println("-------test missing data-------");
		BuildAuto focus = new BuildAuto();
		focus.buildAuto("MissingData.txt");
	}
	/*
	 * test exception of path error
	 */
	public void testPathError() {
		System.out.println("-------test path error-------");
		BuildAuto focus = new BuildAuto();
		focus.buildAuto("wrongPath");
	}
	public static void main(String[] args) throws IOException{
		FileWriter fw = new FileWriter("testPartA_output.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter writer = new PrintWriter(bw);
		TestPartA test = new TestPartA();
		writer.println("-----test starts.-----");
		test.testBuildPrintAuto();
		test.testUpdateOptionSetName();
		test.testUpdateOptionPrice();
		test.testPathError();
		test.testMissingData();
		writer.close();
	}
}
