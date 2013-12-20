import java.io.*;
import java.util.*;

/**
 * You write code here to play a game of Hangman. Some sample code provided at
 * the start. You'll probably remove almost all of it (readString might stick
 * around).
 * 
 * @author Xing Su
 */

public class HangmanGame {

	// Used for reading data from the console.
	Scanner myInput;

	public HangmanGame() {
		// Set up our read-from-console.
		myInput = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	}

	/**
	 * Get a line from the user and return the line as a String.
	 * 
	 * @param prompt
	 *            is printed as an instruction to the user
	 * @return entire line entered by the user
	 */
	public String readString(String prompt) {
		System.out.printf("%s ", prompt);
		String entered = myInput.nextLine();
		return entered;
	}

	/**
	 * Play one game of Hangman. This should prompt user for parameters and then
	 * play a complete game. You'll likely want to call other functions from
	 * this one. The existing code may provide some helpful examples.
	 */
	public void play() {
		HangmanFileLoader data = new HangmanFileLoader();
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("A", "movies");
		options.put("B", "countries");
		options.put("C", "lowerwords");
		
		String category = readString("# What category do you wanna guess from?\nA. Movies\nB. Countries\nC. Random\n");
		
		int letCount = 0; 
		int guessCount = 0; 
		String secretWord = "";
		
		if(options.get(category.toUpperCase())==null){
			category  = readString("Your choice is invalid, please choose again: \nA. Movies\nB. Countries\nC. Random\n");
		}else if(category.equalsIgnoreCase("c")){
			data.readFile(options.get(category.toUpperCase()).concat(".txt"));
			String input = readString("# How many letters in guess word:");
			letCount = Integer.parseInt(input);

			String input2 = readString("# How many guesses to hanging:");
			guessCount = Integer.parseInt(input2);
			
			secretWord = data.getRandomWord(letCount);
		}else{
			data.readFile(options.get(category.toUpperCase()).concat(".txt"));
			secretWord = data.getRandomWord();
			letCount = secretWord.length();
			if(letCount >= 10)
				guessCount = 8;
			else if(letCount >= 4)
				guessCount = letCount - 2;
			else
				guessCount = letCount;
		}


//		 The line below was used to test the game.
//		 System.out.printf("The %d letter secret word is %s\n", letCount,
//		 secretWord);

		String[] blank = new String[letCount];
		HashSet<Character> guess = new HashSet<Character>();
		
		for (int i = 0; i < letCount; i++) {
			blank[i] = "_";
			System.out.print(blank[i] + " ");
		}

		int missCount = guessCount;
		int k = 0;
		boolean game = false;
		while (k < guessCount) {
			System.out.print("\nmisses left: " + missCount
					+ "\nguesses so far: ");
			for (Character c : guess) {
				System.out.print(c + " ");
			}
			
			
			String letter = readString("\n# What letter do you want to guess:  ");
			
			while (guess.contains(letter.charAt(0)) || guess.contains(letter.toUpperCase().charAt(0)) || guess.contains(letter.toLowerCase().charAt(0))) {
				System.out.print("You have already guessed " + letter
						+ ", please guess again");
				letter = readString("\n# What letter do you want to guess:  ");
			}
			
			guess.add(letter.charAt(0));
			if (secretWord.indexOf(letter) >= 0 || secretWord.indexOf(letter.toUpperCase()) >= 0 || secretWord.indexOf(letter.toLowerCase()) >= 0) {
				String temp = secretWord;
				
				while (temp.contains(letter) || temp.contains(letter.toUpperCase()) || temp.contains(letter.toLowerCase())) {
					if(temp.contains(letter)){
						blank[temp.indexOf(letter)] = letter;
						temp = temp.replaceFirst(letter, "-");
					}else if(temp.contains(letter.toUpperCase())){
						blank[temp.indexOf(letter.toUpperCase())] = letter.toUpperCase();
						temp = temp.replaceFirst(letter.toUpperCase(), "-");
					}else{
						blank[temp.indexOf(letter.toLowerCase())] = letter.toLowerCase();
						temp = temp.replaceFirst(letter.toLowerCase(), "-");
					}
				}
				
				for (String l : blank) {
					System.out.print(l + " ");
				}

			} else {
				System.out.print("no " + letter + "\n");
				for (String l : blank) {
					System.out.print(l + " ");
				}
				k++;
				missCount--;
			}
			if (!Arrays.toString(blank).contains("_")) {
				game = true;
				System.out.println("\nyou won!");
				break;
			}
		}

		if (game == false) {
			System.out.println("\nyou are hung :-(, secret word is "
					+ secretWord);
		}

	}
}
