package MineSweeper;
import java.awt.*;

import javax.swing.*;


/**
 * Class representing a Mine Sweeper button.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class msButton extends JButton {
	
	/**
	 * Empty constructor.
	 */
	public msButton(){

	}
	
	/**
	 * Constructor for the button with given parameters.
	 * @param txt - button's label.
	 * @param bg - button's background image.
	 */
	public msButton(String txt, String bg){
		super(new SourcedImageIcon().image(bg));
		JLabel text = new JLabel(txt);
		text.setAlignmentX(CENTER_ALIGNMENT);
		text.setForeground(Color.WHITE);
		add(text);	
	}
}
