package MineSweeper;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Class representing a Game Win menu for the Game.
* @author Victor Banshats
* @author Gavriel Giladov
*/
public class GameWin  extends JFrame implements ActionListener{

	private Board gameBoard; //current Game's Board. 
	private msButton bRetry; //Retry button.
	private msButton bQuit; //Quit button.
/**
 * Constructor for the GameWin JFrame.
 * @param board - Current Game's Board.
 * @param time - Time at moment of victory.
 */
	public GameWin(Board board, StringTimer time){
		super("Victory!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		gameBoard = board;

		JLabel backgroundLabel = new JLabel(new SourcedImageIcon().image("sw_main_win.jpg"));
		backgroundLabel.setLayout(new FlowLayout());

		JLabel buttonsLabel = new JLabel();
		buttonsLabel.setLayout(new FlowLayout());

		JLabel timeLabel = new JLabel(time.getText());
		timeLabel.setFont(new Font(timeLabel.getFont().getName(),Font.BOLD,26));
		timeLabel.setForeground(Color.WHITE);

		bRetry = new msButton("Play Again","sw_menu_rust.jpg");
		bQuit = new msButton("Quit","sw_menu_rust.jpg");

		bQuit.addActionListener(this);
		bRetry.addActionListener(this);

		buttonsLabel.add(bRetry);
		buttonsLabel.add(bQuit);

		buttonsLabel.setPreferredSize(new Dimension(160,30));
		timeLabel.setPreferredSize(new Dimension(220,30));

		backgroundLabel.add(buttonsLabel);
		backgroundLabel.add(timeLabel);

		getContentPane().add(backgroundLabel);
		
		this.pack();
		this.setLocation((board.getGame().getLocation().x+board.getGame().getWidth()/2), board.getGame().getLocation().y+board.getGame().getHeight()/2);
		setVisible(true);
	}
	
	/**
	 * ActionPerformed for the Game Win menu.
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