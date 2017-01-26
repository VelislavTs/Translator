import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.Color;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TranslatorUI {

	JFrame frame;
	private JTextField translatedText;
	private JTextField userInputText;

	/**
	 * Create the application.
	 */
	public TranslatorUI() {
		interfaceInit();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void interfaceInit() {

		frame = new JFrame("Translator");
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setResizable(false);
		frame.getContentPane().setBackground(UIManager.getColor("CheckBox.light"));
		frame.getContentPane().setLayout(null);

		JPanel translatedPanel = new JPanel();
		translatedPanel.setBounds(20, 199, 470, 137);
		translatedPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Translated Text",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(translatedPanel);
		translatedPanel.setLayout(null);

		translatedText = new JTextField();
		translatedText.setHorizontalAlignment(SwingConstants.CENTER);
		translatedText.setEditable(false);
		translatedText.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 21));
		translatedText.setBounds(12, 23, 446, 101);
		translatedText.setBackground(SystemColor.control);
		translatedPanel.add(translatedText);
		translatedText.setColumns(10);

		JPanel userInputPanel = new JPanel();
		userInputPanel.setBackground(UIManager.getColor("Button.background"));
		userInputPanel.setBounds(10, 63, 494, 137);
		userInputPanel.setBorder(new TitledBorder(null, "Input text to be translated", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(userInputPanel);
		userInputPanel.setLayout(null);

		userInputText = new JTextField();
		userInputText.setHorizontalAlignment(SwingConstants.CENTER);
		userInputText.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 21));
		userInputText.setColumns(10);
		userInputText.setBackground(SystemColor.menu);
		userInputText.setBounds(22, 23, 446, 101);
		userInputPanel.add(userInputText);

		JLabel lblBulgarianToEnglish = new JLabel("VSC Translator");
		lblBulgarianToEnglish.setBounds(66, 13, 373, 37);
		lblBulgarianToEnglish.setHorizontalAlignment(SwingConstants.CENTER);
		lblBulgarianToEnglish.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 21));
		frame.getContentPane().add(lblBulgarianToEnglish);

		JButton btnTranslate = new JButton("Translate");
		btnTranslate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				translatedText.setText(returnTranslatedText(userInputText.getText()));
			}
		});
		btnTranslate.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnTranslate.setBounds(20, 337, 115, 25);
		frame.getContentPane().add(btnTranslate);
		JButton btnCopyText = new JButton("Copy text");
		btnCopyText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				translatedText.copy();
			}

		});
		btnCopyText.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnCopyText.setBounds(136, 337, 115, 25);
		frame.getContentPane().add(btnCopyText);

		JButton btnScreenshot = new JButton("Screenshot");
		btnScreenshot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String userHomeFolder = System.getProperty("user.home") + "/Desktop";
				File textFile = new File(userHomeFolder, "myPic.png");
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
				} catch (IOException e1) {
					System.out.println(e1);
				}
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage capture;

				try {
					capture = new Robot().createScreenCapture(screenRect);
					ImageIO.write(capture, "bmp", textFile);
				} catch (IOException e) {
					System.out.println(e);
				} catch (AWTException e) {
					System.out.println(e);
				}
			}
		});
		btnScreenshot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnScreenshot.setBounds(251, 337, 129, 25);
		frame.getContentPane().add(btnScreenshot);

		JButton btnInsertWord = new JButton("Insert word");
		btnInsertWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MAX_ROWS = MAX_ROWS + 1;
				InsertWordIntoDictionary addWord = new InsertWordIntoDictionary();
				addWord.openInsertWindow();

			}
		});
		btnInsertWord.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnInsertWord.setBounds(375, 337, 115, 25);
		frame.getContentPane().add(btnInsertWord);
		frame.setBackground(SystemColor.scrollbar);
		frame.setBounds(100, 100, 523, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static int lineNumber = 0;
	public static int MAX_ROWS = countLines() - 1;
	public static String textForTranslation = " ";

	public static String returnTranslatedText(String inputText) {

		char[] bulgarianAlphabet = { 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
				'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'щ', 'ь', 'ю', 'я' };

		try {

			textForTranslation = inputText;
			int currentLine = 0;
			File initialWords = new File("C:\\Users\\velis\\Desktop\\EnglishWords.txt");
			File translationWords = new File("C:\\Users\\velis\\Desktop\\BulgarianWords.txt");
			char[] text = textForTranslation.toCharArray();
			// check if the input is in English or Bulgarian
			for (int i = 0; i < text.length;) {
				for (int j = 0; j < bulgarianAlphabet.length; j++) {
					if (text[i] == (bulgarianAlphabet[j])) {
						initialWords = new File("C:\\Users\\velis\\Desktop\\BulgarianWords.txt");
						translationWords = new File("C:\\Users\\velis\\Desktop\\EnglishWords.txt");
					}
				}

				break;
			}
			// Convert the input to a List to make manipulations easier
			List<String> userInputSentence = new ArrayList<String>(Arrays.asList(textForTranslation.split(" ")));

			String currentWord = null;
			String translatedWord = null;
			List<String> translatedSentence = new ArrayList<String>();

			Scanner readInitialWordsFile = new Scanner(initialWords);
			Scanner readTranslatedWordsFile = new Scanner(translationWords);
			// Translation algorithm
			while (!userInputSentence.isEmpty()) {
				// Find the row where words
				// have matched
				while (readInitialWordsFile.hasNextLine()) {
					currentWord = readInitialWordsFile.nextLine();
					lineNumber++;
					if (userInputSentence.get(0).equals(currentWord)) {
						break;
					}
				}
				/*
				 * when the counter and the line number meet, the word at that
				 * line is added to the list
				 */

				while (lineNumber != currentLine) {

					translatedWord = readTranslatedWordsFile.nextLine();
					currentLine++;
					// if the loop finds no matches throughout the document it
					// returns the word

					if (currentLine == MAX_ROWS + 1) {
						translatedSentence.add(userInputSentence.get(0));
						break;
					}

					if (lineNumber == currentLine) {
						translatedSentence.add(translatedWord);
						break;
					}
				}

				userInputSentence.remove(0);
				currentLine = 0;
				lineNumber = 0;
				readInitialWordsFile = new Scanner(initialWords);
				readTranslatedWordsFile = new Scanner(translationWords);
			}

			StringBuilder builder = new StringBuilder();
			for (String appendWords : translatedSentence) {
				builder.append(appendWords + " ");
			}

			return builder.toString();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return null;

	}

	private static int countLines() {
		int lines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\velis\\Desktop\\BulgarianWords.txt"));
			while (reader.readLine() != null) {
				lines++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;

	}
}
