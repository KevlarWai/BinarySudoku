import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * GUI to select size of grid.
 * @author zbva977
 *
 */
public class SelectSizeView extends JFrame {
	JPanel frame = new JPanel();
	private static SelectSizeView instance = new SelectSizeView();
	private static int frameWidth = 400;
	private static int frameLength = 400;
	private String item = "No item";
	private JButton setSizeBtn;
	JComboBox<String> sizeList;

	public SelectSizeView() {
		setBounds(100, 100, 400, 400);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSizeBtn = new JButton("Select Size");
		setSizeBtn.setBounds(125, 250, 100, 20);
		JLabel Label1 = new JLabel("Binary Puzzle");
		Label1.setBounds(135, 50, 100, 100);
		JLabel Label2 = new JLabel("Please select grid size");
		Label2.setBounds(110, 80, 200, 100);
		sizeList = new JComboBox<String>();
		sizeList.addItem("");
		sizeList.addItem("6");
		sizeList.addItem("8");
		sizeList.addItem("10");
		sizeList.addItem("12");
		sizeList.setBounds(125, 175, 100, 30);

		getContentPane().add(setSizeBtn);
		getContentPane().add(Label1);
		getContentPane().add(Label2);
		getContentPane().add(sizeList);

		setVisible(true);

	}

	public void setSizeListener(ActionListener listener) {
		this.setSizeBtn.addActionListener(listener);
	}

	public void setSizeListListener(ActionListener listener) {
		this.sizeList.addActionListener(listener);
	}

	public static SelectSizeView getInstance() {
		return instance;
	}

}
