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
import java.util.Collections;				//Imports Collections Utility

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
		
		ArrayList<String> stringArrayList = convertToArrayList(stringArray);
		
		//This initializes the upper-case letters that are inside of the 'stringArray'
		int uppercaseCount = countUpperCaseLetters(stringArray);
		
		//This stores the word frequency inside of the 'stringArray' to the doubleArray var.
		//calls countWordFrequency method
		String wordFreqArray[][] = CountWordFrequency(stringArrayList);
		
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
		outputData(uppercaseCount, wordFreqArray, stringArray, wordCounter, digitCounter, alphCounter, sentenceCounter, punctCounter, whitespaceCounter, lineCounter);
	
	}//***********Closes Main Method**************
	
	/**
	 * Converts the string array into an array list that contains each word
	 * in each element.
	 * @param stringArray
	 * @return
	 */
	public static ArrayList<String> convertToArrayList(String stringArray[]) {
		//Creates a String Array List
		ArrayList<String> stringArrayList = new ArrayList<String>();
		//The 'wordArray' String Array counts the amount of words used in program
		//It splits the words based on : certain punctuations and (white-space).
		//It also splits contractions into two words each.
		for(int i=0; i<stringArray.length; i++) {
			//Splits the stringArray element into sub strings of string array based on the given characters
			String [] wordArray = stringArray[i].toLowerCase().split("[!?\\/*+=:,.;\\s]+");
			//While j is less than the word array's length
			for(int j=0; j<wordArray.length; j++) {		//j is set to 0 and incremented
				//if the word array contains "'t", then it splits it into two words:
				//first part of word and "not" and adds it to the array list
				if(wordArray[j].contains("'t")){
					String contractionWords [] = wordArray[j].split("[']");
					stringArrayList.add(contractionWords[0]);
					stringArrayList.add("not");
				}
				//if the word array contains "'s", then it splits it into two words:
				//first part of word and "is" and adds it to the array list
				else if(wordArray[j].contains("'s")) {
					String contractionWords [] = wordArray[j].split("[']");
					stringArrayList.add(contractionWords[0]);
					stringArrayList.add("is");
				}
				//if the word array contains "'re", then it splits it into two words:
				//first part of word and "are" and adds it to the array list
				else if(wordArray[j].contains("'re")) {
					String contractionWords [] = wordArray[j].split("[']");
					stringArrayList.add(contractionWords[0]);
					stringArrayList.add("are");
				}
				//if the word array contains "'m", then it splits it into two words:
				//first part of word and "am" and adds it to the array list
				else if(wordArray[j].contains("'m")) {
					String contractionWords [] = wordArray[j].split("[']");
					stringArrayList.add(contractionWords[0]);
					stringArrayList.add("am");
				}
				//if the word array contains "'ll", then it splits it into two words:
				//first part of word and "will" and adds it to the array list
				else if(wordArray[j].contains("'ll")) {
					String contractionWords [] = wordArray[j].split("[']");
					stringArrayList.add(contractionWords[0]);
					stringArrayList.add("will");
				}
				//if the word array contains "'d", then it splits it into two words:
				//first part of word and "would" and adds it to the array list
				else if(wordArray[j].contains("'d")) {
					String contractionWords [] = wordArray[j].split("[']");
					stringArrayList.add(contractionWords[0]);
					stringArrayList.add("would");
				}
				//if the word array contains "'ve", then it splits it into two words:
				//first part of word and "have" and adds it to the array list
				else if(wordArray[j].contains("'ve")) {
					String contractionWords [] = wordArray[j].split("[']");
					stringArrayList.add(contractionWords[0]);
					stringArrayList.add("have");
				}
				//if all other conditions fail, just add the word to the array list
				else {
					stringArrayList.add(wordArray[j]);
				}
			}//Close inner for loop
		}//Close outer for loop
		
		//Return the array list
		return stringArrayList;
	}
	
	/**
	 * This outputData method is used to print all 
	 * of the information gathered, to the console
	 * @param uppercaseCount
	 * @param doubleArray
	 * @param stringArray
	 * @param wordCounter
	 * @param digitCounter
	 * @param alphCounter
	 * @param sentenceCounter
	 * @param punctCounter
	 * @param whitespaceCounter
	 * @param lineCounter
	 */
	public static void outputData(int uppercaseCount,  String wordFreqArray[][], String [] stringArray, int wordCounter,
									int digitCounter, int alphCounter, int sentenceCounter, 
									int punctCounter, int whitespaceCounter, int lineCounter) throws IOException{
		//Ths creates an output.txt file for the data to be stored into
		PrintWriter output = new PrintWriter("output.txt");
		//This for loop makes sure that while the counter is less than the length of the stringArray, 
		//to print the length of what is in the document
		for(int i=0; i < stringArray.length; i++){
			//This prints out the full document to the console
			output.print(stringArray[i]+"\r\n");
		}
		
		//***************************
		//	ALL	OUTPUTS TO FILE		*
		//***************************
		
		output.print("-----------------------------------------------------------\r\n");
		
		output.print("Total number of lines in the files: " +lineCounter+"\r\n");
		output.print("Total number of words in the files: " +wordCounter+"\r\n");
		output.print("Total number of sentences in the files: " +sentenceCounter+"\r\n");
		output.print("Total number of punctuations in the files: " +punctCounter+"\r\n");
		output.print("Total number of alphabets in the files: " +alphCounter+"\r\n");
		output.print("Total number of digits in the files: " +digitCounter+"\r\n");
		output.print("Total number of whitespaces in the files: " +whitespaceCounter+"\r\n");
		output.print("Total number of uppercase in the files: " +uppercaseCount+"\r\n");
		
		output.print("-----------------------------------------------------------\r\n");
		
		//====================================================================================
		
		//This for loop will print out the frequency of words to the console
			//while the counter is less than the length of the doubleArray
		for(int i=0; i < wordFreqArray.length; i++){
			//if the element of the array is empty or null
			if(wordFreqArray[i][0] != null) {
				output.println(wordFreqArray[i][0]+": "+wordFreqArray[i][1]+"\r\n");
			}
		}
		output.close();
	}//Closes outputData method
	
	/**
	 * readInputFile is designed to only read 
	 * in the information from the file that is imported
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static String[] readInputFile (String inputFileName) throws IOException {
		//Declares and initializes the integer variable representing the number of lines
		int num_of_lines = 0;
		
		//This creates the scanner to read in the data from the file
		File f = new File(inputFileName);
		Scanner fileCount = new Scanner(f);
		
		//While there is a next line of text, 
		//count that towards the total number of lines in the file
		while(fileCount.hasNextLine()) {
			fileCount.nextLine();
			num_of_lines++;
		}
		//Close the fileCount object
		fileCount.close();
		
		//This scanner reads in the num_of_lines 
		//from fileCount and stores it into a string array
		Scanner fileInput = new Scanner(f);
		String [] stringArray = new String[num_of_lines];
		//This declares and initializes to zero the integer variable representing the counter
		int i = 0;
		
		//This increments the String Array while it 
		//has more lines of data that need to be read
		while(fileInput.hasNextLine()) {
			stringArray[i] = fileInput.nextLine();
			i++;
		}
		//Closes the fileInput object
		fileInput.close();
		
		return stringArray;
	}//Closes readInputFile method
	
	/**
	 * countLines method counts the amount of lines that were 
	 * in the file (that were stored into num_of_lines)
	 * @param stringArray
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static int countLines(String [] stringArray, String inputFileName) throws IOException{
			//Declares and initializes to zero the integer variable representing the number of lines
			int num_of_lines = 0;
			
			//Creates the input file to be used for counting the lines
			File f = new File(inputFileName);
			Scanner fileCount = new Scanner(f);
			
			//Counts the number of lines in a file while there is a next line
			while(fileCount.hasNextLine()) {
				//Read next line of file
				fileCount.nextLine();
				//Increment line counter
				num_of_lines++;
			}
			//Close the input file for counting
			fileCount.close();
			
			//Returns the number of lines
			return num_of_lines;
	}//Closes countLines method
	
	/**
	 * CountDigits is designed to count the number 
	 * of digits that were inside the imported file
	 * @param stringArray
	 * @return
	 */
	public static int CountDigits(String [] stringArray){
		//Declares and initializes to zero the integer variable that counts the digits
		int digitCount = 0;
		
		//Counts the number of digits while i is less than the stringArray's length
		for(int i =0; i<stringArray.length; i++) {	//i starts at zero and increments
			//While j is less than string array
			for(int j =0; j<stringArray[i].length(); j++) {	//j starts at zero and increments
				//if the character at the current element is a digit, increment the digit counter
				if(Character.isDigit(stringArray[i].charAt(j))) {
					digitCount++;
				}
			}
		}
		//Returns the number of digits
		return digitCount;
	}//Closes CountDigits method
	
	/**
	 * CountWords method is used to count the amount of words in the the file
	 * words are separated by the comma, period, semi-colon, and whitespace
	 * @param stringArray
	 * @return
	 */
	public static int CountWords(String [] stringArray) {
		int wordCount =0;
		for(int i=0; i<stringArray.length; i++) {
			String [] wordArray = stringArray[i].split("[!?\\/*+=:,.;'\\s]+");
			wordCount +=wordArray.length;
		}
		return wordCount;
	}//Closes CountWords method
	
	/**
	 * countSentences method is used to calculate the amount of sentences
 	 * that are in the imported file. Number of sentences
 	 * will be determined by the ending punctuation
	 * @param stringArray
	 * @return
	 */
	public static int countSentences(String [] stringArray) {
		int sentenceCount = 0;
		for(int i=0; i<stringArray.length; i++) {
			for(int j=0; j<stringArray[i].length(); j++) {
				if(stringArray[i].charAt(j) == '.') {
					sentenceCount++;
				}
			}
		}
		return sentenceCount++;
	}//Closes countSetences method
	
	/**
	 * countPunctuation method will be used to calculate the number of punctuation
	 * if he letters equal to , or . or ; - they are considered punctuation
	 * @param stringArray
	 * @return
	 */
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
	}//Closes countPunctuation method
	/**
  	 * countPunctuation method will be used to calculate the number of punctuation
  	 * based on given criteria for what is punctuation.
  	 * @param stringArray
  	 * @return
  	 */
	public static int countPunctuation(String [] stringArray) {
		String punctuation = new String(",;.'\"*()&^%$#@!|\\/:+=-_`~?");
		int punctuationCount = 0;
		for(int i=0; i<stringArray.length; i++) {
			
			for(int j=0; j<stringArray[i].length(); j++) {
				String punctHolder = Character.toString(stringArray[i].charAt(j));
				if(punctuation.contains(punctHolder)) {
						punctuationCount++;
				}
			}
		}
		return punctuationCount++;
	}
	
	/**
	 * This method is used to count the white-spaces in between words
	 * @param stringArray
	 * @return
	 */
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
	/**
	 * This method is used to count the amount of upper case letters
	 * @param stringArray
	 * @return
	 */
	public static int countUpperCaseLetters(String [] stringArray) {
		int uppercaseCount = 0;
		for(int i = 0; i<stringArray.length; i++) {
			for(int j =0; j<stringArray[i].length(); j++) {
				if(Character.isUpperCase(stringArray[i].charAt(j))) {
					uppercaseCount++;
				}
			}
		}

		return uppercaseCount;
	}//Closes countUpperCaseLetters method
	
	/**
	 * This final method is used to count the frequency of the words 
	 * that are inside of the imported file
	 * @param stringArrayList
	 * @return
	 */
	public static  String [][] CountWordFrequency(ArrayList<String>stringArrayList) {
		//
		Collections.sort(stringArrayList);
		String wordFreqArray[][] = new String [stringArrayList.size()][2];
		int frequencyCount = 0;
		int place=0;
		//while i is greater than the size of the stringArray 
		for(int i=0; i<stringArrayList.size(); i++) {	//i equals zero and increments
				//if the last element does not equal the current element or current element
				//location is equal to zero, then add to the word frequency array
				if((i-1 >= 0 && !(stringArrayList.get(i-1).equals(stringArrayList.get(i))) || i==0)) {
					wordFreqArray[place][0] = stringArrayList.get(i);
					//increment placeholder for array
					place++;
				}
		}
		
		//while i is greater than the size of the stringArray 
		for(int i=0; i<stringArrayList.size(); i++) {	//i equals zero and increments
			//while j is greater than the size of the wordFreqArray
			for(int j=0; j<wordFreqArray.length; j++) {	//j equals zero and increments
				//if the element of the wordFreqArray is not empty or null
				//and the current wordFreqArray element equals the string array list element,
				//increment the frequency count
				if(wordFreqArray[i][0] != null && wordFreqArray[i][0].equals(stringArrayList.get(j))) {
					frequencyCount++;
				}
			}
			//Add the frequency count to the second dimension of the 
			//wordFreqArray and reset to 0
			wordFreqArray[i][1] = Integer.toString(frequencyCount++);
			frequencyCount =0;
		}	
		
		//Return wordFreqArray
		return wordFreqArray;
	}//Closes CountWordFrequency method

}//Closes file_analysis Class
