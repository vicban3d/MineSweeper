package MineSweeper;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class representing a statistics panel of the Game.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class Stats extends JLabel implements ActionListener{
	
	private StringTimer timer; //current Game time.
	private JLabel timeLabel; 
	private JLabel minesLabel;
	private int numOfMines; //current number of mines.

	/**
	 * Constructor for the Stats JLabel.
	 * @param mines - initial number of mines.
	 */
	public Stats(int mines){
		this.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
		this.setLayout(new FlowLayout());
		numOfMines = mines;
		
		JLabel leftLabel = new JLabel();
		JLabel rightLabel = new JLabel();
		leftLabel.setLayout(new FlowLayout());
		rightLabel.setLayout(new FlowLayout());
		leftLabel.setPreferredSize(new Dimension(88, 40));
		rightLabel.setPreferredSize(new Dimension(80, 40));
		
		JLabel timerIcon = new JLabel(new SourcedImageIcon().image("sw_menu_watch.jpg"));
		
		timer = new StringTimer();
		timeLabel = new JLabel(timer.getText());
		JLabel minesIcon = new JLabel(new SourcedImageIcon().image("sw_menu_mine.jpg"));
		minesLabel = new JLabel(""+numOfMines);
		timeLabel.setForeground(Color.WHITE);
		minesLabel.setForeground(Color.WHITE);
		minesLabel.setPreferredSize(new Dimension(30,30));
		
		rightLabel.add(minesLabel, FlowLayout.LEFT);
		rightLabel.add(minesIcon, FlowLayout.LEFT);
		
		leftLabel.add(timeLabel, FlowLayout.LEFT);
		leftLabel.add(timerIcon, FlowLayout.LEFT);
		
		this.add(rightLabel, FlowLayout.LEFT);
		this.add(leftLabel, FlowLayout.LEFT);
	}
	
	/**
	 * Reduces one mine from the count.
	 */
	public void deductMine(){
		int cMines = Integer.parseInt(minesLabel.getText());
		minesLabel.setText(""+(cMines-1));
	}
	
	/**
	 * adds one mine to the count.
	 */
	public void addMine(){
		int cMines = Integer.parseInt(minesLabel.getText());
		minesLabel.setText(""+(cMines+1));
	}
	
	/**
	 * Getter for the current time.
	 * @return timer.
	 */
	public StringTimer getTime(){
		return timer;
	}
	
	/**
	 * Resets the current stats to initial state.
	 */
	public void reset(){
		minesLabel.setText(""+numOfMines);
		timer.reset();
		timeLabel.setText(timer.getText());
		this.revalidate();
	}

	/**
	 * ActionPerformed event for the Game's timer.
	 */
	public void actionPerformed(ActionEvent e) {
			timer.increase();
			timeLabel.setText(timer.getText());
			this.revalidate();
	}
}
