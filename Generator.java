import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Generator {
	
	public static final String[] UPPER_CASE = {
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	public static final String[] LOWER_CASE = {
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	public static final String[] LETTERS = {
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c",
		"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	public static final String[] NUMBERS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	public static final String[] LETTERS_NUMBERS = {
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c",
		"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9"};
	public static final String[] EVERYTHING = {
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c",
		"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "`", "~", ",", ".", "<", ">", "?", "/", ":", ";", "'", "{", "}", "[", "]",
		"|", " ", "=", "+", "-", "_", "\\", "\""};
	
	public static void generateFile(String filePath, String[] chars, int passcodeLengthStart, int passcodeLengthLimit) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(new File(filePath));
		
		for(int passcodeIndex = passcodeLengthStart; passcodeIndex <= passcodeLengthLimit; passcodeIndex++){
			
			System.out.println("Starting passwords of length: " + passcodeIndex);
			
			Integer[] index = new Integer[passcodeIndex];
			
			for(int i = 0; i < index.length; i++){
				index[i] = 0;
			}
			
			boolean done = false;
			String password = "";
			do{
				//checks if the job is done
				//write the password to file
				done = true;
				password = "";
				for(int i = 0; i < index.length; i++){
					if(index[i] != chars.length - 1){
						done = false;
					}
					
					password += chars[index[i]];
				}
				writer.println(password);
				
				//increse by one
				index[index.length - 1] += 1;
				
				//do carrying
				for(int i = index.length - 1; i >= 0; i--){
					if(index[i] >= chars.length){
						if(i != 0){
							if(index.length > 4 && i <= index.length - 4){
								System.out.println(String.format("%.3f", (index[0] / (float) (chars.length)) * 100) + "%");
							}
							index[i] = 0;
							index[i - 1] += 1;
						}else{
							//Almost done!
							break;
						}
					}
				}
			}while(!done);
			System.out.println("Finished passwords of length: " + passcodeIndex);
		}
		
		writer.close();
	}
	
	public static void smartGenerateFiles(String saveFilePath, String wordsFilePath, int passcodeLengthStart, int passcodeLengthEnd) throws FileNotFoundException{
		File wordsFile = new File(wordsFilePath);
		Scanner scanner = new Scanner(wordsFile);
		String[] completeWords = new String[(int) (wordsFile.length())];
		
		int numInvalid = 0; //the number of words that are too long to fit in the passcode length
		String word = "";
		
		for(int i = 0; scanner.hasNext(); i++){
			word = scanner.next();
			if(word.length() <= passcodeLengthEnd){
				completeWords[i] = word;
			}else{
				numInvalid++;
				i--;
			}
		}
		
		String[] words = new String[(int) (wordsFile.length() - numInvalid)];
		for(int i = 0, j = 0; j < words.length; i++){
			if(completeWords[i].equals("")){
				continue;
			}else{
				words[j] = completeWords[i];
				j++;
			}
		}
		
		PrintWriter writer = new PrintWriter(saveFilePath);
		
		for(int i = 0; i < words.length; i++){
			String[] wordRandomness = computeWordRandomness(words[i], passcodeLengthStart, passcodeLengthEnd);
			for(int j = 0; j < wordRandomness.length; j++){
				writer.println(wordRandomness[j]);				
			}
		}
		writer.close();
	}
	
	public static String[] computeWordRandomness(String word, int shortestLength, int longestLength){
		ArrayList<String> randomness = new ArrayList<String>();
		
		//TODO do stuff
		
		return (String[])(randomness.toArray());
	}
}
