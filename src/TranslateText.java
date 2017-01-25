import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.Object;

public class TranslateText {

	public static int LINE_NUM = 0;

	public static void main(String[] args) {
		try {
			File englishWords = new File("C:\\Users\\velis\\Desktop\\EnglishWords.txt");

			Scanner readEnglishWords = new Scanner(englishWords);
			Scanner userInput = new Scanner(System.in);
			String userInputSentence = userInput.nextLine();
			String[] userInputArray = userInputSentence.split(" ");
			String currentEnglishWord = null;
			String currentWord = " ";
			String searchedWord = " ";

			int currentLine = 0;
			File bulgarianWords = new File("C:\\Users\\velis\\Desktop\\BulgarianWords.txt");
			Scanner readBulgarianWords = new Scanner(bulgarianWords);
			String translatedWord = null;

			List<String> userSentence = new ArrayList<String>(Arrays.asList(userInputSentence.split(" ")));

			while (!userSentence.equals(" ")) {
				while (readEnglishWords.hasNextLine()) {
					currentWord = readEnglishWords.nextLine();
					LINE_NUM++;
					if (searchedWord.equals(currentWord)) {
						userSentence.remove(0);
					}
				}

				while (LINE_NUM != currentLine) {
					translatedWord = readBulgarianWords.nextLine();
					currentLine++;
					if (LINE_NUM == currentLine) {
						System.out.println(translatedWord);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
