/*	3D Geometric Object Rendering Application
    Copyright (C) 2011  Jennifer Hill, Ryan Kane, Sean Weber, Donald Shaner, Dorothy Kirlew

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseLog {
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

	public ParseLog() {

		String selected_file = "";
		File file = null;
		Scanner scan = null;

		try {
			selected_file = "2011_12_03_20_04_31.log";
			file = new File(selected_file);
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found!");
		}

		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] seg = line.split(";");

			// For all lines
			String cmd = seg[0]; // Command

			if (cmd.equals(zoom)) {
				double percent = Double.parseDouble(seg[1]); // Percentage
				System.out.printf("You zoomed in %.2f%%.\n", percent);
			} else {
				String shp = seg[1]; // Action
				if (cmd.equals(create)) {
					System.out.printf("You created a %s.\n", shp);
				} else if (cmd.equals(move)) {
					double x = Double.parseDouble(seg[2]); // X-Axis
															// Translation
					double y = Double.parseDouble(seg[3]); // Y-Axis
															// Translation
					double z = Double.parseDouble(seg[4]); // Z-Axis
															// Translation
					System.out.printf("You moved a %s (%.2f, %.2f, %.2f).\n",
							shp, x, y, z);
				} else if (cmd.equals(rotate)) {
					String axis = seg[2]; // Which axis?
					int numRot = Integer.parseInt(seg[3]);
					System.out
							.printf("You rotated a %s %d times around the %s-axis.\n",
									shp, numRot, axis);
				} else if (cmd.equals(scale)) {
					double percent = Double.parseDouble(seg[2]); // Percentage
					System.out
							.printf("You scaled a %s %.2f%%.\n", shp, percent);
				} else if (cmd.equals(resize)) {
					double height = Double.parseDouble(seg[2]); // X-Axis
																// Translation
					double width = Double.parseDouble(seg[3]); // Y-Axis
																// Translation
					double depth = Double.parseDouble(seg[4]); // Z-Axis
																// Translation
					System.out.printf(
							"You resized a %s to %.2f&x%.2f&x%.2f&.\n", shp);
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Program Opened");
		new ParseLog();
		System.out.println("Program Closed");
	}
}
