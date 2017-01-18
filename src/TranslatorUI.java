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
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.Environment;

import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
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
		frame.getContentPane().setBackground(SystemColor.activeCaptionBorder);
		frame.getContentPane().setLayout(null);

		JPanel translatedPanel = new JPanel();
		translatedPanel.setBounds(20, 199, 470, 137);
		translatedPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Translated Text",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(translatedPanel);
		translatedPanel.setLayout(null);

		translatedText = new JTextField();
		translatedText.setEditable(false);
		translatedText.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 21));
		translatedText.setBounds(12, 23, 446, 101);
		translatedText.setBackground(SystemColor.control);
		translatedPanel.add(translatedText);
		translatedText.setColumns(10);

		JPanel userInputPanel = new JPanel();
		userInputPanel.setBounds(10, 63, 494, 137);
		userInputPanel.setBorder(new TitledBorder(null, "Input text to be translated", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(userInputPanel);
		userInputPanel.setLayout(null);
		userInputText = new JTextField();
		userInputText.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 21));
		userInputText.setColumns(10);
		userInputText.setBackground(SystemColor.menu);
		userInputText.setBounds(22, 23, 446, 101);
		userInputPanel.add(userInputText);

		JLabel lblBulgarianToEnglish = new JLabel("BCO Translator");
		lblBulgarianToEnglish.setBounds(66, 13, 373, 37);
		lblBulgarianToEnglish.setHorizontalAlignment(SwingConstants.CENTER);
		lblBulgarianToEnglish.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 21));
		frame.getContentPane().add(lblBulgarianToEnglish);

		JButton btnTranslate = new JButton("Translate");
		btnTranslate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(arg0);
			}
		});
		btnTranslate.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnTranslate.setBounds(53, 337, 137, 25);
		frame.getContentPane().add(btnTranslate);

		JButton btnCopyText = new JButton("Copy text");
		
		btnCopyText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				translatedText.copy();
			}
			
		});
		btnCopyText.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnCopyText.setBounds(190, 337, 137, 25);
		frame.getContentPane().add(btnCopyText);

		JButton btnScreenshot = new JButton("Screenshot");
		btnScreenshot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String userHomeFolder = System.getProperty("user.home") + "/Desktop";
				File textFile = new File(userHomeFolder, "TranslatorScreenshot%d.png");
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage capture;
				
				try {
					capture = new Robot().createScreenCapture(screenRect);
					ImageIO.write(capture, "jpeg", textFile);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		});
		btnScreenshot.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnScreenshot.setBounds(329, 337, 137, 25);
		frame.getContentPane().add(btnScreenshot);
		frame.setBackground(SystemColor.scrollbar);
		frame.setBounds(100, 100, 523, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
