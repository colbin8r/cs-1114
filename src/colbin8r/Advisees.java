package colbin8r;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
	
	public double mean() {
		if (!this.hasStudents()) {
			return Double.valueOf(-1.0000);
		}
		double sumGpa = 0;
		for (Student student : this.students){
			sumGpa += student.getGpa();
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
			sum += Math.pow(student.getGpa() - mean, 2);
		}
		
		return formatDouble(Math.sqrt(sum / this.students.size()));
	}

}
