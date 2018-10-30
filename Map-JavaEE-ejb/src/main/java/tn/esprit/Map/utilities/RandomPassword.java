package tn.esprit.Map.utilities;

import java.util.Random;

public class RandomPassword {
	public String generateRandomPassword()
	{
		String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String randomString = "";
		int length = 8;
		Random rand = new Random();
		char[] text = new char[length];
		//rand.nextInt(characters.length()) --> index of the array
		//a chaque valeur du tableau =: random value
		for(int i=0;i<length;i++)
		{
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}
		for(int i=0;i<text.length;i++)
		{
			randomString += text[i];
		}
		return randomString;
	}

}
