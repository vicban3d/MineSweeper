package MineSweeper;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Class representing a High Score prompt frame.
* @author Victor Banshats
* @author Gavriel Giladov
*/
public class HighScore extends JFrame implements ActionListener{
	
	private String recordTime;//Current record time.
	private String gameDifficulty; //Game's difficulty.
	private JTextField playerName; //Player's name.
	private msButton bSubmit; //Submit button.
	private Game currentGame; //Current Game.
	
	/**
	 * Constructor for the HighScore JFrame.
	 * @param game - Current game.
	 * @param difficulty - Current Game's difficulty.
	 * @param gameTime - Current Game's time.
	 */
	public HighScore(Game game, String difficulty, String gameTime){
		
		super("Victory and a High Score!");
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280,70);
		this.setResizable(false);
		
		currentGame = game;
		
		recordTime = gameTime;
		gameDifficulty = difficulty;
		playerName = new JTextField("Player");
		
		JLabel backgroundLabel = new JLabel(new SourcedImageIcon().image("sw_main_stats2.jpg"));
		backgroundLabel.setLayout(new FlowLayout());
		
		
		playerName.setPreferredSize(new Dimension(120,30));
		playerName.setForeground(Color.WHITE);
		playerName.setBackground(Color.GRAY);
		playerName.setFont(new Font(playerName.getFont().getName(),Font.BOLD,playerName.getFont().getSize()));
		
		JLabel recordLabel = new JLabel(recordTime);
		recordLabel.setForeground(Color.WHITE);
		recordLabel.setFont(new Font(recordLabel.getFont().getName(),Font.BOLD,recordLabel.getFont().getSize()));
		
		bSubmit = new msButton("Submit","sw_menu_rust.jpg");
		bSubmit.addActionListener(this);
		
		backgroundLabel.add(playerName);
		backgroundLabel.add(recordLabel);
		backgroundLabel.add(bSubmit);
		
		
		this.getContentPane().add(backgroundLabel);
		
		setLocationRelativeTo(null); //Center window on screen.
		setVisible(true);
	}

	/**
	 * ActionPerformed for the menu's buttons.
	 */
	public void actionPerformed(ActionEvent e) {
		Honor.writeScores(gameDifficulty, playerName.getText(), recordTime);
		MainMenu newMenu = new MainMenu();
		currentGame.getBoard().restart();
		currentGame.dispose();
		dispose();
	}
}
