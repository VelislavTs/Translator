import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

public class ErrorPopUp {

	private JFrame frmInsertionError;

	/**
	 * Launch the application.
	 */
	public static void displayError() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorPopUp window = new ErrorPopUp();
					window.frmInsertionError.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ErrorPopUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInsertionError = new JFrame();
		frmInsertionError.setTitle("Insertion error");
		frmInsertionError.setBounds(100, 100, 440, 144);
		frmInsertionError.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInsertionError.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Please, input no more than one word!");
		label.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 23));
		label.setBounds(34, 30, 360, 33);
		frmInsertionError.getContentPane().add(label);
	}

}
