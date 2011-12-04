import java.io.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Logger {

	private String filename;
	private String log;

	// COMMANDS:
	// Move, Rotate, Scale, Resize, Zoom
	private final String create = "cre", move = "mov", rotate = "rot",
			scale = "scl", resize = "rsz", zoom = "zom";

	// SHAPES (Solids):
	// Prisms: Rectangular, Triangular, & Hexagonal
	// Pyramids: Square & Rectangular
	// Cylinder
	// Sphere
	// Cone - We don't support this YET...
	private final String rectangle = "rec", triangle = "tri", hexagon = "hex",
			pyramid = "pyr", cylinder = "cyl", sphere = "sph";

	public Logger() {
		init();
	}

	public void init() {
		setFilename(getDateAndTime());
		log = "";

	}

	public void readFile() {
		try {
			File file = new File(getFilename());
			Scanner scanner = new Scanner(file);
			String log = "";
			while (scanner.hasNextLine()) {
				log += scanner.nextLine() + "\n";
			}
			scanner.close();
			this.log = getLog() + log;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void writeOut(String filename, String log) {
		try {
			FileWriter outFile = new FileWriter(filename);
			PrintWriter out = new PrintWriter(outFile);

			// Also could be written as follows on one line
			// Printwriter out = new PrintWriter(new FileWriter(args[0]));

			// Write text to file
			out.print(log);
			out.close();
			this.log = "";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDateAndTime() {
		String output = "";
		DecimalFormat fmt = new DecimalFormat("00");
		Calendar now = Calendar.getInstance();
		int yyyy = now.get(Calendar.YEAR);
		int mm = now.get(Calendar.MONTH) + 1;
		int dd = now.get(Calendar.DAY_OF_MONTH);
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		output = String.format("%s_%s_%s_%s_%s_%s", yyyy, fmt.format(mm),
				fmt.format(dd), fmt.format(h), fmt.format(m), fmt.format(s));
		return output;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename + ".log";
	}

	public void add(String action) {
		log += (action + "\n");
	}

	public String getLog() {
		/*
		 * String returnedLine = "yo"; try { BufferedReader input = new
		 * BufferedReader(new FileReader(getFilename())); try { String line =
		 * null; while (( line = input.readLine()) != null){ returnedLine =
		 * line; //logText.insert(line + "\n", 0); } } finally{ input.close(); }
		 * } catch (IOException ex) { ex.printStackTrace(); }
		 */
		return log;
	}

	/**
	 * This is what will happen in our program. We will have to create a new
	 * logger and then add to the logger and when ready write out to the log
	 * file. If needed you can write to a log file.
	 */
	public static void main(String[] args) {
		// Create a logger
		Logger l = new Logger();
		l.add("cre;tri");
		l.add("mov;tri;3;6;2.2;");
		l.add("cre;hex;");
		l.add("rot;hex;x;3;");
		l.writeOut(l.getFilename(), l.getLog()); // Write-out
		l.readFile(); // Read File
		l.add("zom;200;");
		// Write over the file
		l.writeOut(l.getFilename(), l.getLog());
	}
}
