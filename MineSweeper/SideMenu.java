package MineSweeper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



/**
 * Class representing a side menu of the Mine Sweeper's main menu.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class SideMenu extends JLabel implements ActionListener{

	MainMenu mainMenu;
	public int selected = 0;
	public ButtonGroup choices = new ButtonGroup();
	//Difficulty choice JRadioButtons
	JRadioButton choice1 = new JRadioButton("Easy");
	JRadioButton choice2 = new JRadioButton("Medium");
	JRadioButton choice3 = new JRadioButton("Hard");
	JRadioButton choice4 = new JRadioButton("Custom");
	//Difficulty image labels.
	JLabel topImage = new JLabel(new SourcedImageIcon().image("sw_menu_easy.jpg"));
	JLabel bottomImage = new JLabel(new SourcedImageIcon().image("sw_menu_easy.jpg"));

	/**
	 * Constructor for the SideMenu JLabel.
	 */
	public SideMenu(){
		super();
		this.setPreferredSize(new Dimension(300,400));
		this.setLayout(new BorderLayout());

		choice1.addActionListener(this);
		choice2.addActionListener(this);
		choice3.addActionListener(this);
		choice4.addActionListener(this);

		choice1.setIcon(new SourcedImageIcon().image("sw_main_stats_rust.jpg"));
		choice2.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
		choice3.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
		choice4.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));

		JLabel cEasy = new JLabel("     Easy");
		choice1.add(cEasy);
		JLabel cMedium = new JLabel("     Medium");
		choice2.add(cMedium);
		JLabel cHard = new JLabel("     Hard");
		choice3.add(cHard);
		JLabel cCustom = new JLabel("     Custom");
		choice4.add(cCustom);

		choice1.setBackground(Color.DARK_GRAY);
		choice2.setBackground(Color.DARK_GRAY);
		choice3.setBackground(Color.DARK_GRAY);
		choice4.setBackground(Color.DARK_GRAY);

		choice1.setSelected(true);

		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		choices.add(choice4);

		JLabel rightLabel = new JLabel(new SourcedImageIcon().image("sw_main_black.jpg"));
		topImage = new JLabel(new SourcedImageIcon().image("sw_menu_easy.jpg"));

		bottomImage = new JLabel(new SourcedImageIcon().image("sw_menu_easy.jpg"));

		rightLabel.setLayout(new GridLayout(3,1));
		rightLabel.setPreferredSize(new Dimension(194,400));

		msButton start =  new msButton("","sw_menu_begin.jpg");
		start.setName("sideBegin");
		start.addActionListener(this);
		start.addActionListener(mainMenu);

		JLabel choiceLabel = new JLabel();
		choiceLabel.setLayout(new GridLayout(4,1));
		choiceLabel.setPreferredSize(new Dimension(100,400));

		choiceLabel.add(choice1);
		choiceLabel.add(choice2);
		choiceLabel.add(choice3);
		choiceLabel.add(choice4);

		rightLabel.add(topImage);
		rightLabel.add(start);
		rightLabel.add(bottomImage);

		this.add(choiceLabel, BorderLayout.WEST);
		this.add(rightLabel,BorderLayout.EAST);
		this.setVisible(true);

	}

	/**
	 * Getter for the current selection of the SideMenu.
	 * @return - currently selected choice.
	 */
	public JRadioButton getSelected(){
		if (choice1.isSelected()) return choice1;
		else if (choice2.isSelected()) return choice2;
		else if (choice3.isSelected()) return choice3;
		else return choice4;
	}

	public void setMainMenu(MainMenu menu){//set to previous main menu to close it if necessary.
		mainMenu = menu;
	}


	/**
	 * ActionPerformed for SideMenu's buttons.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JRadioButton){
			if ((JRadioButton)e.getSource() == choice1){
				topImage.setIcon(new SourcedImageIcon().image("sw_menu_easy.jpg"));
				bottomImage.setIcon(new SourcedImageIcon().image("sw_menu_easy.jpg"));

				choice1.setIcon(new SourcedImageIcon().image("sw_main_stats_rust.jpg"));
				choice2.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice3.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice4.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));

				repaint();
			}
			if ((JRadioButton)e.getSource() == choice2){
				topImage.setIcon(new SourcedImageIcon().image("sw_menu_medium.jpg"));
				bottomImage.setIcon(new SourcedImageIcon().image("sw_menu_medium.jpg"));

				choice1.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice2.setIcon(new SourcedImageIcon().image("sw_main_stats_rust.jpg"));
				choice3.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice4.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));

				repaint();
			}
			if ((JRadioButton)e.getSource() == choice3){
				topImage.setIcon(new SourcedImageIcon().image("sw_menu_hard.jpg"));
				bottomImage.setIcon(new SourcedImageIcon().image("sw_menu_hard.jpg"));

				choice1.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice2.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice3.setIcon(new SourcedImageIcon().image("sw_main_stats_rust.jpg"));
				choice4.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));

				repaint();
			}
			if ((JRadioButton)e.getSource() == choice4){
				topImage.setIcon(new SourcedImageIcon().image("sw_menu_custom.jpg"));
				bottomImage.setIcon(new SourcedImageIcon().image("sw_menu_custom.jpg"));

				choice1.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice2.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice3.setIcon(new SourcedImageIcon().image("sw_main_stats2.jpg"));
				choice4.setIcon(new SourcedImageIcon().image("sw_main_stats_rust.jpg"));

				repaint();
			}
		}
		if (e.getSource() instanceof JButton){
			mainMenu.dispose();
			if (choice1.isSelected()){
				Game newGame = new Game(10,9,9);	
			}
			if (choice2.isSelected()){
				Game newGame = new Game(40,16,16);	
			}
			if (choice3.isSelected()){
				Game newGame = new Game(99,30,16);	
			}
			if (choice4.isSelected()){
				CustomMenu custom = new CustomMenu();	
				mainMenu.dispose();
			}
		}

	}



}
