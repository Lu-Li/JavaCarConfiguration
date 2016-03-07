package util;
/**
 * FileIO
 * Description: read from file and build automotive object
 * add read from file and return properties object to server
 * 
 * @version 02/21/2016
 * 
 * @author Lu Li  (andrew ID: ll1)
 *
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import exception.AutoException;
import model.Automobile;

public class FileIO {
	/*
	 * read from txt file, build an automotive object
	 */
	public Automobile buildAutoObject(String filename) throws AutoException {
		Automobile automotive = null;
		try {
			File file = new File(filename);
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				BufferedReader buff = new BufferedReader(fr);
				String line = buff.readLine();
				String[] arr = line.split(",");
				automotive = new Automobile(arr[0], arr[1], Float.parseFloat(arr[2]),
						Integer.parseInt(arr[3]));
				// build optionSet
				for (int i = 0; i < automotive.getSize(); i++) {
					buff.readLine();
					String[] str = buff.readLine().split(",");
					if (str.length < 2) {
						buff.close();
						throw new AutoException(3);
					} else {
						int opsetSize = Integer.parseInt(str[1]);
						automotive.setOpset(i, str[0], opsetSize);
						// build each option
						for (int j = 0; j < opsetSize; j++) {
							String[] optArr = buff.readLine().split(",");
							if (optArr.length < 2) {
								buff.close();
								throw new AutoException(2);
							} else {
								automotive.setOption(i, j, optArr[0], Float.parseFloat(optArr[1]));
							}			
						}
					}
				}
				buff.close();
			} else {
				throw new AutoException(1);
			}
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return automotive;
	}
	
	/*
	 * serialize an Automotive object to a file
	 */
	public void serializeAutomotive (Automobile auto, String filename){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(filename));
			oos.writeObject(auto);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * deserialize an Automotive from file and return it
	 */
	public Automobile deserializeAutomotive (String filename) {
		ObjectInputStream ois;
		Automobile auto = null;
		try {
			ois = new ObjectInputStream(
					new FileInputStream(filename));
			auto = (Automobile) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return auto;
	}
	/*
	 * reading a properties file
	 * return properties object from client socket over an ObjectStream
	 */
	public Properties readProperties(String filename) {
		Properties props = new Properties();
		try {
			FileInputStream in = new FileInputStream(filename);
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public static ArrayList<String> readFile(String filename) {
		ArrayList<String> list = new ArrayList<String>();
		File file = new File(filename);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader buff = new BufferedReader(fr);
			String line = buff.readLine();
			while (line != null) {
				list.add(line);
				line = buff.readLine();
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("file not found! Please enter valid path, etc: sql.txt");
		}
		return list;
	}
}
