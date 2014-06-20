package MineSweeper;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Class representing a Game Over menu for the Game.
* @author Victor Banshats
* @author Gavriel Giladov
*/
public class GameOver  extends JFrame implements ActionListener{

	private Board gameBoard;//game's current Board.
	private msButton bRetry; //Retry button.
	private msButton bQuit; //Quit button.

	/**
	 * Constructor for the GameOver JFrame.
	 * @param board - Current Game's board.
	 * @param time - Time at the end of the Game.
	 */
	public GameOver(Board board, StringTimer time){
		super("Gave Over");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		gameBoard = board;
		JLabel backgroundLabel = new JLabel(new SourcedImageIcon().image("sw_main_lose.jpg"));
		backgroundLabel.setLayout(new FlowLayout());

		JLabel buttonsLabel = new JLabel();
		buttonsLabel.setLayout(new FlowLayout());

		JLabel timeLabel = new JLabel(time.getText());
		timeLabel.setFont(new Font(timeLabel.getFont().getName(),Font.BOLD,26));

		bRetry = new msButton("Retry","sw_menu_rust.jpg");
		bQuit = new msButton("Quit","sw_menu_rust.jpg");

		bQuit.addActionListener(this);
		bRetry.addActionListener(this);

		buttonsLabel.add(bRetry);
		buttonsLabel.add(bQuit);

		buttonsLabel.setPreferredSize(new Dimension(140,30));
		timeLabel.setPreferredSize(new Dimension(240,30));

		backgroundLabel.add(buttonsLabel);
		backgroundLabel.add(timeLabel);

		getContentPane().add(backgroundLabel);
		
		this.pack();
		this.setLocation((board.getGame().getLocation().x+board.getGame().getWidth()/2), board.getGame().getLocation().y+board.getGame().getHeight()/2);
		setVisible(true);
	}
	
	/**
	 * ActionPerformed for the menu's buttons.
	 */
		public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bQuit){
			gameBoard.restart();
			gameBoard.getGame().dispose();
			MainMenu newMenu = new MainMenu();
			dispose();
		}
		if (e.getSource() == bRetry){
			gameBoard.getGame().unfreeze();
			gameBoard.restart();
			dispose();
		}
	}
}