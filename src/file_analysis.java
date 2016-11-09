import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class file_analysis {

	public static void main(String[] args) throws IOException {
		String inputFileName;
		Scanner input = new Scanner(System.in);
		System.out.print("Please type in the name of the input file: ");
		inputFileName = input.nextLine();
		input.close();
		
		String stringArray[] = ReadInputFile(inputFileName);
		
		ArrayList<String> stringArrayList = new ArrayList<String>();
		
		for(int i=0; i<stringArray.length; i++) {
			String [] wordArray = stringArray[i].toLowerCase().split("[,;.\\s]+");
			for(int j=0; j<wordArray.length; j++) {
				stringArrayList.add(wordArray[j]);
			}
		}
		
		int uppercaseCount = countUpperCaseLetters(stringArray);
		
		String doubleArray[][] = new String [stringArrayList.size()][2];
		int frequencyCount = 0;
		boolean ifDoesNotExist = true;
		int place=0;
		
		for(int i=0; i<stringArrayList.size(); i++) {
			for(int j=0; j<doubleArray[i].length; j++) {
				if(doubleArray[i][0] != null && doubleArray[i][0].equals(stringArrayList.get(j))) {
					ifDoesNotExist = false;
				}
			}
			
			if(ifDoesNotExist) {
				doubleArray[place][0] = stringArrayList.get(i);
				place++;
			}
			
			ifDoesNotExist = true;
			
		}
		
		for(int i=0; i<stringArrayList.size(); i++) {
			for(int j=0; j<doubleArray.length; j++) {
				if(doubleArray[i][0].equals(stringArrayList.get(j))) {
					frequencyCount++;
				}
			}
			doubleArray[i][1] = Integer.toString(frequencyCount++);
			frequencyCount =0;
		}
			String[] trackWord = new String [100];
			
		
		for(int i =0; i<doubleArray.length; i++) {
			for(int j = 0; j< trackWord.length; j++){
				if(trackWord[i] != null && trackWord[i].equals(stringArrayList.get(j))) {
					ifDoesNotExist = false;
			}
			
			}
			//System.out.println(doubleArray[i][0]+": "+doubleArray[i][1]);
		}
		
	}
	
	public static String[] ReadInputFile (String inputFileName) throws IOException {
		int num_of_lines = 0;
		
		File f = new File(inputFileName);
		Scanner fileCount = new Scanner(f);
		
		while(fileCount.hasNextLine()) {
			fileCount.nextLine();
			num_of_lines++;
		}
		fileCount.close();
		
		Scanner fileInput = new Scanner(f);
		String [] stringArray = new String[num_of_lines];
		int i = 0;
		
		//incrementing the String Array
		while(fileInput.hasNextLine()) {
			stringArray[i] = fileInput.nextLine();
			i++;
		}
		fileInput.close();
		
		return stringArray;
	}
	
	public static int countDigits(String [] stringArray){
		int digitCount = 0;
		for(int i =0; i<stringArray.length; i++) {
			for(int j =0; j<stringArray[i].length(); j++) {
				if(Character.isDigit(stringArray[i].charAt(j))) {
					digitCount++;
				}
			}
		}
		return digitCount;
	}
	
	public static int countWords(String [] stringArray) {
		int wordCount =0;
		for(int i=0; i<stringArray.length; i++) {
			String [] wordArray = stringArray[i].split("[, . ; \\s]+");
			wordCount +=wordArray.length;
		}
		return wordCount;
	}
	
	public static int countSentences(String [] stringArray) {
		int sentenceCount = 0;
		for(int i=0; i<stringArray.length; i++) {
			String [] sentenceArray = stringArray[i].split("[. ; ]+");
			sentenceCount +=sentenceArray.length;
		}
		return sentenceCount;
	}
	
	public static int countLetters(String [] stringArray) {
		int letterCount = 0;
		for(int i=0; i<stringArray.length; i++) {
			for(int j=0; j<stringArray[i].length(); j++) {
				if(Character.isLetter(stringArray[i].charAt(j))) {
					letterCount++;
				}
			}
		}
		return letterCount;
	}
	
	public static int countPunctuation(String [] stringArray) {
		int punctuationCount = 0;
		for(int i=0; i<stringArray.length; i++) {
			for(int j=0; j<stringArray[i].length(); j++) {
				if(stringArray[i].charAt(j) == ',' ||stringArray[i].charAt(j) == ';'
						|| stringArray[i].charAt(j) == '.') {
						punctuationCount++;
				}
			}
		}
		return punctuationCount++;
	}
	
	public static int countWhitespace(String [] stringArray) {
		int whitespaceCount = 0;
		for(int i=0; i<stringArray.length; i++) {
			for(int j=0; j<stringArray[i].length(); j++) {
				if(stringArray[i].charAt(j) == ' ') {
					whitespaceCount++;
				}
			}
		}
		
		return whitespaceCount;
	}
	
	public static int countUpperCaseLetters(String [] stringArray) {
		int uppercaseCount = 0;
		for(int i = 0; i<stringArray.length; i++) {
			for(int j =0; j<stringArray[i].length(); j++) {
				if(((j -1 >=0 && stringArray[i].charAt(j-1) == ' ') || (j==0))  && Character.isUpperCase(stringArray[i].charAt(j))) {
					uppercaseCount++;
				}
			}
		}
		System.out.println(uppercaseCount);
		return uppercaseCount;
	}
}
