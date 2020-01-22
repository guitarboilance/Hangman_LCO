import java.security.*;
import java.io.*;
import java.util.*;
public class Dictionary {
	//Empty-Argument Constructor
	private static ArrayList<String> wordList = new ArrayList<String>();
	int currentCard = 0;
	SecureRandom random = new SecureRandom();
	
	/**
	 * the Dictionary empty argument constructor tries to read the file Words.txt and if it doesn't, it throws the FileNotFound Exception and prints the error
	 */
	public Dictionary(){
		try {
			readFile("src/Words.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * the dictionary preferred constructor passes in fileName to read the Words.txt file and prints and throws exception if not found
	 * @param fileName
	 */
	
	public Dictionary(String fileName){
		try {
			readFile(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//methods
	
	/**
	 * the readFile method scans the text file and turns it into a temporary file object which is scanned and put in the wordList ArrayList until there is no more words left to scan
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	
	private static void readFile(String fileName) throws FileNotFoundException {
		try (Scanner input = new Scanner(new File(fileName))) {
			while(input.hasNextLine()) {
				wordList.add(input.nextLine());
			}
		}
		
	}
	
	/**
	 * choose word string method gets a random number from the securerandom class from 0 to 199 and then adds 1 to make it 1-200. It then gets the word from the corresponding wordList that matches the random number.
	 * @return
	 */
	
	public String chooseWord() {
		int rNum = random.nextInt(199)+1; 
		return wordList.get(rNum);
	}
	
	
}




