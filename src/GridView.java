import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The view of the Grid.
 * @author zbva977
 *
 */
public class GridView extends JFrame implements ActionListener {

	JPanel frame = new JPanel();
	private static GridView instance = new GridView();
	private static int frameWidth = 850;
	private static int frameLength = 850;
	int size;
	private JButton cells[][];
	private JButton solveBtn;
	private JButton validateBtn;
	private JButton generateBtn;
	private JButton clearBtn;
	private JButton backBtn;

	public GridView() {
		setBounds(100, 100, 850, 850);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.solveBtn = new JButton("Solve!");
		this.solveBtn.setBounds(700, 75, 100, 20);
		this.validateBtn = new JButton("Validate");
		this.validateBtn.setBounds(700, 100, 100, 20);
		this.generateBtn = new JButton("Generate");
		this.generateBtn.setBounds(700, 125, 100, 20);
		this.clearBtn = new JButton("Clear Grid");
		this.clearBtn.setBounds(700, 150, 100, 20);
		this.backBtn = new JButton("Back");
		this.backBtn.setBounds(700, 175, 100, 20);
		JLabel Label1 = new JLabel("Binary Puzzle");
		Label1.setBounds(10, 10, 100, 50);

		add(this.solveBtn);
		add(this.validateBtn);
		add(this.generateBtn);
		add(this.clearBtn);
		add(this.backBtn);
		add(Label1);
		setVisible(false);
	}

	public void addCellBtn() {
		this.cells = new JButton[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.cells[i][j] = new JButton();
				this.cells[i][j].setBounds(20 + ((i + 1) * 50), ((j + 1) * 50) + 30, 50, 50);
				this.cells[i][j].setActionCommand("click");
				this.cells[i][j].addActionListener(this);
				add(this.cells[i][j]);
			}
		}
	}

	public void setGridSize(int size) {
		this.size = size;
	}

	public void setCell(String value, int xcoord, int ycoord) {
		this.cells[xcoord][ycoord].setText(value);
	}

	public String getCell(int xcoord, int ycoord) {
		return this.cells[xcoord][ycoord].getText();
	}

	public void setSolveBtnListener(ActionListener listener) {
		this.solveBtn.addActionListener(listener);
	}

	public void setValidateBtnListener(ActionListener listener) {
		this.validateBtn.addActionListener(listener);
	}

	public void setGenerateBtnListener(ActionListener listener) {
		this.generateBtn.addActionListener(listener);
	}

	public void setClearBtnListener(ActionListener listener) {
		this.clearBtn.addActionListener(listener);
	}

	public void setBackBtnListener(ActionListener listener) {
		this.backBtn.addActionListener(listener);
	}

	public void cellBtnListener(ActionListener listener) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.cells[i][j].addActionListener(listener);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("click".equals(e.getActionCommand())) {
			if ("".equals(((AbstractButton) e.getSource()).getText())) {
				((AbstractButton) e.getSource()).setText("0");
			} else if ("0".equals(((AbstractButton) e.getSource()).getText())) {
				((AbstractButton) e.getSource()).setText("1");
			} else if ("1".equals(((AbstractButton) e.getSource()).getText())) {
				((AbstractButton) e.getSource()).setText("");
			} else if ("-1".equals(((AbstractButton) e.getSource()).getText())) {
				((AbstractButton) e.getSource()).setText("");
			}
		}

	}

	public static GridView getInstance() {
		return instance;
	}

	public void removeBtn() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.remove(cells[i][j]);
			}
		}

	}
}
