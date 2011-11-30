import java.io.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Logger {

	private String filename;
	private String log;

	/** I have to create a Generate Shape Command: gen */
   
   /**
    * Check out file://Common/extra/ReadLog.java
    * It is not a runnable java file, but it shows how to read the log file
    * We can look inot this further as a group.
   */

	// COMMANDS:
	// Move, Rotate, Scale, Resize, Zoom
	private final String mov = "mov", rot = "rot", scl = "scl", rsz = "rsz",
			zom = "zom";

	// SHAPES (Solids):
	// Prisms: Rectangular, Triangular, & Hexagonal
	// Pyramids: Square & Rectangular
	// Cylinder
	// Sphere
	// Cone - We don't support this YET...
	private final String rec = "rec", tri = "tri", hex = "hex", sqp = "sqp",
			rep = "rep", cyl = "cyl", sph = "sph";

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
			scanner.nextLine();
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
		log += action + "\n";
	}

	public String getLog() {
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
		// Move Triangular-Prism to right 3, back 5
		l.add("mov" + "tri" + "3,5,0");
		// Rotate Triangular-Prism 45 Degrees around y-axis
		l.add("rot" + "tri" + "y,45");
		// Zoom in 200%
		l.add("zom" + "200");
		// Scale Sphere to 10% of original size
		l.add("scl" + "sph" + "10");
		// Write-out
		l.writeOut(l.getFilename(), l.getLog());
		// Read File
		l.readFile();
		// Move Cylinder
		l.add("mov" + "cyl" + "0,0,6");
		// Scale Hexagonal-Prism to 50% of original size
		l.add("scl" + "hex" + "50");
		// Write over the file
		l.writeOut(l.getFilename(), l.getLog());
	}
}
