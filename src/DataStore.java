
import java.io.*;

/**
 * Uses java.io.Serializable to read and write objects to a file.
 */
public class DataStore {
	String dataFilePath;

	public DataStore(String dataFile) {
		this.dataFilePath = dataFile;
	}

	/**
	 * A private method for Serializing objects to the data file.
	 * 
	 * @param data - an object with the Serializable tag
	 * @return boolean - true if serializations was successfull, false if not.
	 */
	private boolean writeToFile(Serializable data) {
		try {
			FileOutputStream dataFile = new FileOutputStream(this.dataFilePath);
			ObjectOutputStream out = new ObjectOutputStream(dataFile);
			out.writeObject(data);
			out.close();
			dataFile.close();
			return true;
		} catch (IOException e) {
			System.out.println("DataStore.writeToDataFile() error: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Reads the object stored in dataFilePath and returns it. NOTE: This might
	 * error out with class not found stuff.
	 * 
	 * @return Object - returns whatever object is stored in the dataFilePath file.
	 * 
	 */
	private Object readFromFile() {
		try {
			FileInputStream dataFile = new FileInputStream(this.dataFilePath);
			ObjectInputStream in = new ObjectInputStream(dataFile);
			Object data = in.readObject();
			in.close();
			return data;

		} catch (IOException e) {
			System.out.println("DataStore.readFromFile() error: " + e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("DataStore.readFromFile() error: " + e.getMessage());
			return null;
		}
	}

	public boolean create(Serializable data) {
		return writeToFile(data);
	}

	/** */
	public Object read() {
		return readFromFile();
	}

}
