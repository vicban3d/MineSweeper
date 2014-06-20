package MineSweeper;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Class representing an Honor frame which displays record times.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class Honor extends JFrame implements ActionListener{


	private msButton bClose; //Close button.
	private msButton bReset; //Reset Scores button.
	//Labels for player names and records at each difficulty level.
	private JLabel easyNameLabel;
	private JLabel easyTimeLabel;
	private JLabel mediumNameLabel;
	private JLabel mediumTimeLabel;
	private JLabel hardNameLabel;
	private JLabel hardTimeLabel;

	/**
	 * Constructor for the Honor JFrame.
	 */
	public Honor(){
		super("Honor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		JLabel mainLabel = new JLabel(new SourcedImageIcon().image("sw_main_honor.jpg"));
		mainLabel.setLayout(new GridLayout(2,1));

		JLabel scoresLabel = new JLabel();
		scoresLabel.setLayout(new GridLayout());

		JLabel buttonsLabel = new JLabel();
		buttonsLabel.setLayout(new FlowLayout());

		bClose = new msButton("Close","sw_menu_rust.jpg");
		bReset = new msButton("Reset","sw_menu_rust.jpg");

		JLabel easyLabel = new JLabel();
		easyLabel.setLayout(new GridLayout(6,1,1,0));
		easyNameLabel = setPlayerName();
		easyTimeLabel = setRecordTime();
		easyLabel.add(easyNameLabel);
		easyLabel.add(easyTimeLabel);

		JLabel mediumLabel = new JLabel();
		mediumLabel.setLayout(new GridLayout(6,1,1,0));
		mediumNameLabel = setPlayerName();
		mediumTimeLabel = setRecordTime();
		mediumLabel.add(mediumNameLabel);
		mediumLabel.add(mediumTimeLabel);

		JLabel hardLabel = new JLabel();
		hardLabel.setLayout(new GridLayout(6,1,1,0));
		hardNameLabel = setPlayerName();
		hardTimeLabel = setRecordTime();
		hardLabel.add(hardNameLabel);
		hardLabel.add(hardTimeLabel);

		bClose.addActionListener(this);
		bReset.addActionListener(this);

		buttonsLabel.add(bClose);
		buttonsLabel.add(bReset);

		scoresLabel.add(hardLabel);
		scoresLabel.add(mediumLabel);
		scoresLabel.add(easyLabel);

		mainLabel.add(scoresLabel);
		mainLabel.add(buttonsLabel);


		getContentPane().add(mainLabel);

		setSize(400,400);
		setLocationRelativeTo(null); //Center window on screen.
		readScore(); //Read Scores from file("scores.dat").
		setVisible(true);
	}

/**
 * ActionPerformed for the Honor's buttons.
 */
	public void actionPerformed(ActionEvent e) {
		if ((msButton)e.getSource() == bClose){
			dispose();
		}
		if ((msButton)e.getSource() == bReset){
			resetScores();
		}
	}

	/**
	 * Reset all scores to initial value.
	 */
	public void resetScores(){
		ReadWriteFile.writeToFile("scores.dat", "---\n99:99:99\n---\n99:99:99\n---\n99:99:99");

		String tFileContent = ReadWriteFile.readFromFile("scores.dat");
		StringTokenizer tokenizer = new StringTokenizer(tFileContent);

		easyNameLabel.setText(tokenizer.nextToken());
		easyTimeLabel.setText(tokenizer.nextToken());
		mediumNameLabel.setText(tokenizer.nextToken());
		mediumTimeLabel.setText(tokenizer.nextToken());
		hardNameLabel.setText(tokenizer.nextToken());
		hardTimeLabel.setText(tokenizer.nextToken());

		this.revalidate();
	}

	/**
	 * Read scores from file and update labels accordingly.
	 */
	public void readScore(){
		String tFileContent = ReadWriteFile.readFromFile("scores.dat");
		StringTokenizer tokenizer = new StringTokenizer(tFileContent);

		easyNameLabel.setText(tokenizer.nextToken());
		easyTimeLabel.setText(tokenizer.nextToken());
		mediumNameLabel.setText(tokenizer.nextToken());
		mediumTimeLabel.setText(tokenizer.nextToken());
		hardNameLabel.setText(tokenizer.nextToken());
		hardTimeLabel.setText(tokenizer.nextToken());
	}

	/**
	 * Write sacores to file according to given difficulty, name and time.
	 * @param difficulty - Game's difficulty level(easy/medium/hard).
	 * @param name - Player's name.
	 * @param score - Record game time.
	 */
	public static void writeScores(String difficulty, String name, String score){
		String tFileContent = ReadWriteFile.readFromFile("scores.dat");
		StringTokenizer tokenizer = new StringTokenizer(tFileContent);
		String s = "";


		if (difficulty == "easy"){
			tokenizer.nextToken();
			tokenizer.nextToken();
			s = s + name + "\n";
			s = s + score + "\n";
			s = s + tokenizer.nextToken()+"\n";
			s = s + tokenizer.nextToken()+"\n";
			s = s + tokenizer.nextToken()+"\n";
			s = s + tokenizer.nextToken();
		}

		if (difficulty == "medium"){
			s = s + tokenizer.nextToken()+"\n";
			s = s + tokenizer.nextToken()+"\n";
			tokenizer.nextToken();
			tokenizer.nextToken();
			s = s + name + "\n";
			s = s + score + "\n";
			s = s + tokenizer.nextToken()+"\n";
			s = s + tokenizer.nextToken();
		}

		if (difficulty == "hard"){
			s = s + tokenizer.nextToken()+"\n";
			s = s + tokenizer.nextToken()+"\n";
			s = s + tokenizer.nextToken()+"\n";
			s = s + tokenizer.nextToken()+"\n";
			s = s + name + "\n";
			s = s + score;
		}
		ReadWriteFile.writeToFile("scores.dat", s);
	}

	/**
	 * Getter for the current record in the given difficulty.
	 * @param difficulty - Game's difficulty.
	 * @return - record time String.
	 */
	public static String getRecord(String difficulty){
		String tFileContent = ReadWriteFile.readFromFile("scores.dat");
		StringTokenizer tokenizer = new StringTokenizer(tFileContent);

		if (difficulty == "easy"){
			tokenizer.nextToken();
			return tokenizer.nextToken();
		}

		if (difficulty == "medium"){
			tokenizer.nextToken();
			tokenizer.nextToken();
			tokenizer.nextToken();
			return tokenizer.nextToken();
		}

		if (difficulty == "hard"){
			tokenizer.nextToken();
			tokenizer.nextToken();
			tokenizer.nextToken();
			tokenizer.nextToken();
			tokenizer.nextToken();
			return tokenizer.nextToken();
		}
		return "00:00:00";
	}

	/**
	 * Setter for initial value of name labels.
	 * @return  JLabel with initial parameters.
	 */
	public JLabel setPlayerName(){
		JLabel label = new JLabel("---",JLabel.CENTER);
		label.setLayout(new GridLayout(6,1,1,0));
		label.setForeground(Color.WHITE);
		label.setFont(new Font(label.getFont().getName(),Font.BOLD,14));
		return label;
	}

	/**
	 * Setter for initial value of record labels.
	 * @return JLabel with initial parameters.
	 */
	public JLabel setRecordTime(){
		JLabel label = new JLabel("00:00:00",JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font(label.getFont().getName(),Font.BOLD,14));
		return label;
	}
}
