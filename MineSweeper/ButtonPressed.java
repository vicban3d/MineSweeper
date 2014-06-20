package MineSweeper;


import java.awt.event.*;
import javax.swing.*;

/**
 * Class representing the ButtonPressed action on a Board.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class ButtonPressed implements MouseListener {

	//Booleans used to determine that both mouse buttons were pressed simultaneously.
	private boolean oneHeld = false;
	private boolean bothHeld = false;
	private boolean bothWereHeld = false;
	private boolean bothReleased = false;

	private Board board; // current Game's Board.
	private int x,y; //Coordinates of the clicked button.
	private Cell[][] array;
	private JButton[][] buttons;
	private static boolean started = false; //Checks whether the first click has been made.
	private static Timer gameTimer; //Game timer.
	private int flagCount; //Game current flag count.

	/**
	 * Constructor for the action.
	 * @param x - x position of the triggered button.
	 * @param y - y position of the triggered button.
	 * @param board - the current Game's Board.
	 */
	public ButtonPressed(int x,int y,Board board){
		this.x = x; this.y = y;
		this.array = board.getArray();
		this.buttons = board.getButtonsArray();
		this.board = board;
		gameTimer = new Timer(10,board.getGame().getStats());
	}

	/**
	 * MouseClicked action.
	 * @param e - the event trigger.
	 */
	public void mouseClicked(MouseEvent e){
		if(started == false){
			board.layMines(x,y);
			gameTimer.start();
			started = true;
		}
		if (bothReleased == false && oneHeld == false){//if both mouse buttons clicked the action was performed on release.
			if(e.getButton() == 1){//if left-clicked.
				if (array[x][y].isFlagged()){//remove flag if flagged.
					flagCount--;
					board.getGame().addMine();
					array[x][y].flag();
				}
				if (array[x][y].getNumOfMines() == 0){//if cell has no adjacent mines open all cells around recursively.
					array[x][y].openCell();
					openEmptyCells(array[x][y]);
					ImageIcon cellIcon = new SourcedImageIcon().image("sw_board_"+array[x][y].getNumOfMines()+".jpg");
					board.getButtonsArray()[x][y].setIcon(cellIcon);
				}
				else if(array[x][y].getNumOfMines() > 0){//if cell has adjacent mines open it normally.
					array[x][y].openCell();
					buttons[x][y].setIcon(new SourcedImageIcon().image("sw_board_" + array[x][y].getNumOfMines()+".jpg"));
				}
				else{//Game is lost.
					board.revealMines();
					gameTimer.stop();
					board.getGame().freeze();
					GameOver loseMessage = new GameOver(board, board.getGame().getStats().getTime());
				}
			}
			else if(e.getButton() == 3){//if right-clicked.
				if(array[x][y].getNumOfMines() == -1){//if mine set flag and disarm it.
					buttons[x][y].setIcon(new SourcedImageIcon().image("sw_board_m.jpg" ));
					array[x][y].neutralizeMine();
					array[x][y].flag();
					flagCount++;
					board.getGame().reduceNumOfMines();
				}
				else if(array[x][y].getNumOfMines() == -2){//if flag remove flag.
					buttons[x][y].setIcon(new SourcedImageIcon().image("sw_board_u.jpg" ));
					array[x][y].rearmMine();
					array[x][y].flag();
					flagCount--;
					board.getGame().addMine();
				}
				else {
					if (!(array[x][y].isOpen())){//if unopened cell set flag.
						if (!array[x][y].isFlagged()){
							buttons[x][y].setIcon(new SourcedImageIcon().image("sw_board_m.jpg" ));
							array[x][y].flag();
							flagCount++;
							board.getGame().reduceNumOfMines();
						}
						else{//if flagged cell remove flag.
							buttons[x][y].setIcon(new SourcedImageIcon().image("sw_board_u.jpg"));
							array[x][y].flag();
							flagCount--;
							board.getGame().addMine();
						}
					}
				}
			}
		}

		if(board.isWin()){//Check win condition after each click.
			gameTimer.stop();
			StringTimer endTime = board.getGame().getStats().getTime();
			board.revealMines();
			if (endTime.isRecord(Honor.getRecord(board.getDifficulty()))){
				HighScore hs = new HighScore(board.getGame(), board.getDifficulty(),endTime.getText());
				board.getGame().freeze();
			}
			else{
				GameWin winMessage = new GameWin(board,endTime);
				board.getGame().freeze();
			}
		}
	}

	/**
	 * Getter for current Game timer.
	 * @return gameTimer.
	 */
	public static Timer getTimer(){
		return gameTimer;
	}

	/**
	 * Opens the Cells starting from the given Cell recursively according to rules.
	 * @param cell
	 */
	private void openEmptyCells(Cell cell){
		if (!cell.isFlagged()){//prevent opening of flagged cells.
			cell.openCell();
			ImageIcon cellIcon = new SourcedImageIcon().image("sw_board_"+array[cell.getX()][cell.getY()].getNumOfMines()+".jpg");
			board.getButtonsArray()[cell.getX()][cell.getY()].setIcon(cellIcon);
			if(cell.getNumOfMines() == 0){
				Cell[] neighbors = board.getNeighbors(cell);
				for(int i = 0; i < neighbors.length; i++){
					if((neighbors[i] != null) && !(neighbors[i].isOpen()) && !(neighbors[i].isFlagged())){
						openEmptyCells(neighbors[i]); //perform recursively on all neighbors.
					}
				}
			}
		}
	}

	/**
	 * Change game status to Not started.
	 */
	public static void resetStarted(){
		started = false;
	}

	/**
	 * Shows which cells will be revealed by pressing both mouse buttons.
	 */
	private void showSurroundings(){
		Cell[] neighbors = board.getNeighbors(array[x][y]);
		for (int i=0; i<neighbors.length; i++){
			if (neighbors[i] != null && !neighbors[i].isFlagged() && !neighbors[i].isOpen()){
				board.getButtonsArray()[neighbors[i].getX()][neighbors[i].getY()].setIcon(new SourcedImageIcon().image("sw_board_re.jpg"));
			}
		}
		board.revalidate();
		board.repaint();
	}
	
	/**
	 * Hides the cells after revealing.
	 */
	private void hideSurroundings(){
		Cell[] neighbors = board.getNeighbors(array[x][y]);
		for (int i=0; i<neighbors.length; i++){
			if (neighbors[i] != null && !neighbors[i].isFlagged() && !neighbors[i].isOpen()){
				board.getButtonsArray()[neighbors[i].getX()][neighbors[i].getY()].setIcon(new SourcedImageIcon().image("sw_board_u.jpg"));
			}
		}
		board.revalidate();
		board.repaint();
	}


	/**
	 * MousePressed event, checks whether one or two mouse buttons were clicked.
	 * @param e - the event trigger.
	 */
	public void mousePressed(MouseEvent e) {
		if (bothReleased == true) bothReleased = false; //check whether the other button is held or not.
		if ((e.getButton() == 1 || e.getButton() == 3) && oneHeld  == true){
			showSurroundings();
			bothHeld = true;
			bothWereHeld = true;
			oneHeld = false;
		}
		else{
			oneHeld = true;
			bothWereHeld = false;
		}
	}

	/**
	 * MouseReleased event, checks whether one or two mouse buttons were released.
	 * @param e - the event trigger.
	 */
	public void mouseReleased(MouseEvent e) {
		if (bothHeld == true){
			oneHeld = true;
			bothHeld = false;
		}
		else{
			if (bothWereHeld == true && oneHeld == true){
				oneHeld = false;
				bothHeld = false;
				bothWereHeld = false;
				bothReleased = true;
				hideSurroundings();
				bothPressed();//open nearby cells as necessary.
			}
			else{
				bothHeld = false;
				bothWereHeld = false;
				oneHeld = false;
			}

		}
	}

	/**
	 * Action to perform if both mouse buttons were pressed simultaneously.
	 */
	public void bothPressed(){
		Cell[] neighbors = board.getNeighbors(array[x][y]);
		int flags = 0;
		for (int i=0; i<neighbors.length; i++){//count flags near the trigger.
			if (neighbors[i] != null && neighbors[i].isFlagged()){
				flags++;
			}
		}
		if (array[x][y].isOpen() && flags == array[x][y].getNumOfMines()){//if number of flags = number of mines open all surrounding cells.
			for (int i=0; i<neighbors.length; i++){
				if(neighbors[i] != null && neighbors[i].isMine() && !neighbors[i].isFlagged()){//if palyer made a mistake Game is Lost.
					board.revealMines();
					gameTimer.stop();
					board.getGame().freeze();
					GameOver loseMessage = new GameOver(board, board.getGame().getStats().getTime());
				}
			}
			for (int i=0; i<neighbors.length; i++){//open adjacent unflagged cells recursively.
				if (neighbors[i] != null && !neighbors[i].isFlagged()){
					if (neighbors[i].getNumOfMines() == 0 && !neighbors[i].isOpen()){
						neighbors[i].openCell();
						openEmptyCells(neighbors[i]);
						ImageIcon cellIcon = new SourcedImageIcon().image("sw_board_"+neighbors[i].getNumOfMines()+".jpg");
						board.getButtonsArray()[x][y].setIcon(cellIcon);
					}
					else if(neighbors[i].getNumOfMines() > 0){
						neighbors[i].openCell();
						board.getButtonsArray()[neighbors[i].getX()][neighbors[i].getY()].setIcon(new SourcedImageIcon().image("sw_board_" + neighbors[i].getNumOfMines()+".jpg"));
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
