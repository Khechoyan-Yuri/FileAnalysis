import java.util.Scanner;
import java.io.*;

public class file_analysis {

	public static void main(String[] args) throws IOException {
		int num_of_lines = 0;
		String inputFileName;
		Scanner input = new Scanner(System.in);
		System.out.print("Please type in the name of the input file");
		inputFileName = input.nextLine();
		input.close();
		Scanner fileCount = new Scanner(inputFileName);
		
		while(fileCount.hasNextLine()) {
			fileCount.nextLine();
			num_of_lines++;
		}
		fileCount.close();
		
		Scanner fileInput = new Scanner(inputFileName);
		String [] stringArray = new String[num_of_lines];
		int i = 0;
		
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
			String [] wordArray = stringArray[i].split("[, .;]");
			wordCount +=wordArray.length;
			String [] sentenceArray = stringArray[i].split("[.;]");
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
		
		for(i=0; i<stringArray.length; i++) {
			for(int j = 0; j<stringArray[i].length(); j++) {
	
			}
		}
		
	}

}
