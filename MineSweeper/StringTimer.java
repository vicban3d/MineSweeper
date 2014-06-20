package MineSweeper;

/**
 * Class representing a timer for the Game.
 * @author Victor Banshats
 * @author Gavriel Giladov
 */
public class StringTimer{

	private String m; //minutes.
	private String s; //seconds.
	private String ms; //milliseconds.
	private String text; //timer text.

	/**
	 * Constructor for a new timer.
	 */
	public StringTimer(){
		m = "00";
		s = "00";
		ms = "00";
		text = m+":"+s+":"+ms;
	}

	/**
	 * Increases milliseconds by 1.
	 */
	public void increaseMS(){
		int newMS = Integer.parseInt(ms) +1;
		if (newMS > 99){
			newMS = 0;
		}
		if ( newMS < 10){
			ms = "0"+newMS;
		}
		else
		{
			ms = ""+newMS;
		}
		text = m+":"+s+":"+ms;
	}

	/**
	 * Increases seconds by 1.
	 */
	public void increaseS(){
		int newS = Integer.parseInt(s) +1;
		if (newS > 59){
			newS = 0;
		}
		if ( newS < 10){
			s = "0"+newS;
		}
		else
		{
			s = ""+newS;
		}
		text = m+":"+s+":"+ms;
	}

	/**
	 * Increases minutes by 1.
	 */
	public void increaseM(){
		int newM = Integer.parseInt(m) +1;
		if (newM < 100){
			if ( newM < 10){
				m = "0"+newM;
			}
			else
			{
				m = ""+newM;
			}
		}
		text = m+":"+s+":"+ms;
	}

	/**
	 * increases overall time.
	 */
	public void increase(){
		increaseMS();
		if (Integer.parseInt(ms) == 0){
			increaseS();
		}
		if (Integer.parseInt(s) == 59 && Integer.parseInt(ms) == 99){
			increaseM();
		}
	}

	/**
	 * Getter for the curent time.
	 * @return timer text.
	 */
	public String getText(){
		return text;
	}

	/**
	 * Resets the timer.
	 */
	public void reset(){
		ms="00";
		s="00";
		m="00";
		text = "00:00:00";
	}

	/**
	 * Checks if the Game's time is a new Game record.
	 * @param time - the Game's time..
	 * @return - True/False.
	 */
	public boolean isRecord(String time){
		if (Integer.parseInt(time.substring(0,2)) > Integer.parseInt(m)){
			return true;
		}
		if (Integer.parseInt(time.substring(0,2)) == Integer.parseInt(m)){
			if (Integer.parseInt(time.substring(3,5)) > Integer.parseInt(s)){
				return true;
			}
		}
		if (Integer.parseInt(time.substring(0,2)) == Integer.parseInt(m)){
			if (Integer.parseInt(time.substring(3,5)) == Integer.parseInt(s)){
				if (Integer.parseInt(time.substring(6,8)) > Integer.parseInt(ms)){
					return true;
				}
			}
		}
		return false;
	}





}
