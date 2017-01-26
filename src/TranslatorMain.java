import java.awt.EventQueue;

public class TranslatorMain {
//http://pastebin.com/xfsxx8mD
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
