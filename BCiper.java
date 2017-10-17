
/*
The program asks users to input a string of variable length. Then, it converts each letter to the index in the alphabetic list (1-26). 
Each index is then represented by a 8-bit binary block. The encryption process basically reverse the bits and then flip the last bit, for all blocks. 
Then, the scrambler processes 8 blocks at a time by reversing the blocks. If there are less than 8 blocks left, then reversing operation is still 
performed with the size of the blocks. Repeat this process for 10 times. Same in Lab1, the program prints out the ciphertext, then performs decryption 
and finally prints out the plaintext to verify everything works perfectly.
*/





package com.Infosecurity.main;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BCipher{

public static void main(String[] args) throws IOException {
	
	System.out.println("Press ENTER after your MESSAGE & then enter number of ITERATION:");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String RealText = br.readLine();
	int iteration = Integer.valueOf(br.readLine());
	
	
	String[] BlocksEncrypted = operation.getquick().encrypt(RealText, iteration);

	StringBuffer BlocksEncryptedBuffer = new StringBuffer();
	for (String encryptedBlock : BlocksEncrypted) {
		BlocksEncryptedBuffer.append(encryptedBlock);
		BlocksEncryptedBuffer.append("\t");
		
		
	}
	System.out.println("Your Encrypted Message is:");
	System.out.println(RealText);

	System.out.println("Your Encrypted Message in Block of 8 bits are:");
	System.out.println(BlocksEncryptedBuffer.toString());

	String MessageDecrypted = operation.getquick().decrypt(BlocksEncrypted, iteration);

	System.out.println("Your Decrypted Message is:");
	System.out.println(MessageDecrypted);
}



public static class operation {


	private static final int ASCII = 96;
	private static final int BSize = 8;

	private static operation quick;

	public int[] Str_To_Int(String message) {
		int[] result = new int[message.length()];
		for (int i = 0; i < result.length; i++) {
			result[i] = Character.toLowerCase(message.charAt(i)) - ASCII;
		}
		return result;
	}
	public static operation getquick() {
		if (quick == null) {
			quick = new operation();
		}
		return quick;
	}

	
	public String[] Binary_8Bit(int[] indexes) {
		String[] result = new String[indexes.length];
		for (int i = 0; i < indexes.length; i++) {
			result[i] = String.format("%8s", Integer.toBinaryString(indexes[i])).replace(' ', '0');
		}
		return result;
	}

	
	public String[] Bit_Rev(String[] blocks) {
		String[] result = new String[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			result[i] = new StringBuffer(blocks[i]).reverse().toString();
		}
		return result;
	}

	


	private String LastBit_Flip(String input) {
		char endChar = input.charAt(input.length() - 1);
		String result = input.substring(0, input.length() - 1);
		if (endChar == '1') {
			return result + '0';
		}
		return result + '1';
	}
	
	public String[] LastBit_Flip(String[] blocks) {
		String[] result = new String[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			result[i] = LastBit_Flip(blocks[i]);
		}
		return result;
	}

	public String[] Block_Change(String[] blocks) {
		String[] result = new String[blocks.length];
		for (int i = 0; i + BSize - 1 < blocks.length; i = i + BSize) {
			for (int j = 0; j < BSize; j++) {
				result[i + j] = blocks[i + BSize - j - 1];
			}
		}
		
		int division = blocks.length / BSize;
		int modulus = blocks.length % BSize;
		if (modulus != 0) {
			for (int i = 0; i < modulus; i++) {
				result[division * BSize + i] = blocks[division * BSize + modulus - i - 1];

			}
		}
		return result;
	}

	
	public String decrypt(String[] Binary_Encrypted, int iteration) {
		String[] BlocksIn = Binary_Encrypted;
		for (int i = 0; i < iteration; i++) {
			String[] Blocks_Swapped = Block_Change(BlocksIn);
			String[] EndBit_Flipped = LastBit_Flip(Blocks_Swapped);
			BlocksIn = Bit_Rev(EndBit_Flipped);
		}
		return Bits_To_Str(BlocksIn);
	}
	
	private String Bits_To_Str(String[] blocks) {
		StringBuffer result = new StringBuffer();
		for (String block : blocks) {
			result.append(Character.toChars(Integer.parseInt(block, 2) + ASCII));
		}
		return result.toString();
	}

	public String[] encrypt(String message, int iteration) {
		int[] intBlocks = Str_To_Int(message);
		String[] _8BitBlocks = Binary_8Bit(intBlocks);
		for (int i = 0; i < iteration; i++) {
			String[] Block_Reversed = Bit_Rev(_8BitBlocks);
			String[] EndBit_Flipped = LastBit_Flip(Block_Reversed);
			_8BitBlocks = Block_Change(EndBit_Flipped);
		}
		return _8BitBlocks;
	}	
}

}

