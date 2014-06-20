package MineSweeper;


/**
 * Class representing a Cell in a Game's array.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class Cell {
	
	private int numOfMines; //total number of adjacent mines.
	private boolean open; //cell is opened or closed.
	private boolean flagged; //cell is flagegd or unflagged.
	private int xBoard; //cell's x position.
	private int yBoard; //cell's y position.

	/**
	 * Empty constructor for the Cell.
	 */
	public Cell(){
		numOfMines = 0;
		open = false;
		flagged = false;
		xBoard = 0;
		yBoard = 0;
	}
	
	/**
	 * Constructor for the Cell.
	 * @param xBoard - cell's x position.
	 * @param yBoard - cell's y position.
	 */
	public Cell(int xBoard, int yBoard){
		numOfMines = 0;
		open = false;
		flagged = false;
		this.xBoard = xBoard;
		this.yBoard = yBoard;
	}
	
	/**
	 * Flag or unflag the current Cell.
	 */
	public void flag(){
		flagged = !flagged;
	}
	
	/**
	 * checks whether the current cell is flagged.
	 * @return - True/False.
	 */
	public boolean isFlagged(){
		return flagged;
	}
	
	/**
	 * Adds one mine to the cell's count.
	 */
	public void addMine(){
		numOfMines++;
	}
	
	/**
	 * Checks whether the cell had been opened.
	 * @return - True/False.
	 */
	public boolean isOpen(){
		return open;
	}
	
	/**
	 * Opens the Cell.
	 */
	public void openCell(){
		open = true;
	}
	
	/**
	 * Places a mine on the Cell.
	 */
	public void layMine(){
		numOfMines = -1;
	}
	
	/**
	 * Checks whether current Cell is a mine.
	 * @return - True/False.
	 */
	public boolean isMine(){
		return numOfMines == -1 || numOfMines == -2;
	}
	
	/**
	 * Neutrilizes the mine if it had been flagged.
	 */
	public void neutralizeMine(){
		if(this.numOfMines == -1)
			this.numOfMines = -2;
	}
	
	/**
	 * Rearms mine of flag was removed.
	 */
	public void rearmMine(){
		if(this.numOfMines == -2)
			this.numOfMines = -1;
	}
	
	/**
	 * Getter for current number of adjacent mines.
	 * @return numOfMines.
	 */
	public int getNumOfMines(){
		return numOfMines;
	}

	/**
	 * Getter for Cell's x position.
	 * @return xBoard.
	 */
	public int getX() {
		return xBoard;
	}
	
	/**
	 * Setter for Cell's x position.
	 * @param xBoard.
	 */
	public void setX(int xBoard){
		this.xBoard = xBoard;
	}
	
	/**
	 * Getter for Cell's y position.
	 * @return xBoard.
	 */
	public int getY() {
		return yBoard;
	}

	/**
	 * Setter for Cell's y position.
	 * @param xBoard.
	 */
	public void setY(int yBoard) {
		this.yBoard = yBoard;
	}
}
