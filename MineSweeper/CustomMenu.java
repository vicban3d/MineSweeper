package MineSweeper;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class representing a custom Game choice JFrame.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class CustomMenu extends JFrame implements ActionListener{

	private msButton bBegin; //Begin button.
	private msButton bCancel; //Cancel button.
	private JSpinner spMines; //Mines spinner.
	private JSpinner spWidth; //Width spinner.
	private JSpinner spHeight; //Height spinner.

	/**
	 * Constructor for the CustomMenu JFrame.
	 */
	public CustomMenu(){
		super("Custom Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1,0));
		this.setResizable(false);
		this.getContentPane().setBackground(Color.BLACK);
		
		JLabel mainLabel = new JLabel(new SourcedImageIcon().image("sw_main_stats2.jpg"));
		mainLabel.setLayout(new GridLayout(2,0));
		
		spMines = new JSpinner(new SpinnerNumberModel(10,1,999,1));
		spWidth = new JSpinner(new SpinnerNumberModel(9,9,999,1));
		spHeight = new JSpinner(new SpinnerNumberModel(9,9,999,1));

		spMines.setPreferredSize(new Dimension(40,20));
		spWidth.setPreferredSize(new Dimension(40,20));
		spHeight.setPreferredSize(new Dimension(40,20));

		JLabel minesLabel = new JLabel("Mines");
		JLabel widthLabel = new JLabel("Width");
		JLabel heightLabel = new JLabel("Height");

		minesLabel.setForeground(Color.WHITE);
		widthLabel.setForeground(Color.WHITE);
		heightLabel.setForeground(Color.WHITE);

		JLabel spinnersLabel = new JLabel();

		spinnersLabel.setLayout(new FlowLayout());
		spinnersLabel.add(minesLabel);
		spinnersLabel.add(spMines);
		spinnersLabel.add(widthLabel);
		spinnersLabel.add(spWidth);
		spinnersLabel.add(heightLabel);
		spinnersLabel.add(spHeight);

		bBegin = new msButton("Begin","sw_menu_rust.jpg");
		bCancel = new msButton("Cancel","sw_menu_rust.jpg");

		bBegin.addActionListener(this);
		bCancel.addActionListener(this);

		JLabel buttonsLabel = new JLabel();

		buttonsLabel.setLayout(new FlowLayout());
		buttonsLabel.add(bBegin);
		buttonsLabel.add(bCancel);

		mainLabel.add(spinnersLabel);
		mainLabel.add(buttonsLabel);

		getContentPane().add(mainLabel);

		this.setSize(new Dimension(280,100));
		setLocationRelativeTo(null); //Center window on screen.
		setVisible(true);
	}
	
	/**
	 * ActionPerformed event for menu's buttons.
	 */
	public void actionPerformed(ActionEvent e) {
		if (((msButton)e.getSource()) == bBegin){
			this.dispose();
			//get data from spinners and limit to reasonable amount of mines.
			int newWidth = (int)spWidth.getValue();
			int newHeight = (int)spHeight.getValue();
			int newMines = Math.min((int)spMines.getValue(),(newWidth*newHeight*2/3));
			Game newGame = new Game(newMines,newWidth,newHeight);	
		}
		if (((msButton)e.getSource()) == bCancel){
			this.dispose();	
			MainMenu newMenu = new MainMenu(); 
		}
	}
}
