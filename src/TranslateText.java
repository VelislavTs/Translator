import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TranslateText {

	public static int LINE_NUM = 0;

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("C:\\Users\\velis\\Desktop\\EnglishWords.txt");
		File translatedFile = new File("C:\\Users\\velis\\Desktop\\BulgarianWords.txt");
		Scanner inputTxt = new Scanner(System.in);
		String[] inputText = inputTxt.nextLine().split("\\s");

		ArrayList<String> list = (ArrayList<String>) Arrays.asList(inputText);
		int currentPosition = 0;
		
		try {
		
			Scanner input = new Scanner(file);
			Scanner user = new Scanner(System.in);
			String line = null;
			String userWord = user.next();
			while (input.hasNextLine()) {
				line = input.nextLine();
				LINE_NUM++;
				if (userWord.equals(line)) {
					break;
				}
				
			}
				
			int currentLine = 0;
			String translatedWord = null;
			Scanner findRow = new Scanner(translatedFile);
			
			while(LINE_NUM != currentLine) {
				translatedWord = findRow.nextLine();
				currentLine++;
				if(LINE_NUM == currentLine) {
					System.out.println(translatedWord);
					break;
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
