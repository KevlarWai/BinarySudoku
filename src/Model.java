/**
 * 
 * @author zbva977
 *
 */
public class Model {

	private static Model instance = new Model();
	private static int size;

	private Grid grid;

	public static Model getInstance() {
		return instance;
	}

	public void setSize(int size) {
		this.size = size;
		grid = new Grid(this.size, this.size);
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				grid.setValue(-1, i, j);
			}
		}
	}

	public int getSize() {
		return this.size;
	}

	public boolean solve() {
		grid.doubleValue();
		return grid.solveBruteForce(0);
	}

	public boolean validate() {
		return grid.validGrid();
	}

	public void setValue(int value, int xcoord, int ycoord) {
		grid.setValue(value, xcoord, ycoord);
	}

	public int getValue(int xcoord, int ycoord) {
		return grid.getValue(xcoord, ycoord);
	}

	public void getEmpty() {
		grid.getEmpty();
	}

	public void generate(int size) {
		grid.generateGrid(size);
	}
}
