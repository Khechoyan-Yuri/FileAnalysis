import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class file_analysis {

	public static void main(String[] args) throws IOException {
		int num_of_lines = 0;
		String inputFileName;
		Scanner input = new Scanner(System.in);
		System.out.print("Please type in the name of the input file: ");
		inputFileName = input.nextLine();
		input.close();
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
		
		int wordCount = 0;
		int sentenceCount = 0;
		int alphabetCount = 0;
		int punctuationCount = 0;
		int digitCount = 0;
		int whitespaceCount = 0;
		int uppercaseCount = 0;
		for(i = 0; i<stringArray.length; i++) {
			String [] wordArray = stringArray[i].split("[, . ; \\s]+");
			wordCount +=wordArray.length;
			String [] sentenceArray = stringArray[i].split("[. ; ]+");
			sentenceCount +=sentenceArray.length;
			for(int j =0; j<stringArray[i].length(); j++) {
				if(Character.isLetter(stringArray[i].charAt(j))) {
					alphabetCount++;
				}
				else if(stringArray[i].charAt(j) == ',' ||stringArray[i].charAt(j) == ';'
						|| stringArray[i].charAt(j) == '.') {
					punctuationCount++;
				}
				else if(Character.isDigit(stringArray[i].charAt(j))) {
					digitCount++;
				}
				else if(stringArray[i].charAt(j) == ' ') {
					whitespaceCount++;
				}
				else if(Character.isUpperCase(stringArray[i].charAt(j))) {
					uppercaseCount++;
				}
				
			}
					
			
		}
		
		ArrayList<String> stringArrayList = new ArrayList<String>();
		
		for(i=0; i<stringArray.length; i++) {
			String [] wordArray = stringArray[i].toLowerCase().split("[,;.\\s]+");
			for(int j=0; j<wordArray.length; j++) {
				stringArrayList.add(wordArray[j]);
			}
		}
		
		String doubleArray[][] = new String [stringArrayList.size()][2];
		int frequencyCount = 0;
		boolean ifDoesNotExist = true;
		int place=0;
		
		for(i=0; i<stringArrayList.size(); i++) {
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
		
		for(i=0; i<stringArrayList.size(); i++) {
			for(int j=0; j<doubleArray.length; j++) {
				if(doubleArray[i][0].equals(stringArrayList.get(j))) {
					frequencyCount++;
				}
			}
			doubleArray[i][1] = Integer.toString(frequencyCount++);
			frequencyCount =0;
		}
			String[] trackWord = new String [100];
			
		
		for(i =0; i<doubleArray.length; i++) {
			for(int j = 0; j< trackWord.length; j++){
				if(trackWord[i] != null && trackWord[i].equals(stringArrayList.get(j))) {
					ifDoesNotExist = false;
			}
			
			}
			System.out.println(doubleArray[i][0]+": "+doubleArray[i][1]);
		}
		
	}
}
