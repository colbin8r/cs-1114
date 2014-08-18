package colbin8r;

import java.util.ArrayList;
import java.util.Scanner;

public class Advisees {
	
	private static final String HEADER_DELIMITER = "//";
	protected ArrayList<Student> students = new ArrayList<Student>();

	public Advisees() {
		// TODO Auto-generated constructor stub
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
	
	public Student find(String idNumber) throws Exception {
		for (Student student : this.students) {
			if (student.idNumber().equals(idNumber)) {
				return student;
			}
		}
		return null;
	}
	
	public Student get(int index) {
		if (this.students.isEmpty()) {
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

}
