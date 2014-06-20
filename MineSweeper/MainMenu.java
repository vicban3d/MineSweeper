
package MineSweeper;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

/**
 * Class representing a Mine Sweeper main menu.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class MainMenu extends JFrame implements ActionListener{

	private JLabel sideMenu; //side menu of the flame.
	private Honor honorFrame; //Honor menu.
	//MainMenu buttons.
	private msButton bNewGame;
	private msButton bHonor;
	private msButton bAbout;
	private msButton bExit;

	/**
	 * Constructor for the MainMenu JFrame.
	 */
	public MainMenu(){
		super("Mine Sweeper - Main Menu");
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setResizable(false);	

		loadScores();//read high scores from file.

		JLabel buttonsMenu = new JLabel();
		buttonsMenu.setLayout(new GridLayout(4,1));
		buttonsMenu.setPreferredSize(new Dimension(100,400));

		sideMenu = new JLabel(new SourcedImageIcon().image("sw_menu_title.jpg"));

		bNewGame = new msButton("New Game","sw_menu_rust.jpg");
		bHonor = new msButton("Honor","sw_menu_rust.jpg");
		bAbout = new msButton("About","sw_menu_rust.jpg");
		bExit = new msButton("Exit","sw_menu_rust.jpg");

		bNewGame.addActionListener(this);
		bExit.addActionListener(this);
		bHonor.addActionListener(this);
		bAbout.addActionListener(this);

		buttonsMenu.add(bNewGame);
		buttonsMenu.add(bHonor);	
		buttonsMenu.add(bAbout);
		buttonsMenu.add(bExit);	

		this.getContentPane().add(buttonsMenu, BorderLayout.WEST);
		this.getContentPane().add(sideMenu, BorderLayout.CENTER);

		setLocationRelativeTo(null); //Center window on screen.
		this.setVisible(true);

	}

	/**
	 * Reads high scores from file to present in the Honor menu.
	 */
	private void  loadScores() {
		try{
			File tFile = new File("scores.dat");
			if (!tFile.exists()) {
				tFile.createNewFile();
				ReadWriteFile.writeToFile("scores.dat", "---\n99:99:99\n---\n99:99:99\n---\n99:99:99");
			}
		}
		catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	/**
	 * ActionPerformed for MainMenu's buttons.
	 */
	public void actionPerformed(ActionEvent e) {
		if (((msButton)e.getSource()) == bNewGame){//change side menu.
			this.getContentPane().remove(sideMenu);
			sideMenu = new SideMenu();
			((SideMenu)sideMenu).setMainMenu(this);
			this.getContentPane().add(sideMenu);
			this.revalidate(); 
		}
		if (((msButton)e.getSource()) == bHonor){ //open honor frame.
			if (honorFrame == null)honorFrame = new Honor();
			if (!honorFrame.isVisible()) honorFrame = new Honor();
		}
		if (((msButton)e.getSource()) == bAbout){//change side menu.
			this.getContentPane().remove(sideMenu);
			sideMenu = new JLabel(new SourcedImageIcon().image("sw_main_about.jpg"));
			this.getContentPane().add(sideMenu);
			this.revalidate(); 
		}
		if (((msButton)e.getSource()) == bExit){//terminate program.
			System.exit(0); 
		}
		if (((JButton)e.getSource()).getName() == "sideBegin"){//close main menu at start of new game.
			dispose(); 
		}

	}
}


