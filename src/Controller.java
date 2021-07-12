import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

/**
 * 
 * @author zbva977
 *
 */
public class Controller {

	private static Controller instance = new Controller();
	private NoSelectedItemView noItem = new NoSelectedItemView();
	private CorrectView valid = new CorrectView();
	private WrongView wrong = new WrongView();
	private NoSolutionView noSolution = new NoSolutionView();
	private Model model;
	private GridView gridView;
	private SelectSizeView selectSizeView;

	private SolveBtnListener solveListener;
	private ValidateBtnListener validateListener;
	private GenerateBtnListener generateListener;
	private ClearBtnListener clearListener;
	private BackBtnListener backListener;

	private SetSizeListener setSizeListener;
	private SizeListListener sizeListListener;
	private int selectedSize = 0;

	private Controller() {
		model = Model.getInstance();

		selectSizeView = SelectSizeView.getInstance();

		this.setSizeListener = new SetSizeListener();
		this.sizeListListener = new SizeListListener();

		selectSizeView.setSizeListener(setSizeListener);
		selectSizeView.setSizeListListener(sizeListListener);
	}

	public void setSize(int size) {
		model.setSize(size);
	}

	public void gridView(String item) {
		gridView = GridView.getInstance();
		this.solveListener = new SolveBtnListener();
		this.validateListener = new ValidateBtnListener();
		this.generateListener = new GenerateBtnListener();
		this.clearListener = new ClearBtnListener();
		this.backListener = new BackBtnListener();
		gridView.setSolveBtnListener(solveListener);
		gridView.setValidateBtnListener(validateListener);
		gridView.setGenerateBtnListener(generateListener);
		gridView.setClearBtnListener(clearListener);
		gridView.setBackBtnListener(backListener);

		gridView.setGridSize(selectedSize);
		gridView.addCellBtn();
		gridView.setVisible(true);
		selectSizeView.setVisible(false);

	}

	public void noSelectedItem() {
		noItem.run();
	}

	public void validGrid() {
		valid.run();
	}

	public void invalidGrid() {
		wrong.run();
	}

	public void noSolutionFound() {
		noSolution.run();
	}

	public boolean solve() {
		model.getEmpty();
		return model.solve();
	}

	public boolean validate() {
		return model.validate();
	}

	public void setValue(int value, int xcoord, int ycoord) {
		model.setValue(value, xcoord, ycoord);
	}

	public int getValue(int xcoord, int ycoord) {
		return model.getValue(xcoord, ycoord);
	}

	public void generate(int size) {
		model.generate(size);
	}

	public void updateGrid() {
		for (int i = 0; i < selectedSize; i++) {
			for (int j = 0; j < selectedSize; j++) {
				if (gridView.getCell(i, j).equals("")) {
					setValue(-1, i, j);
				} else {
					setValue(Integer.parseInt(gridView.getCell(i, j)), i, j);
				}
			}
		}
	}

	public void getGrid() {
		for (int i = 0; i < selectedSize; i++) {
			for (int j = 0; j < selectedSize; j++) {
				if (model.getValue(i, j) == -1) {
					gridView.setCell("", i, j);
				} else {
					gridView.setCell(Integer.toString(model.getValue(i, j)), i, j);
				}
			}
		}
	}

	private class SolveBtnListener implements ActionListener {

		SolveBtnListener() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateGrid();
			if (solve()) {
				getGrid();
			} else {
				noSolutionFound();
			}

		}
	}

	private class ValidateBtnListener implements ActionListener {

		ValidateBtnListener() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateGrid();
			if (validate()) {
				validGrid();
			} else {
				invalidGrid();
			}
		}

	}

	private class GenerateBtnListener implements ActionListener {

		GenerateBtnListener() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			generate(selectedSize);
			getGrid();
		}

	}

	private class ClearBtnListener implements ActionListener {

		ClearBtnListener() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			for (int i = 0; i < selectedSize; i++) {
				for (int j = 0; j < selectedSize; j++) {
					model.setValue(-1, i, j);
					gridView.setCell("", i, j);
				}
			}
		}
	}

	private class BackBtnListener implements ActionListener {
		BackBtnListener() {

		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gridView.setVisible(false);
			selectSizeView.setVisible(true);
			gridView.removeBtn();
		}
	}

	private class SetSizeListener implements ActionListener {

		SetSizeListener() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (selectedSize == 0) {
				noSelectedItem();
			} else {
				gridView(Integer.toString(selectedSize));
				setSize(selectedSize);
			}
		}
	}

	private class SizeListListener implements ActionListener {

		SizeListListener() {
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			selectedSize = Integer.parseInt((String) ((JComboBox<String>) arg0.getSource()).getSelectedItem());
		}

	}

	public static Controller getInstance() {
		return instance;
	}
}
