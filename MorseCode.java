package edu.metrostate.ics240.p5.STP059.morse;

import java.util.Map;

import edu.metrostate.ics240.p5.STP059.morse.DecodeTree.*;
import edu.metrostate.ics240.p5.STP059.morse.TreeNode;

public class MorseCode {
	private static Map<Character, String> encoder;
	private static MorseNode decoder;
	
	static {
		encoder = new EncodeMap().buildMap();
		new DecodeTree().decodeTreeBuilder(encoder);
		decoder = DecodeTree.getRoot();
	}
	
	/*
	 * Translates the given text of ASCII Latin Characters to its morse code equivalent. The encoding places a space 
	   between encoded characters; a space in the text is represented as a SLASH ('/') character. 
	   The text can be in mixed case.
	   Preconditions
	   The text string cannot be null. If so, the method will throw a NullPointerException
	   The text contains only the subset of characters contained in the provided morseCode.txt file and spaces.
	 */
	public static String encode(String text) {
		if (text == null) {
			throw new NullPointerException("Encoding Input Cannot Be Null");
		}
		Map<Character, String> encoder = getEncodingMap();
		StringBuffer builder = new StringBuffer();
		char[] inputTextChars = new char[text.length()];
		inputTextChars = text.toUpperCase().toCharArray();
		String morseKey = new String();

		for (char inputChar : inputTextChars) {
			if (encoder.containsKey(inputChar)) {
				morseKey = encoder.get(inputChar);
				builder.append(morseKey + " ");
			}
			if (!encoder.containsKey(inputChar)) {
				if (inputChar == ' ') {
					builder.append('/');
				} else {
					throw new IllegalArgumentException(
					String.format("Illegal character encountered: %s [%s]", text, inputChar));
				}
			}
		}
		return builder.toString().replaceAll(" /", "/").trim();
		}
	}

	/*
	 * Decodes the provided code string to its text representation. Since Morse Code is case-less, 
	   the returned string will be in all UPPER CASE characters.
	   Preconditions:
   	   The code string cannot be null. If so, the method will throw a NullPointerException
	   The code string contains only DOTs ('*'), DASHes('-'), spaces that separate characters, and SLASHes('/') that represent spaces in the original text.
	 */
	public static String decode(String code) {
		if (code == null) {
			throw new NullPointerException("Decoding Code cannot be Null");
		}
		String[] splitWords = code.split("/");
		StringBuffer realTextBuilder = new StringBuffer();
		for (int i = 0; i < splitWords.length; i++) {
			String[] morseLetters = splitWords[i].split(" ");
			for (int j = 0; j < morseLetters.length; j++) {
				realTextBuilder.append(decoder.decodeLetter(morseLetters[j], realTextBuilder));
			}
			realTextBuilder.append(" ");
		}
		return realTextBuilder.toString().toUpperCase().trim();
	}

	/*
	 * Returns the mapping of encodings from each character to its morse code representation.
	 */
	public static Map<Character, String> getEncodingMap() {
		return encoder;
	}

	/*
	 * Returns the root node of the binary tree used to decode a code string containing 
	   DOTs, DASHes, SLASHes, and space characters to its character representation
	   To ensure consistency, the right children represent DOTs, the left children DASHes.
	 */
	public static TreeNode<Character> getDecodingTree() {
		return decoder;
	}
}
