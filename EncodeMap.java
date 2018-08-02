package edu.metrostate.ics240.p5.STP059.morse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EncodeMap {
	Map<Character, String> encoder;
	/**
	 * builds a map using the text file and creating key value pairs for each character in morseCode.txt
	 */
	public Map<Character, String> buildMap() {
		String fileName = "morseCode.txt";
		HashMap<Character, String> encoderMap = new HashMap<>();
		String line = new String();
		String value = new String();
		String[] tempKeyValArray = new String[2];
		Character key = null;

		try (BufferedReader inputReader = new BufferedReader(new FileReader(fileName))) {
			while ((line = inputReader.readLine()) != null) {
				tempKeyValArray = line.split("\\t");
				key = tempKeyValArray[0].charAt(0);
				value = tempKeyValArray[1];
				encoderMap.put(key, value);
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
		return encoderMap;
	}
}
