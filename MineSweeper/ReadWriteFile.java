package MineSweeper;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
/**
 * A Class that handles input and output files.
 * @author Victor Banshats
 * @author Gavriel Giladov.
 *
 */
public class ReadWriteFile {


	/**
	 * This function reads the content of the file named fileName and concatenate it to one string which will be returned to the user
	 * @param fileName - name of file we wish to read
	 * @return String - file content concatenated to one string
	 */
	public static String readFromFile(String fileName){
		String tContent = "";

		//Must wrap working with files with try/catch
		try{
			//Creating a file object
			File tFile = new File(fileName);
			//Initiate input stream
			FileInputStream fstream = new FileInputStream(tFile);

			//Creating a buffered reader.
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null){
				tContent = tContent + strLine + "\n";//concatenating the line to content string
			}
			//Close the input stream
			fstream.close();
		}
		catch(Exception e)//Catch exception if any
		{
			System.err.println("Error: " + e.getMessage());
		}
		return tContent;
	}
	/**
	 * This function writes the content of the string data to a file called fileName
	 * @param filename - of output file
	 * @param data - data to write
	 */
	public static void writeToFile(String fileName , String data)
	{
		//Must wrap working with files with try/catch
		try {
			//Creating a file object
			File tFile = new File(fileName);
			//if file doesn't exists, then create it
			if (!tFile.exists()) {
				tFile.createNewFile();
			}
			//Initialize fileWriter


			FileWriter fw = new FileWriter(tFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			//Writing the data to the file
			bw.write(data);
			//Close the output stream
			bw.close();

		} catch (IOException e) {//Catch exception if any

			System.err.println("Error: " + e.getMessage());
		}
		return;
	}
}
