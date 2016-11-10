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
		//This initializes the upper-case letters that are inside of the 'stringArray'
		int uppercaseCount = countUpperCaseLetters(stringArray);
		
		String doubleArray[][] = CountWordFrequency(stringArrayList);

		
	}//Closes Main Method
	
	public static String[] readInputFile (String inputFileName) throws IOException {
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
	}
	
	public static int CountWords(String [] stringArray) {
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
	
	public static  String [][] CountWordFrequency(ArrayList<String>stringArrayList) {
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
	}

}

