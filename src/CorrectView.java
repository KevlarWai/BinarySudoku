import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Tells user grid is valid.
 * @author zbva977
 *
 */
public class CorrectView {

	public void run() {
		JFrame frame = new JFrame();
		frame.setSize(200, 150);
		JLabel Label1 = new JLabel("Valid Grid!");
		Label1.setBounds(25, 20, 200, 100);
		frame.add(Label1);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
