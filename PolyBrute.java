



package com.Infosecurity.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.commons.lang3.mutable.MutableInt;

import com.security.util.OperationType;


public class PolyBrute {

	private static final int[] KEY_PATTERN = new int[] { 5, 19 };

	public static void main(String[] args) throws IOException {

		// Encryption
		System.out.println("Enter your message: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String plainText = br.readLine();

		System.out.println("Message:\t\t" + plainText);

		String encryptedMessage = PolyAlphabeticUtils.getInstance().encrypt(plainText, KEY_PATTERN);
		System.out.println("Encrypted Message:\t" + encryptedMessage);

		// Decryption
		String decryptedMessage = PolyAlphabeticUtils.getInstance().decrypt(encryptedMessage, KEY_PATTERN);
		System.out.println("Decrypted Message:\t" + decryptedMessage);
		
		
		//Bruteforce Decryption
		int[] KEY_BRUTEFORCE;		
		System.out.println("Bruteforcing on keys from 1 to 26");
		for(int i=1;i<=26;i++) {
			for(int j=1;j<=26;j++) {
				KEY_BRUTEFORCE =new int[] {i,j};
				String bdecryptedMessage = PolyAlphabeticUtils.getInstance().decrypt(encryptedMessage, KEY_BRUTEFORCE);
				if(bdecryptedMessage.equals(plainText)) {
					System.out.println("Brutforcing Successfull");
					System.out.println("Key Pair is: " + Arrays.toString(KEY_BRUTEFORCE));
				}
			}
		}
	}
	public static class PolyAlphabeticUtils {


		private static PolyAlphabeticUtils instance;
		private static int MIN_ASCII_CODE = 97; // a
		private static int MAX_ASCII_CODE = 122; // z

		
		private PolyAlphabeticUtils() {
		}

		
		public static PolyAlphabeticUtils getInstance() {
			if (instance == null) {
				instance = new PolyAlphabeticUtils();
			}
			return instance;
		}

		
		public String encrypt(String plainText, int... pattern) {
			return convert(plainText, OperationType.ENCRYPT, pattern);
		}

		private String convert(String plainText, OperationType type, int... pattern) {
			String result = "";
			MutableInt patternIndex = new MutableInt(0);
			for (char chr : plainText.toCharArray()) {
				char encryptedCharacter = chr;
				if (Character.isAlphabetic(chr) && Character.isLowerCase(chr)) {
					encryptedCharacter = encryptCharacter(chr, type, patternIndex, pattern);
				}
				result += encryptedCharacter;
			}
			return result;
		}

		
		public char encryptCharacter(char chr, OperationType type, MutableInt patternIndex, int... patterns) {
			if (patternIndex.getValue() >= patterns.length) {
				patternIndex.setValue(0);
			}
			Integer key = patterns[patternIndex.getValue()];
			patternIndex.setValue(patternIndex.getValue() + 1);
			return convert(chr, type, key);
		}


		public char convert(char chr, OperationType type, Integer key) {
			switch (type) {
			case DECRYPT: {
				int newCharAscii = (int) chr - key;
				if (newCharAscii < MIN_ASCII_CODE) {
					newCharAscii = MAX_ASCII_CODE - (MIN_ASCII_CODE - newCharAscii) + 1;
				}
				return (char) newCharAscii;
			}
			case ENCRYPT: {
				int newCharAscii = (int) chr + key;
				if (newCharAscii > MAX_ASCII_CODE) {
					newCharAscii = MIN_ASCII_CODE + (newCharAscii - MAX_ASCII_CODE) - 1;
				}
				return (char) newCharAscii;
			}
			}
			return 0;
		}


		public String decrypt(String encryptedMessage, int[] keyPattern) {
			return convert(encryptedMessage, OperationType.DECRYPT, keyPattern);
		}
	
	
}

}