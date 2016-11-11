/* ========================================================================== */

/*	PROGRAM File Analysis
    AUTHOR: Yuri Khechoyan & Jessie Wilkins
    COURSE NUMBER: CIS 210
    COURSE SECTION NUMBER: 01
    INSTRUCTOR NAME: Dr. Tian
    PROJECT NUMBER: 9
    DUE DATE: 11/10/2016

    

SUMMARY

	This program is designed to (first) read in a text document.
	It will ask the user to enter the name of the document that
	the user wishes to import. 
	If the file does not exist, program will give the user an error and then
	terminate itself. If the file that user imports does exist, program will
	proceed to accomplish the following:
		
	The list of tasks that this program is designed to do:
	
	Parse out:
		. (periods)
		, (commas)
		; (semicolons)
		---- end.___Then ---- (white-spaces) - 2x at the end of sentences
	
	1. Read in the content of the file. 
	2. Calculate the number of words.
	3. Calculate the number of sentences. 
	4. Calculate the number of alphabet characters. 
	5. Calculate the number of punctuation characters. 
	6. Calculate the number of digit characters. 
	7. Calculate the number of white-spaces (including the double white-spaces after each sentence).
	8. Calculate the number of words that start with upper-case letter.
	9. Calculate the frequency of words. 
    10. Output the list of words & their frequencies in alphabetical order
	

INPUT

	The input for this program will be a text file appropriately titled:
	
	--'input.txt'--

	In order for this program to work, it must read in the file. If reading in the file fails,
	the program will throw an error to the user and self-terminate.

OUTPUT

	The output for this program will be all of the calculations that were made. 
	The program will print the output the words used in the file to the console
	in alphabetical order.
	
	Here is an example of how the output of the program would look:

	Total number of lines in the files: 8 
	Total number of words in the files: 86 
	Total number of sentences in the files: 6 
	Total number of punctuation in the files: 15 
	Total number of alphabets in the files: 448 
	Total number of digits in the files: 0 
	Total number of white-spaces in the files: 81 
	Total number of upper-case in the files: 39 
	------------------------------------------------------- 
	a: 1 
	achieve: 1 
	acronym: 1 
	an: 1 
	and: 2 
	are: 1 
	arrange: 1 
	at: 1 
	begin: 1 
	can: 1 
	code: 1 
	college: 1 
	confined: 1 
	debugging: 1 
	designed: 1 
	developed: 1 
	do: 1 
	end: 1 
	environment: 1 
	error: 1 
	errors: 1 
	for: 2 
	frustrating: 1 
	hanover: 1 
	how: 1 
	idiot: 1 
	impossible: 1 
	in: 3 
	is: 2 
	it: 2 
	language: 2 
	languages: 1 
	make: 2 
	matter: 1 
	misfits: 1 
	monopurpose: 1 
	no: 1 
	not: 1 
	nothing: 1 
	of: 2 
	other: 1 
	process: 1 
	programming: 1 
	programs: 2 
	results: 1 
	sheer: 1 
	simple: 2 
	statements: 2 
	stop: 1 
	syntax: 1 
	technological: 1 
	tedious: 1 
	testing: 1 
	the: 5 
	therefore: 1 
	they: 1 
	this: 1 
	thus: 1 
	to: 3 
	useful: 1 
	was: 1 
	with: 1 
	without: 1
	write: 1 
	written: 2 
	you: 2 
 

ASSUMPTIONS
- None

*/

//*******************************
//*START OF file_analysis CLASS	*
//*******************************

import java.util.Scanner;					//Imports Scanner Utility
import java.io.*;							//Imports IO Utility
import java.util.ArrayList;					//Imports ArrayList Utility
import java.util.Arrays;					//Imports Arrays Utility

public class file_analysis {

	public static void main(String[] args) throws IOException {
		//This initializes an 'inputFileName' String
		String inputFileName;
		//This initiates a scanner object for reading in the text file
		Scanner input = new Scanner(System.in);
		//This asks user for the name of the text file that will be imported
		System.out.print("Please type in the name of the input file: ");
		//This reads in the file
		inputFileName = input.nextLine();
		//Destroys the scanner object after file import - scanner is no longer needed
		input.close();
		//This creates a String Array for the readInputFile Method
		String stringArray[] = readInputFile(inputFileName);
		//Creates a String Array List
		ArrayList<String> stringArrayList = new ArrayList<String>();
		//The 'wordArray' String Array counts the amount of words used in program
		//It splits the words based on on: , ; . and (white-space)
		for(int i=0; i<stringArray.length; i++) {
			String [] wordArray = stringArray[i].toLowerCase().split("[,;.\\s]+");
			for(int j=0; j<wordArray.length; j++) {
				stringArrayList.add(wordArray[j]);
			}
		}
		//This stores the upper-case letters inside of the 'stringArray' to the uppercaseCount var.
		//calls countUpperCaseLetters method
		int uppercaseCount = countUpperCaseLetters(stringArray);
		
		//This stores the word frequency inside of the 'stringArray' to the doubleArray var.
		//calls countWordFrequency method
		String doubleArray[][] = CountWordFrequency(stringArrayList);
		
		//This stores the word count inside of the 'stringArray' to the wordCounter var.
		//calls CountWords method
		int wordCounter = CountWords(stringArray);
		
		//This stores the digits inside of the 'stringArray' to the digitCounter var.
		//calls CountDigit method
		int digitCounter = CountDigits(stringArray);
		
		//This stores all of the letters inside of the 'stringArray' to the alphCounter var.
		//calls countLetters method
		int alphCounter = countLetters(stringArray);
		
		//This stores the number of sentences inside of the 'stringArray' to the sentenceCounter var.
		//calls countSentences method
		int sentenceCounter = countSentences(stringArray);
		
		//This stores the punctuation count inside of the 'stringArray' to the punctCounter var.
		//calls countPunctuation method
		int punctCounter = countPunctuation(stringArray);
		
		//This stores the number of white-spaces inside of the 'stringArray' to the whitespaceCounter var.
		//calls countWhitespece method
		int whitespaceCounter = countWhitespace(stringArray);
		
		//This stores the number of lines inside of the 'stringArray' to the lineCounter var.
		//calls countLines method
		int lineCounter = countLines(stringArray, inputFileName);
		
		//This stores all of the data that was stored into vars above, into a single var with those exact parameters
		//calls outputData method
		outputData(uppercaseCount, doubleArray, stringArray, wordCounter, digitCounter, alphCounter, sentenceCounter, punctCounter, whitespaceCounter, lineCounter);
	
	
	}//***********Closes Main Method**************
	
	
	//This outputData method is used to print all of the information gathered, to the console
	public static void outputData(int uppercaseCount,  String doubleArray[][], String [] stringArray, int wordCounter, int digitCounter, int alphCounter, int sentenceCounter, int punctCounter, int whitespaceCounter, int lineCounter){
		
		//This for loop makes sure that while the counter is less than the length of the stringArray, 
		//to print the length of what is in the document
		for(int i=0; i < stringArray.length; i++){
		//This prints out the full document to the console
			System.out.println(stringArray[i]);
		}
		
		//*******************************
		//	ALL	OUTPUTS TO CONSOLE		*
		//*******************************
		
		
		System.out.println("-----------------------------------------------------------");
		
		System.out.println("Total number of lines in the files: " +lineCounter);
		System.out.println("Total number of words in the files: " +wordCounter);
		System.out.println("Total number of sentences in the files: " +sentenceCounter);
		System.out.println("Total number of punctuations in the files: " +punctCounter);
		System.out.println("Total number of alphabets in the files: " +alphCounter);
		System.out.println("Total number of digits in the files: " +digitCounter);
		System.out.println("Total number of whitespaces in the files: " +whitespaceCounter);
		System.out.println("Total number of uppercase in the files: " +uppercaseCount);
		
		System.out.println("-----------------------------------------------------------");
		
		//This for loop will print out the frequency of words to the console
		//while the counter is less than the length of the doubleArray
		for(int i=0; i < doubleArray.length; i++){
			System.out.println(doubleArray[i][0]+": "+doubleArray[i][1]);
		}
	
	}//Closes outputData method
	
	//readInputFile is designed to only read i the information from the file that is imported
	public static String[] readInputFile (String inputFileName) throws IOException {
		int num_of_lines = 0;
		
		//This creates the scanner to read in the data from the file
		File f = new File(inputFileName);
		Scanner fileCount = new Scanner(f);
		
		//while there is a next line of text, 
		//count that towards the total number of lines in the file
		while(fileCount.hasNextLine()) {
			fileCount.nextLine();
			num_of_lines++;
		}
		//Close the fileCount object
		fileCount.close();
		
		//This scanner reads in the num_of_lines from fileCount and stores it into a string array
		Scanner fileInput = new Scanner(f);
		String [] stringArray = new String[num_of_lines];
		int i = 0;
		
		//This increments the String Array while it has more lines of data that need to be read
		while(fileInput.hasNextLine()) {
			stringArray[i] = fileInput.nextLine();
			i++;
		}
		//Closes the fileInput object
		fileInput.close();
		
		return stringArray;
	}//Closes readInputFile method
	
	//countLines method counts the amount of lines that were 
	//in the file (that were stored into num_of_lines
	public static int countLines(String [] stringArray, String inputFileName) throws IOException{
			int num_of_lines = 0;
			
			File f = new File(inputFileName);
			Scanner fileCount = new Scanner(f);
			
			while(fileCount.hasNextLine()) {
				fileCount.nextLine();
				num_of_lines++;
			}
			fileCount.close();
			
			return num_of_lines;
	}//Closes countLines method
	
	//CountDigits is designed to count the number of digits that were inside of the imported file
	public static int CountDigits(String [] stringArray){
		int digitCount = 0;
		for(int i =0; i<stringArray.length; i++) {
			for(int j =0; j<stringArray[i].length(); j++) {
				if(Character.isDigit(stringArray[i].charAt(j))) {
					digitCount++;
				}
			}
		}
		return digitCount;
	}//Closes CountDigits method
	
	//CountWords method is used to count the amount of words in the the file
	//words are separated by the comma, period, and semi-colon
	public static int CountWords(String [] stringArray) {
		int wordCount =0;
		for(int i=0; i<stringArray.length; i++) {
			String [] wordArray = stringArray[i].split("[, . ; \\s]+");
			wordCount +=wordArray.length;
		}
		return wordCount;
	}//Closes CountWords method
	
	// countSentences method is used to the amount of sentences there are in the imported file
	//number of sentences will be determined by the ending punctuation
	public static int countSentences(String [] stringArray) {
		int sentenceCount = 0;
		for(int i=0; i<stringArray.length; i++) {
			String [] sentenceArray = stringArray[i].split("[. ; ]+");
			sentenceCount +=sentenceArray.length;
		}
		return sentenceCount;
	}//Closes countSetences method
	
	//countLetters method is used to determine the amount of letters that are in the imported file
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
	}//Closes countLetters method
	
	//countPunctuation method will be used to calculate the number of punctuation
	//if he letters equal to , or . or ; - they are considered punctuation
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
	}//Closes countPunctuation method
	
	//This method is used to count the white-spaces in between words
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
	//This method is used to count the amount of upper case letters
	public static int countUpperCaseLetters(String [] stringArray) {
		int uppercaseCount = 0;
		for(int i = 0; i<stringArray.length; i++) {
			for(int j =0; j<stringArray[i].length(); j++) {
				if(((j -1 >=0 && stringArray[i].charAt(j-1) == ' ') || (j==0))  && Character.isUpperCase(stringArray[i].charAt(j))) {
					uppercaseCount++;
				}
			}
		}

		return uppercaseCount;
	}//Closes countUpperCaseLetters method
	
	//This final method is used to count the frequency of the words that are inside of the imported file
	public static  String [][] CountWordFrequency(ArrayList<String>stringArrayList) {
		String doubleArray[][] = new String [stringArrayList.size()][2];
		int frequencyCount = 0;
		boolean ifDoesNotExist = true;
		int place=0;
		//if the size of the stringArray is greater than 'i' AND the frequency of the word is not null,
		//then the letter already exists in the imported file
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
		//if the stringArray and double array are both greater than the counters, 
		//then add 1 to the amount of times the word appears
		for(int i=0; i<stringArrayList.size(); i++) {
			for(int j=0; j<doubleArray.length; j++) {
				if(doubleArray[i][0].equals(stringArrayList.get(j))) {
					frequencyCount++;
				}
			}
			doubleArray[i][1] = Integer.toString(frequencyCount++);
			frequencyCount =0;
		}	
		
		String [] wordArray = new String [doubleArray.length];
		String [] numberArray = new String [doubleArray.length];
		
		for(int i=0; i<numberArray.length; i++){
			wordArray[i] = doubleArray[i][0];
			numberArray[i] = doubleArray[i][1];
		}
		
		Arrays.sort(wordArray);
		Arrays.sort(numberArray);
		
		String [][] returnArray = new String[numberArray.length][2];
		place = 0;
		for(int i =0; i<returnArray.length; i++) {
			if((i-1>=0 && !(wordArray[i].equals(wordArray[i-1])))|| i==0) {
				returnArray[place][0] = wordArray[i];
				returnArray[place][1] = numberArray[i];
				place++;
			}
		}
		
		return returnArray;
	}//Closes CountWordFrequency method

}//Closes file_analysis Class

