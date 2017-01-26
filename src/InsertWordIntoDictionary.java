import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class InsertWordIntoDictionary extends ErrorPopUp {

	private JFrame frmInsertNewWords;
	private JTextField englishWord;
	private JTextField bulgarianWord;

	/**
	 * Launch the application.
	 */
	public static void openInsertWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertWordIntoDictionary window = new InsertWordIntoDictionary();
					window.frmInsertNewWords.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InsertWordIntoDictionary() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInsertNewWords = new JFrame();
		frmInsertNewWords.setTitle("Insert new words into dictionary");
		frmInsertNewWords.getContentPane().setBackground(UIManager.getColor("CheckBox.light"));
		frmInsertNewWords.setBackground(new Color(204, 204, 204));
		frmInsertNewWords.setBounds(100, 100, 450, 300);
		frmInsertNewWords.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInsertNewWords.getContentPane().setLayout(null);

		JPanel addedEnglishWord = new JPanel();
		addedEnglishWord.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "English word",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		addedEnglishWord.setBounds(12, 27, 408, 100);
		frmInsertNewWords.getContentPane().add(addedEnglishWord);
		addedEnglishWord.setLayout(null);

		englishWord = new JTextField();
		englishWord.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 21));
		englishWord.setHorizontalAlignment(SwingConstants.CENTER);
		englishWord.setBounds(12, 27, 384, 45);
		addedEnglishWord.add(englishWord);
		englishWord.setColumns(10);

		JPanel addedBulgarianWord = new JPanel();
		addedBulgarianWord.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bulgarian word",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		addedBulgarianWord.setBounds(12, 140, 408, 100);
		frmInsertNewWords.getContentPane().add(addedBulgarianWord);
		addedBulgarianWord.setLayout(null);

		JButton btnInsertWord = new JButton("Insert");
		btnInsertWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (englishWord.getText().split(" ").length != 1 || bulgarianWord.getText().split(" ").length != 1) {
					ErrorPopUp showError = new ErrorPopUp();
					ErrorPopUp.displayError();

				} else {
					importEnglishText(englishWord.getText());
					importBulgarianText(bulgarianWord.getText());
				}
			}

		});
		btnInsertWord.setBounds(153, 75, 97, 25);
		addedBulgarianWord.add(btnInsertWord);

		bulgarianWord = new JTextField();
		bulgarianWord.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 21));
		bulgarianWord.setHorizontalAlignment(SwingConstants.CENTER);
		bulgarianWord.setColumns(10);
		bulgarianWord.setBounds(12, 25, 384, 45);
		addedBulgarianWord.add(bulgarianWord);
	}

	public static void importEnglishText(String word) {
		try (FileWriter fw = new FileWriter("C:\\Users\\velis\\Desktop\\EnglishWords.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(word);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private void importBulgarianText(String word) {

		try (FileWriter fw = new FileWriter("C:\\Users\\velis\\Desktop\\BulgarianWords.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(word);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
