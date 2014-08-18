package colbin8r;

import java.io.*;
import java.util.*;

import student.IOHelper;

public class Application {
	
	private static PrintWriter output;
	
	private static ArrayList<String> options = new ArrayList<String>();
	private static ArrayList<String> arguments = new ArrayList<String>();

	public Application() {
		
	}

	public static void main(String[] args) {
		try {
			int index = 0;
			
			for (String arg : args) {
				// sort between options and arguments
				if (arg.startsWith("-")) {
					index = 1;
					if (arg.startsWith("--")) {
						index = 2;
					}
					Application.options.add(arg.substring(index, arg.length()));
				} else {
					Application.arguments.add(arg);
				}
			}
			
			if (Application.option("h") || Application.option("help")) {
				help();
			}
			
			if (Application.option("v") || Application.option("version")) {
				version();
			}
			
			if (Application.arguments.size() <= 1) {
				throw new Exception("Not enough arguments.");
			}
			
			String studentFileName = "";
			String courseFileName = "";
			String outputFileName = "gpa.txt";
			
			if (Application.arguments.size() >= 2) {
				studentFileName = Application.argument(0);
				courseFileName = Application.argument(1);
			}
			
			if (Application.arguments.size() >= 3) {
				outputFileName = Application.argument(2);
			} else {
				print("No output filename provided; defaulting to " + outputFileName);
			}
			
			output = IOHelper.createPrintWriter(outputFileName);

			// input student file
			Scanner studentsInput = IOHelper.createScanner(studentFileName);
			
			// input course file
			Scanner courseInput = IOHelper.createScanner(courseFileName);
			
			// feed student file into advisees
			Advisees advisees = new Advisees();
			advisees.inputStudents(studentsInput);
			
			// match each course to student
			advisees.inputCourses(courseInput);
			
			// recalculate gpas
			advisees.calculate();
			
			// output calculated student list with new GPAs
			advisees.output(output);
			
			// output bar chart
			advisees.histogram(output);
			
			// output statistics
			advisees.statistics(output);
			
		} catch (Exception e) {
			System.out.println("MAIN error: " + e.getMessage());
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}
	
	public static boolean option(String option) {
		if (Application.options.contains(option)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String argument(int index) {
		return Application.arguments.get(index);
	}
	
	private static void stop() {
		System.exit(0);
	}
	
	private static void help() {
		Application.print("Calculates GPA from a file.\n"
				+ "Usage: java colbin8r.Application STUDENTS.TXT COURSES.TXT [OUTPUT.TXT]");
		Application.stop();
	}
	
	private static void version() {
		Application.print("Version 1.0");
		Application.stop();
	}

	private static void print(String text) {
		System.out.println(text);
	}

}
