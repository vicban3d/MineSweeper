package MineSweeper;


import java.awt.*;
import javax.swing.*;

/**
 * Class representing the Mine Sweeper board.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class Board extends JPanel{

	private Cell[][] array; //Program cells array.
	private JButton[][] buttons; //GUI cells array.
	private int height;
	private int width;
	private Game game; //Game to which the Board belongs.

	/**
	 * Constructer of the board.
	 * @param game - the game to which the board belongs.
	 * @param newHeight - number of rows in game's grid.
	 * @param newWidth - number of columns in game's grid.
	 */
	public Board(Game game,  int newHeight, int newWidth){
		super(new GridLayout(newWidth, newHeight));
		this.width = newWidth;
		this.height = newHeight;
		this.game = game;
		this.setBackground(Color.BLACK);

		msButton b;
		array = new Cell[width][height];
		buttons = new JButton[width][height];

		for (int i = 0; i < width; i++){ //Initiate Board.
			for(int j = 0; j < height; j++){
				array[i][j] = new Cell(i, j);		
				b = new msButton("","sw_board_u.jpg");
				b.addMouseListener(new ButtonPressed(i,j,this));
				this.add(b);
				buttons[i][j] = b;
			}
		}
	}

	/**
	 * Reset the board to initial state.
	 */
	public void restart(){
		for (int i = 0; i < width; i++)
			for(int j = 0; j < height; j++){
				array[i][j] = new Cell(i, j);
				buttons[i][j].setIcon(new SourcedImageIcon().image("sw_board_u.jpg"));
				buttons[i][j].revalidate();
			}
		ButtonPressed.getTimer().stop(); //Stop game timer.
		ButtonPressed.resetStarted(); //Reset mine count.
		game.restart();
	}

	/**
	 * Lay mines on the board.
	 * @param x - first click x position.
	 * @param y - first click y position.
	 */
	public  void layMines(int x, int y){//Lay mines on Board.
		int indexX = -1, indexY = -1;

		for(int i = 0; i < game.getNumOfMines(); i++){//Get random position of next mine.
			indexX = (int) ( indexX + 1 + Math.floor( Math.random() * ( width - 1 ) ) ) % width;
			indexY = (int) ( indexY + 1 + Math.floor( Math.random() * ( height - 1 ) ) ) % height;;
			if (indexX == x && indexY == y || array[indexX][indexY].isMine()){
				i--;
			}
			else{
				array[indexX][indexY].layMine();				
				Cell[] neighbors = getNeighbors(array[indexX][indexY]);
				for(int j = 0; j < neighbors.length; j++){//increase mine count of cell's neighbors.
					if((neighbors[j] != null) && (!neighbors[j].isMine())){
						neighbors[j].addMine();
					}
				}
			}
		}
	}

	/**
	 * Game win condition.
	 * @return True/False.
	 */
	public boolean isWin(){//Game win condition - no unraveled cell other than a mine remains.
		for (int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				if(!array[i][j].isMine() && !array[i][j].isOpen())
					return false;
		return true;
	}

	/**
	 * Getter for the program array.
	 * @return array.
	 */
	public Cell[][] getArray(){
		return array;
	}

	/**
	 * Getter for the GUI array of buttons.
	 * @return buttons.
	 */
	public JButton[][] getButtonsArray(){
		return buttons;
	}

	/**
	 * Getter for board's current Game.
	 * @return - game.
	 */
	public Game getGame(){
		return game;
	}

	/**
	 * Getter for the neighbors of a given cell.
	 * @param cell - the cell to check.
	 * @return an array of cells with the neighbors.
	 */
	public Cell[] getNeighbors(Cell cell){
		Cell[] neighbors;
		int x = cell.getX(), y = cell.getY();
		neighbors = new Cell[8];

		if (x < array.length - 1) 								neighbors[0] = array[x + 1][y];
		if (x < array.length - 1 && y < array[0].length - 1) 	neighbors[1] = array[x + 1][y + 1];
		if (x < array.length - 1 && y > 0)						neighbors[2] = array[x + 1][y - 1];
		if (y < array[0].length -1 )							neighbors[3] = array[x][y + 1];
		if (y > 0)												neighbors[4] = array[x][y - 1];
		if (x > 0)												neighbors[5] = array[x - 1][y];
		if (y < array[0].length - 1 && x > 0)					neighbors[6] = array[x - 1][y + 1];
		if (x > 0 && y > 0)										neighbors[7] = array[x - 1][y - 1];

		return neighbors;
	}

	/**
	 * Reveals all the mines on the Board.
	 */
	public void revealMines(){
		for (int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				if(array[i][j].isMine()){
					buttons[i][j].setIcon(new SourcedImageIcon().image("sw_board_r.jpg"));
				}
				if(!array[i][j].isMine() && array[i][j].isFlagged()){
					buttons[i][j].setIcon(new SourcedImageIcon().image("sw_board_rx.jpg"));
					buttons[i][j].revalidate();
				}
				buttons[i][j].revalidate();
			}
		}
	}

	/**
	 * Getter for the current difficulty of the game.
	 * @return a String representation of the difficulty(easy/medium/hard/custom).
	 */
	public String getDifficulty(){//Returns the difficulty of the current Game.

		if (width == 9 && height == 9 && game.getInitialMines() == 10){
			return "easy";
		}
		if (width == 16 && height == 16 && game.getInitialMines() == 40){
			return "medium";
		}
		if (width == 30 && height == 16 && game.getInitialMines() == 99){
			return "hard";
		}
		return "custom";
	}
}
