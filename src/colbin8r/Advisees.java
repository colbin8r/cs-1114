package colbin8r;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Advisees {
	
	private static final String HEADER_DELIMITER = "//";
	protected ArrayList<Student> students = new ArrayList<Student>();

	public Advisees() {
		// TODO Auto-generated constructor stub
	}
	
	public double formatDouble(double number) {
		DecimalFormat format = new DecimalFormat("#.####");      
		return Double.valueOf(format.format(number));
	}
	
	public void addStudent(Student student) {
		if (!this.hasStudent(student)) {
			this.students.add(student);			
		}
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
	
	public boolean hasStudent(Student student) {
		return this.students.indexOf(student) != -1;
//		for (Student s : this.students) {
//			if (s.equals(student)) {
//				return true;
//			}
//		}		
//		return false;
	}
	
	public boolean hasStudents() {
		return !this.students.isEmpty();
	}
	
	public Student find(String idNumber) throws Exception {
		for (Student student : this.students) {
			if (student.idNumber().equals(idNumber)) {
				return student;
			}
		}
		return null;
	}
	
	public Student get(int index) {
		if (!this.hasStudents()) {
			return null;
		}
		try {
			return this.students.get(index);
		} catch (Exception e) {
			return null;
		}
		
	}

	public boolean inputStudents(Scanner inStream) 	{
		String token = inStream.next();
		
		if (token.contains(HEADER_DELIMITER)) {
			inStream.nextLine();
		}
		
		while (inStream.hasNext()) {
			token = inStream.nextLine();
			this.addStudent(new Student(token));
		}
		return false;
	}
	
	public boolean inputCourses(Scanner inStream) 	{
		String token = inStream.nextLine();
		Course course;
		
//		if (token.contains(HEADER_DELIMITER)) {
//			token = inStream.nextLine();
//		}

		while (inStream.hasNext()) {
			token = inStream.nextLine();
			course = new Course(token);
			try {
				this.addCourse(course);
			} catch (Exception e) {
				// no corresponding student
			}
		}
		return true;
	}
	
	private void addCourse(Course course) throws Exception {
		this.find(course.idNumber()).addCourse(course);
	}

	public double mean() {
		if (!this.hasStudents()) {
			return Double.valueOf(-1.0000);
		}
		double sumGpa = 0;
		for (Student student : this.students){
			sumGpa += student.gpa();
		}
		
		return formatDouble(sumGpa/this.students.size());
	}
	
	public double stdDev() {
		if (!this.hasStudents()) {
			return Double.valueOf(-1.0000);
		}
		
		double sum = 0.0;
		double mean = this.mean();
		
		for (Student student : this.students){
			sum += Math.pow(student.gpa() - mean, 2);
		}
		
		return formatDouble(Math.sqrt(sum / this.students.size()));
	}

	public void output(PrintWriter outStream) {
		outStream.println("ID Number Name                 Major Minor Rank GPA    AltGPA QCredits Hours Att.");
		Collections.sort(this.students);
		for (Student student : this.students) {
			student.outputStuData(outStream);
		}
		outStream.println();
	}
	
	public void histogram(PrintWriter outStream) {
		BarChartGPAs charts = new BarChartGPAs();
		charts.setMaxColLength(41);
		
		outStream.println("Student GPAs:");
		outStream.print("ID Number ");
		
		StringBuilder header = new StringBuilder(charts.getMaxColLength());
		int dividers = 4;
		double dotDistance = charts.getMaxColLength() / charts.getMaxRangeValue();
		int delimiterWidth = 3;
		
		// print a dot for 0
		header.append(".");
		for (int i = 1; i <= dividers; i++) {
			// print spacing dots
			for (int j = 0; j < (dotDistance - delimiterWidth - 1); j++) {
				header.append(".");
			}
			// print the value
			header.append(Math.floor(i));
		}
		
		outStream.println(header.toString());
		
//		maxValue	maxLength
//		length = maxLength;
//		step = maxLength / maxValue
//		places = 3
//		start = step
		
//			ID Number ........1.0.......2.0.......3.0.......4.0
		
		for (Student student : this.students) {
			outStream.print(student.idNumber() + ":");
			for (int i = 0; i < charts.getBarLength(student.gpa()); i++) {
				outStream.print("*");
			}
			outStream.println();
		}
		
		outStream.println();
	}
	
	public void statistics(PrintWriter outStream) {
		outStream.println("Average GPA:        " + this.mean());
		outStream.println("Standard Deviation: " + this.stdDev());
	}

	public void calculate() {
		for (Student student : this.students) {
			student.calculateGpa();
			student.calculateAltGPA();
			student.calculateQualCred();
			student.calculateHrsAtt();
		}
	}

}
