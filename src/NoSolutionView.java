import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Tells the User there are no solutions to this grid.
 * @author zbva977
 *
 */
public class NoSolutionView {

	public void run() {
		JFrame frame = new JFrame();
		frame.setSize(250, 150);
		JLabel Label1 = new JLabel("No Solution found for this grid");
		Label1.setBounds(25, 20, 200, 100);
		frame.add(Label1);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
