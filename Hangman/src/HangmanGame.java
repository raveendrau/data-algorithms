import java.io.*;
import java.util.*;

/**
 * You write code here to play a game of Hangman.
 * Some sample code provided at the start. You'll probably remove almost 
 * all of it (readString might stick around).
 * 
 * @author kl78
 */

public class HangmanGame {

	// Used for reading data from the console.
	Scanner myInput;
	
	public HangmanGame() {
		// Set up our read-from-console.
		myInput = new Scanner(
				new BufferedReader(new InputStreamReader(System.in)));
	}
	
	
	/**
	 * Get a line from the user and return the line as a String.
	 * 
	 * @param prompt is printed as an instruction to the user
	 * @return entire line entered by the user
	 */
	public String readString(String prompt) {
		System.out.printf("%s ", prompt);
		String entered = myInput.nextLine();
		return entered;
	}
	
	/**
	 * Play one game of Hangman. This should prompt
	 * user for parameters and then play a complete game.
	 * You'll likely want to call other functions from this one. 
	 * The existing code may provide some helpful examples.
	 */

	/**
	 * [Random](http://docs.oracle.com/javase/7/docs/api/java/util/Random.html)
	 */
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public void play() {
		
		 HangmanFileLoader data = new HangmanFileLoader();
		 data.readFile("lowerwords.txt");
		 
		 /**
		  * Generate number of guesses.
		  * Generate length of word and pull a word of that length.
		  */
		 int guesses = randInt(8, 12);
		 int wordLength = randInt(4, 6);
		 String secretWord = data.getRandomWord(wordLength);
		 
		 try {
			Thread.currentThread();
//			System.out.println("I am Hangman. Starting on my count.");
//			Thread.sleep(1000); // value in milliseconds
//			System.out.print("One. ");
//			Thread.sleep(1000); 
//			System.out.print("Two. ");
//			Thread.sleep(1000); 
//			System.out.print("Three. ");
//			Thread.sleep(1000); 
//			System.out.print("Four. ");
//			Thread.sleep(1000); 
//			System.out.print("Five.");
//			Thread.sleep(1000); 
//		 	System.out.println("");
//		 	System.out.println("How many guesses would you like to have in the game?");
//			Thread.sleep(3000); 
//			System.out.println("Too bad, I am going to decide from a value of 1-10.");
//			Thread.sleep(2000);
//			System.out.println("Rolling my electronic die!");
//			Thread.sleep(1000);
			System.out.println("You are given "+guesses+" guesses!");
			Thread.sleep(500);
			System.out.println("How long would you like the word you are guessing to be?");
			Thread.sleep(500);
			System.out.println("Don't you learn? I decide!");
			Thread.sleep(500);
			System.out.println("Done! You get a "+wordLength+" letter word!");
			Thread.sleep(500);
			System.out.println("Duke Honor Code! Don't look.");
			Thread.sleep(500);
			System.out.println("For debugging, the word is "+secretWord);
		 } 
		 
		 /* Without this catch block, the try method does not seem to work */
		 catch (InterruptedException ie) {
		 }
		 
		 boolean gameWon = false; 
		 
		 /* Store the letters used throughout game */
		 HashSet<Character> letterSet = new HashSet<Character>();
			
		 for (int k = 0; k < guesses; k += 1) {
			 String guess = readString("What's the secret word (Press 'Enter' to skip):");
			 if(guess.equals(secretWord)) {
				 System.out.println("You guessed my word!");
				 gameWon = true;
				 break;
			 } 
			 else {
				
				 /* Generate list of letters attempted and inform user */
				 System.out.println("");
				 System.out.print("Letters used: ");
				 for (Character x: letterSet) {
					System.out.print(x+" ");
					}

				 /* Convert String array to char array */
//				 char[] letterChar; 
//				 letterChar = new char[letterStr.length]; 
//				 for (int i=0;i<letterStr.length;i++) {
//					letterChar[i] = letterStr[i].charAt(0);
//				 }

				 /* Convert char array to a set */
//				 Set<Character> letterCharSet = new HashSet(Arrays.asList(letterChar));
//				 System.out.println("These are in the character set");
//				 System.out.println(letterCharSet);
				 	
				 /* Make representation of secret word */ 
				 System.out.println("");
				 for (int i = 0; i < secretWord.length(); i++) {
					 Character secretWordChar = secretWord.charAt(i);
					 boolean contains = false;
					 if (letterSet.contains(secretWordChar)) {
						 contains = true;
						 System.out.print(secretWordChar);
					 }
					 if (!contains) {
						 System.out.print(" - ");
					 }
				 }
				 
				/* Get number of guesses and inform user */
				System.out.println("");
				System.out.println("");
				int guessesRemain = guesses - k;
				System.out.println("You have **"+guessesRemain+"** guesses remaining.");

								   
				
				
				System.out.println("");
				String input = readString("Guess a letter:"); 
				Character c = input.charAt(0);
				
				letterSet.add(c);
				System.out.println("");
				 
				
			 }
		 }
		 
		 if (!gameWon) {
			 System.out.println("You lost, secret word was " + secretWord);
		 }	 
	}
}
