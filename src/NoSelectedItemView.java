import javax.swing.*;

/**
 * Tells user they have no selected a size for the grid.
 * @author zbva977
 *
 */
public class NoSelectedItemView {

	public void run() {
		JFrame frame = new JFrame();
		frame.setSize(200, 150);
		JLabel Label1 = new JLabel("Please select grid size");
		Label1.setBounds(25, 20, 200, 100);
		frame.add(Label1);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
