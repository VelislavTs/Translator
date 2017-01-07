import java.awt.EventQueue;

public class TranslatorMain extends TranslatorAPI {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TranslatorUI window = new TranslatorUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
