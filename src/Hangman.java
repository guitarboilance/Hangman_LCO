import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.*;
public class Hangman {
	//empty argument
	private int wins;
	private int losses;
	private Dictionary dic;
	
	/**
	 * The hangman empty argument constructor creates a dictionary object and calls the Words.txt.
	 * @throws FileNotFoundException
	 */
	
	public Hangman() throws FileNotFoundException {
		dic = new Dictionary("src/Words.txt");
		loadWL();
	}
	Scanner input = new Scanner(System.in);
	
	
	/**
	 * prints start and asks player if they would like to play. If no, system prints go away
	 * if true, guesses are set to 5
	 * the chooseWord method is called and is set equal to String currentWord
	 * makes a character array called blanks that stores how long the length of currentWord is and multiplies it by 2 so that there are spaces inbetween each of the blanks
	 * blanks and spaces are printed for how long the currentWord.length is
	 * game while loop starts as long as guesses are greater than 0 and game is not finished
	 * the char guess is created to take in char user input
	 * the for then scans if any of the letters in the currentWord equals the guess
	 * if the guess is incorrect, then guesses will be de-incremented by 1 and total guesses print. Sets finished to true but if there are any blanks left, then finished is set equal to false and the while game loop restarts
	 * 
	 * @throws IOException
	 */
	
	//Methods
	public void playGame() throws IOException {
		
		System.out.println("<<START>>");
		System.out.println("Would you like to play Y/N?");
		boolean playing = false;
			
		if(input.next().charAt(0)=='Y') {
			playing = true;
		}
		
		if(playing) {
				int guesses = 5;
				String currentWord = dic.chooseWord();
				char [] blanks = new char[currentWord.length()*2];
					
				
				for(int i=0;i<currentWord.length()*2;i+=2) {
						blanks[i] = '_';
						blanks[i+1] = ' ';
					}
					boolean finished = false;
				System.out.println("Begin Game");
				
				
						while (guesses>0 && !finished) {
							boolean correct = false;
							System.out.println(blanks);
							System.out.println(currentWord);
							char guess = input.next().charAt(0);
							
							
							for(int i=0; i<currentWord.length() ;i++) {
								
								if(guess==currentWord.charAt(i)) {
									correct = true;
									blanks[i*2] = guess;
									}
							}
							if(!correct) {
								guesses--;
								System.out.println("You have " + guesses + " left");
							}
							
							finished = true;
							for(int j=0;j<blanks.length;j++) {
								if(blanks[j]=='_') {
									finished = false;
								}
							}
							
						}//end while
						
						/**
						 * if finished is true from winning, system prints you win and displays the wins and losses from the writeWL method
						 * if finished is false, then system prints you lose and displays wins and losses
						 * then asks if you want to play again and sets playing equal to false and resets finished to false and guesses to 0
						 */
						
						System.out.println("<<END>>");
						if(finished) {
							System.out.println("You win");
							wins++;
							System.out.println("win loss ratio: wins=" + wins + " losses=" + losses);
							writeWL();
						}
						else {
							System.out.println("You lose");
							losses++;
							System.out.println("win loss ratio: wins=" + wins + " losses=" + losses);
							writeWL();
						}
							
							
			System.out.println("Want to play again? (Y/N)?");
			if(input.next().charAt(0)!='Y') {
				playing = false;
			}
			finished = false;
			guesses = 0;
			
			}
			else{
				System.out.println("Go away");
			}
			
		
		
			
	}
	/**
	 * Scanner reads score.txt
	 * sets the wins and losses in the hangman java file
	 * 
	 * @throws FileNotFoundException
	 */
	private void loadWL() throws FileNotFoundException {
		Scanner read = new Scanner(new File("src/score.txt"));
		String score = null;
		while(read.hasNextLine()) {
			score = read.nextLine();
		}
		wins = score.charAt(0)-48;
		losses = score.charAt(2)-48;
		
		
		}
	/**
	 * writes the current winloss to the score.txt file
	 * @throws IOException
	 */
	
	private void writeWL() throws IOException {
			String winloss = wins + "," + losses;
			
			FileWriter fileWriter = new FileWriter("src/score.txt");
			fileWriter.write(winloss);
			fileWriter.close();
			
		}
	}
	
	

