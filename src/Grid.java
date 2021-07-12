import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Contains the main functions/algorithms for Binary Puzzle.
 * 
 * @author zbva977
 *
 */
public class Grid {

	private int xdim;
	private int ydim;
	private int isEmpty = -1;
	private int[][] grid;
	private int numOfEmptyCell;
	private List<Integer> emptyListXcoord;
	private List<Integer> emptyListYcoord;
	private Random random = new Random();

	Grid(int xdim, int ydim) {
		this.xdim = xdim;
		this.ydim = ydim;
		this.grid = new int[ydim][xdim];

	}

	public void getEmpty() {
		emptyCell();
		this.emptyListXcoord = new ArrayList<Integer>(numOfEmptyCell);
		this.emptyListYcoord = new ArrayList<Integer>(numOfEmptyCell);
		fillEmptyList();
	}

	/**
	 * Gets the X dimension of grid.
	 * 
	 * @return
	 */
	public int getXdimention() {
		return this.xdim;
	}

	/**
	 * Gets Y dimension of grid.
	 * 
	 * @return
	 */
	public int getYdimention() {
		return this.ydim;
	}

	public int getNumOfEmptyCell() {
		return this.numOfEmptyCell;
	}

	/**
	 * Sets X dimension of grid.
	 * 
	 * @param xdim
	 */
	public void setXdimention(int xdim) {
		this.xdim = xdim;
		this.grid = new int[this.ydim][this.xdim];
	}

	/**
	 * Sets Y dimension of grid.
	 * 
	 * @param ydim
	 */
	public void setYdimention(int ydim) {
		this.ydim = ydim;
		this.grid = new int[this.ydim][this.xdim];
	}

	/**
	 * Sets value of a cell using the coordinates.
	 * 
	 * @param value
	 * @param xcoord
	 * @param ycoord
	 */
	public void setValue(int value, int xcoord, int ycoord) {
		grid[ycoord][xcoord] = value;
	}

	/**
	 * Returns the value of the value using the coordinates.
	 * 
	 * @param xcoord
	 * @param ycoord
	 * @return
	 */
	public int getValue(int xcoord, int ycoord) {
		return grid[ycoord][xcoord];
	}

	/**
	 * return true if empty.
	 * 
	 * @param xcoord
	 * @param ycoord
	 * @return
	 */
	public boolean checkEmpty(int xcoord, int ycoord) {
		if (getValue(xcoord, ycoord) == isEmpty) {
			return true;
		}
		return false;
	}

	/**
	 * return true if whole grid if filled.
	 * 
	 * @return
	 */
	public boolean filledGrid() {
		int count = 0;
		for (int i = 0; i < xdim; i++) {
			for (int j = 0; j < ydim; j++) {
				if (checkEmpty(i, j)) {
					count++;
				}
			}
		}
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * returns true if it has less than 3 of neighbours same value.
	 * 
	 * @param xcoord
	 * @param ycoord
	 * @return
	 */
	public boolean checkNeighbours(int xcoord, int ycoord) {
		int check = 0;
		int value = getValue(xcoord, ycoord);
		if (xcoord > 0) {
			if (value == getValue(xcoord - 1, ycoord)) {
				check++;
			}
		}
		if (xcoord < xdim - 1) {
			if (value == getValue(xcoord + 1, ycoord)) {
				check++;
			}
		}
		if (ycoord > 0) {
			if (value == getValue(xcoord, ycoord - 1)) {
				check++;
			}
		}
		if (ycoord < ydim - 1) {
			if (value == getValue(xcoord, ycoord + 1)) {
				check++;
			}
		}
		if (check < 3) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkTwoNeighbours(int xcoord, int ycoord) {
		int check = 0;
		int value = getValue(xcoord, ycoord);
		if (xcoord > 1) {
			if (value == getValue(xcoord - 1, ycoord) && value == getValue(xcoord - 2, ycoord)) {
				check++;
			}
		}
		if (xcoord < xdim - 2) {
			if (value == getValue(xcoord + 1, ycoord) && value == getValue(xcoord + 2, ycoord)) {
				check++;
			}
		}
		if (ycoord > 1) {
			if (value == getValue(xcoord, ycoord - 1) && value == getValue(xcoord, ycoord - 2)) {
				check++;
			}
		}
		if (ycoord < ydim - 2) {
			if (value == getValue(xcoord, ycoord + 1) && value == getValue(xcoord, ycoord + 2)) {
				check++;
			}
		}
		if (check == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * return true if whole grid is correct.
	 * 
	 * @return
	 */
	public boolean validGrid() {
		int check = 0;
		boolean valid = true;
		boolean filled = true;
		boolean unique = true;
		boolean neighbour = true;
		boolean equal = true;
		for (int i = 0; i < ydim; i++) {
			for (int j = 0; j < ydim; j++) {
				if (!checkNeighbours(j, i) || !checkTwoNeighbours(i, j)) {
					check++;
				}
			}
		}
		if (check != 0) {
			neighbour = false;
		}
		for (int i = 0; i < xdim; i++) {
			if (!checkCol(i) || !checkRow(i)) {
				equal = false;
			}
		}
		if (!uniqueRow() || !uniqueCol()) {
			unique = false;
		}
		filled = filledGrid();
		valid = (filled && unique && neighbour && equal);
		return valid;
	}

	public boolean checkMove(int value, int xcoord, int ycoord) {
		int check = 0;
		int empty = 0;
		if (xcoord > 0) {
			if (value == getValue(xcoord - 1, ycoord)) {
				check++;
			}
			if (-1 == getValue(xcoord - 1, ycoord)) {
				empty++;
			}
		}
		if (xcoord < xdim - 1) {
			if (value == getValue(xcoord + 1, ycoord)) {
				check++;
			}
			if (-1 == getValue(xcoord + 1, ycoord)) {
				empty++;
			}
		}
		if (ycoord > 0) {
			if (value == getValue(xcoord, ycoord - 1)) {
				check++;
			}
			if (-1 == getValue(xcoord, ycoord - 1)) {
				empty++;
			}
		}
		if (ycoord < ydim - 1) {
			if (value == getValue(xcoord, ycoord + 1)) {
				check++;
			}
			if (-1 == getValue(xcoord, ycoord + 1)) {
				empty++;
			}
		}
		if (check < 3 && empty <= 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * return true if next/previous 2 neighbours are not the same.
	 * 
	 * @param value
	 * @param xcoord
	 * @param ycoord
	 * @return
	 */
	public boolean checkTwoNeighbourMove(int value, int xcoord, int ycoord) {
		int check = 0;
		if (xcoord > 1) {
			if ((value == getValue(xcoord - 1, ycoord)) && (value == getValue(xcoord - 2, ycoord))) {
				check++;
			}
		}
		if (xcoord < xdim - 2) {
			if ((value == getValue(xcoord + 1, ycoord)) && (value == getValue(xcoord + 2, ycoord))) {
				check++;
			}
		}
		if (ycoord > 1) {
			if ((value == getValue(xcoord, ycoord - 1)) && (value == getValue(xcoord, ycoord - 2))) {
				check++;
			}
		}
		if (ycoord < ydim - 2) {
			if ((value == getValue(xcoord, ycoord + 1)) && (value == getValue(xcoord, ycoord + 2))) {
				check++;
			}
		}
		if (check == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkRow(int ycoord) {
		int numOfZero = 0;
		int numOfOne = 0;
		for (int i = 0; i < getXdimention(); i++) {
			if (getValue(i, ycoord) == 0) {
				numOfZero++;
			} else if (getValue(i, ycoord) == 1) {
				numOfOne++;
			}
		}
		if (numOfZero == numOfOne) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkCol(int xcoord) {
		int numOfZero = 0;
		int numOfOne = 0;
		for (int i = 0; i < getYdimention(); i++) {
			if (getValue(xcoord, i) == 0) {
				numOfZero++;
			} else if (getValue(xcoord, i) == 1) {
				numOfOne++;
			}
		}
		if (numOfZero == numOfOne) {
			return true;
		} else {
			return false;
		}
	}

	private void emptyCell() {
		numOfEmptyCell = 0;
		for (int i = 0; i < ydim; i++) {
			for (int j = 0; j < xdim; j++) {
				if (checkEmpty(j, i)) {
					numOfEmptyCell++;
				}
			}
		}
	}

	/**
	 * return true if valid move.
	 * 
	 * @param value
	 * @param xcoord
	 * @param ycoord
	 * @return
	 */
	public boolean move(int value, int xcoord, int ycoord) {
		if ((checkMove(value, xcoord, ycoord) && checkTwoNeighbourMove(value, xcoord, ycoord))) {
			return true;
		}
		return false;
	}

	private void fillEmptyList() {
		for (int i = 0; i < ydim; i++) {
			for (int j = 0; j < xdim; j++) {
				if (checkEmpty(j, i)) {
					emptyListXcoord.add(j);
					emptyListYcoord.add(i);
				}
			}
		}
	}

	public boolean solve(int pos) {
		boolean check = false;
		if (pos < numOfEmptyCell) {
			if (checkMove(0, emptyListXcoord.get(pos), emptyListYcoord.get(pos))) {
				setValue(0, emptyListXcoord.get(pos), emptyListYcoord.get(pos));

			}
			check = solve(pos + 1);
			if (!check) {
				if (checkMove(1, emptyListXcoord.get(pos), emptyListYcoord.get(pos))) {
					setValue(1, emptyListXcoord.get(pos), emptyListYcoord.get(pos));
				}
				check = solve(pos + 1);
			}
		} else {
			return validGrid();
		}
		return check;
	}

	public boolean solveBruteForce(int pos) {
		boolean check = false;
		if (pos < numOfEmptyCell) {
			setValue(0, emptyListXcoord.get(pos), emptyListYcoord.get(pos));
			check = solveBruteForce(pos + 1);
			if (!check) {
				setValue(1, emptyListXcoord.get(pos), emptyListYcoord.get(pos));
				check = solveBruteForce(pos + 1);
			}
		} else {
			return validGrid();
		}
		return check;
	}

	public void doubleValue() {
		for (int i = 0; i < numOfEmptyCell; i++) {
			if (emptyListXcoord.get(i) > 1) {
				if (getValue(emptyListXcoord.get(i) - 1, emptyListYcoord.get(i)) == getValue(emptyListXcoord.get(i) - 2,
						emptyListYcoord.get(i))) {
					if ((getValue(emptyListXcoord.get(i) - 1, emptyListYcoord.get(i))) == 1) {
						setValue(0, emptyListYcoord.get(i), emptyListXcoord.get(i));
					} else {
						setValue(1, emptyListYcoord.get(i), emptyListXcoord.get(i));
					}
					numOfEmptyCell = numOfEmptyCell - 1;
					emptyListXcoord.remove(i);
					emptyListYcoord.remove(i);
				}
			}
			if (emptyListXcoord.get(i) < xdim - 2) {
				if (getValue(emptyListXcoord.get(i) + 1, emptyListYcoord.get(i)) == getValue(emptyListXcoord.get(i) + 2,
						emptyListYcoord.get(i))) {
					if ((getValue(emptyListXcoord.get(i) + 1, emptyListYcoord.get(i))) == 1) {
						setValue(0, emptyListYcoord.get(i), emptyListXcoord.get(i));
					} else {
						setValue(1, emptyListYcoord.get(i), emptyListXcoord.get(i));
					}
					numOfEmptyCell = numOfEmptyCell - 1;
					emptyListXcoord.remove(i);
					emptyListYcoord.remove(i);
				}
			}
			if (emptyListYcoord.get(i) > 1) {
				if (getValue(emptyListXcoord.get(i), emptyListYcoord.get(i) - 1) == getValue(emptyListXcoord.get(i),
						emptyListYcoord.get(i) - 2)) {
					if ((getValue(emptyListXcoord.get(i), emptyListYcoord.get(i) - 1)) == 1) {
						setValue(0, emptyListYcoord.get(i), emptyListXcoord.get(i));
					} else {
						setValue(1, emptyListYcoord.get(i), emptyListXcoord.get(i));
					}
					numOfEmptyCell = numOfEmptyCell - 1;
					emptyListXcoord.remove(i);
					emptyListYcoord.remove(i);
				}
			}
			if (emptyListYcoord.get(i) < ydim - 2) {
				if (getValue(emptyListXcoord.get(i), emptyListYcoord.get(i) + 1) == getValue(emptyListXcoord.get(i),
						emptyListYcoord.get(i) + 2)) {
					if ((getValue(emptyListXcoord.get(i), emptyListYcoord.get(i) + 1)) == 1) {
						setValue(0, emptyListYcoord.get(i), emptyListXcoord.get(i));
					} else {
						setValue(1, emptyListYcoord.get(i), emptyListXcoord.get(i));
					}
					numOfEmptyCell = numOfEmptyCell - 1;
					emptyListXcoord.remove(i);
					emptyListYcoord.remove(i);
				}
			}

		}
	}

	public boolean uniqueCol() {
		boolean unique = true;
		int[] col = new int[ydim];
		int[] compare = new int[ydim];
		for (int i = 0; i < xdim; i++) {
			for (int j = 0; j < ydim; j++) {
				col[j] = getValue(i, j);
			}
			for (int k = i + 1; k < xdim; k++) {
				for (int l = 0; l < ydim; l++) {
					compare[l] = getValue(k, l);
				}
				if (Arrays.equals(col, compare)) {
					unique = false;
				}
			}
		}
		return unique;
	}

	public boolean uniqueRow() {
		boolean unique = true;
		int[] col = new int[xdim];
		int[] compare = new int[xdim];
		for (int i = 0; i < ydim; i++) {
			for (int j = 0; j < xdim; j++) {
				col[j] = getValue(j, i);
			}
			for (int k = i + 1; k < ydim; k++) {
				for (int l = 0; l < xdim; l++) {
					compare[l] = getValue(l, k);
				}
				if (Arrays.equals(col, compare)) {
					unique = false;
				}
			}
		}
		return unique;
	}

	public boolean countRow(int row) {
		boolean count = true;
		int counter1 = 0;
		int counter0 = 0;
		for (int i = 0; i < xdim; i++) {
			if (1 == getValue(i, row)) {
				counter1++;
			} else if (0 == getValue(i, row)) {
				counter0++;
			}
			if (counter1 >= (ydim / 2) || counter0 >= (ydim / 2)) {
				count = false;
			}
		}
		return count;
	}

	public boolean countCol(int col) {
		boolean count = true;
		int counter1 = 0;
		int counter0 = 0;
		for (int i = 0; i < ydim; i++) {
			if (1 == getValue(col, i)) {
				counter1++;
			} else if (0 == getValue(col, i)) {
				counter0++;
			}
			if (counter1 >= (ydim / 2) || counter0 >= (ydim / 2)) {
				count = false;
			}
		}
		return count;
	}

	public boolean invalidCol(int col) {
		boolean count = true;
		int counter1 = 0;
		int counter0 = 0;
		for (int i = 0; i < ydim; i++) {
			if (1 == getValue(col, i)) {
				counter1++;
			} else if (0 == getValue(col, i)) {
				counter0++;
			}
			if (counter1 == (ydim / 2) || counter0 == (ydim / 2)) {
				if (!checkTwoNeighbourMove(-1, col, i)) {
					count = false;
				}
			}
		}

		return count;
	}

	public boolean invalidRow(int row) {
		boolean count = true;
		int counter1 = 0;
		int counter0 = 0;
		for (int i = 0; i < xdim; i++) {
			if (1 == getValue(i, row)) {
				counter1++;
			} else if (0 == getValue(i, row)) {
				counter0++;
			}
			if (counter1 == (xdim / 2) || counter0 == (xdim / 2)) {
				if (!checkTwoNeighbourMove(-1, i, row)) {
					count = false;
				}

			}
		}
		return count;
	}

	public void generateGrid(int gridSize) {
		int numberOfGivenValue = 0;
		int numberToRemove;
		int value = 0;
		int xcoord = 0;
		int ycoord = 0;
		boolean solveable;
		if (gridSize == 6) {
			numberOfGivenValue = 12 + random.nextInt(5);
		} else if (gridSize == 8) {
			numberOfGivenValue = 16 + random.nextInt(5);
		} else if (gridSize == 10) {
			numberOfGivenValue = 27 + random.nextInt(6);
		} else if (gridSize == 12) {
			numberOfGivenValue = 39 + random.nextInt(7);
		}

		do {
			for (int j = 0; j < xdim; j++) {
				for (int k = 0; k < ydim; k++) {
					setValue(-1, xcoord, ycoord);
				}
			}
			for (int i = 0; i < numberOfGivenValue; i++) {
				value = random.nextInt(2);
				xcoord = random.nextInt(xdim);
				ycoord = random.nextInt(ydim);
				if (checkTwoNeighbourMove(value, xcoord, ycoord)) {
					setValue(value, xcoord, ycoord);
					if ((uniqueCol() && uniqueCol() && countRow(xcoord) && countCol(ycoord) && invalidCol(xcoord)
							&& invalidRow(ycoord)) && checkNeighbours(xcoord, ycoord) && !checkEmpty(xcoord, ycoord)) {
						setValue(-1, xcoord, ycoord);
						i--;
					}
				} else {
					i--;
				}
			}
			getEmpty();
			solveable = solveBruteForce(0);
		} while (!solveable);
		numberToRemove = (gridSize * gridSize) - numberOfGivenValue;
		for (int j = 0; j <= numberToRemove; j++) {
			xcoord = random.nextInt(xdim);
			ycoord = random.nextInt(ydim);
			setValue(-1, xcoord, ycoord);
		}
	}

}
