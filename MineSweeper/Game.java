package MineSweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class representing a Mine Sweeper Game.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class Game extends JFrame implements ActionListener{

	private JButton bRestart; //Restart button.
	private JButton bQuit; //Quit button.
	private JButton bHonor; //Honor menu button.
	private int numOfMines; //Current number of mines.
	private  int initialNumOfMines; //Initial number of mines.
	private Board board; //Game's Board.
	private int boardWidth; 
	private int boardHeight;
	private Honor honorFrame;
	private Stats stats; //Stats panel for the Game.

	/**
	 * Game's constructor.
	 * @param mines - Initial number of mines.
	 * @param width - Initial width of the Board.
	 * @param height - Initial height of the Board.
	 */
	public Game(int mines, int width, int height) {
		super("Minesweeper");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		this.setResizable(false);
		
		stats = new Stats(mines);

		boardWidth = width;
		boardHeight = height;

		bRestart = new msButton("Reset","sw_menu_rust.jpg");
		bRestart.addActionListener(this);
		bRestart.setPreferredSize(new Dimension(68,30));

		bHonor = new msButton("Honor","sw_menu_rust.jpg");
		bHonor.addActionListener(this);
		bHonor.setPreferredSize(new Dimension(68,30));

		bQuit = new msButton("Quit","sw_menu_rust.jpg");
		bQuit.addActionListener(this);
		bQuit.setPreferredSize(new Dimension(58,30));

		JLabel buttonsPanel = new JLabel(new SourcedImageIcon().image("sw_main_stats2.jpg"));
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(bHonor);
		buttonsPanel.add(bRestart);
		buttonsPanel.add(bQuit);

		numOfMines = mines;
		initialNumOfMines = mines;

		board = new Board(this, width, height);

		getContentPane().add(stats, BorderLayout.NORTH);
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		getContentPane().add(board , BorderLayout.CENTER);

		setSize(width*24,height*24+100);
		setLocationRelativeTo(null); //Center window on screen.
		setVisible(true);
	}

	/**
	 * Getter for the games current Stats.
	 * @return - stats.
	 */
	public Stats getStats(){
		return stats;
	}

	/**
	 * ActionPerformed for the Game's buttons.
	 */
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == bRestart){
			board.restart();
		}
		if (e.getSource() == bQuit){
			board.restart();
			MainMenu newMenu = new MainMenu();
			this.dispose();
		}
		if (e.getSource() == bHonor) {
			if (honorFrame == null)honorFrame = new Honor();
			if (!honorFrame.isVisible()) honorFrame = new Honor();
		}
	}

	/**
	 * Getter for the current number of mines.
	 * @return numOfMines.
	 */
	public int getNumOfMines(){
		return numOfMines;
	}

	/**
	 * Getter for the Initial number of mines.
	 * @return initialNumOfMines.
	 */
	public int getInitialMines(){
		return initialNumOfMines;
	}

	/**
	 * Reduces the number of mines by one.
	 */
	public void reduceNumOfMines(){
		stats.deductMine();
		numOfMines--;
		stats.revalidate();
	}

	/**
	 * increases the number of mines by one.
	 */
	public void addMine(){
		stats.addMine();
		numOfMines++;
		stats.revalidate();
	}

	/**
	 * Restarts the Game.
	 */
	public void restart(){
		numOfMines = initialNumOfMines;
		stats.reset();
		stats.revalidate();
	}
	
	/**
	 * Getter for the Game's Board.
	 * @return board.
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * Renders the Game disabled.
	 */
	public void freeze(){
		setEnabled(false);
	}

	/**
	 * Renders the Game enabled.
	 */
	public void unfreeze(){
		setEnabled(true);
	}
}
