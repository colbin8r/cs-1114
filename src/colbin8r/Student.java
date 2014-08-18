package colbin8r;

import java.io.PrintWriter;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  Models a student and their associated grade information.
 * 
 *  @author  colbin8r
 *  @version 2014.08.15
 */
public class Student implements Comparable<Student>
{
    //~ Instance/static variables .............................................
	private String idNumber;
	private String name;
	private String address;
	private String state;
	private int zip;
	private String major;
	private String minor;
	private int rank;
	private float gpa = Float.NaN; // no gpa by default
	private float altGPA;
	private float qualCred;
	private float hrsAtt;
	
	private ArrayList<Course> courses = new ArrayList<Course>();
		
	//~ Constructor ...........................................................

	// ----------------------------------------------------------
    /**
     * Creates a new Student object.
     */
    public Student() {
        
    }
    
    public Student(String inStudent) {
    	this.inputStuData(inStudent);
    }
    
//    public Student(String[] fields) {
//    	StringBuilder inStudent = new StringBuilder();
//    	for (String field : fields) {
//    		inStudent.append(field + "\t");
//    	}
//    	this.inputStuData(inStudent.toString());
//    }
    
    public String idNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String address() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String state() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int zip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = Math.abs(zip);
	}
	
	public void setZip(String zip) {
		this.setZip(Integer.parseInt(zip));
	}

	public String major() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String minor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public int rank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = Math.abs(rank);
	}
	
	public void setRank(String rank) {
		this.setRank(Integer.parseInt(rank));
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public void setGpa(String gpa) {
		this.setGpa(Float.parseFloat(gpa));
	}

	public void setAltGPA(float altGPA) {
		this.altGPA = altGPA;
	}
	
	public void setAltGPA(String altGPA) {
		this.setAltGPA(Float.parseFloat(altGPA));
	}

	public void setQualCred(float qualCred) {
		this.qualCred = qualCred;
	}
	
	public void setQualCred(String qualCred) {
		this.setQualCred(Float.parseFloat(qualCred));
	}

	public void setHrsAtt(float hrsAtt) {
		this.hrsAtt = hrsAtt;
	}
	
	public void setHrsAtt(String hrsAtt) {
		this.setHrsAtt(Float.parseFloat(hrsAtt));
	}
	
	public void addCourse(Course course) {
		// no duplicate courses allowed
		if (!this.hasCourse(course)) {
			this.courses.add(course);
		}
	}
	
	public boolean hasCourse(Course course) {;
		for (Course c : this.courses) {
			if (c.getIdentifier().equals(course.getIdentifier())) {
				return true;
			}
		}
		return false;
//		return this.courses.contains(course);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}

    //~ Methods ...............................................................
	
    /*
	 * Read all of the student data into the corresponding fields from the provided message String.
	 */
	public boolean inputStuData(String inStudent) {
		String[] fields = inStudent.split("[\t]+");
		
		this.setIdNumber(fields[0]);
		this.setName(fields[1]);
		this.setAddress(fields[2]);
		this.setState(fields[3]);
		this.setZip(fields[4]);
		this.setMajor(fields[5]);
		this.setMinor(fields[6]);
		this.setRank(fields[7]);
		this.setGpa(fields[8]);
		this.setAltGPA(fields[9]);
		this.setQualCred(fields[10]);
		this.setHrsAtt(fields[11]);
		
		return true;
	}
	
	public boolean inputCoursesData(String inCourse) {
		Course course = new Course(inCourse);
		this.addCourse(course);
		return false;
	}
	
	public boolean outputStuData(PrintWriter outStream) {
		
		StringBuilder output = new StringBuilder();
		
		// "ID Number Name                 Major Minor Rank GPA    AltGPA QCredits Hours Att.";
		String idNumberHead = "ID Number ";
		String nameHead = "Name                 ";
		String majorHead = "Major ";
		String minorHead = "Minor ";
		String rankHead = "Rank ";
		String gpaHead = "GPA    ";
		String altGPAHead = "AltGPA ";
		String qualCredHead = "QCredits ";
		String hrsAttHead = "Hours Att.";
		
		output.append(formatColumn(idNumberHead, this.idNumber));
		output.append(formatColumn(nameHead, this.name()));
		output.append(formatColumn(majorHead, this.major()));
		output.append(formatColumn(minorHead, this.minor()));
		output.append(formatColumn(rankHead, String.valueOf(this.rank())));
		output.append(formatColumn(gpaHead, String.format("%.4f", this.gpa())));
		output.append(formatColumn(altGPAHead, String.format("%.4f", this.altGPA())));
		output.append(formatColumn(qualCredHead, String.format("%.4f", this.qualCred())));
		output.append(formatColumn(hrsAttHead, String.format("%.2f", this.hrsAtt())));

		outStream.println(output);
		
		return false;
	}
	
	private String formatColumn(String columnHead, String value) {
		StringBuilder output = new StringBuilder();
		
		output.append(value);
		output.append(spacer(columnHead, value));
		
		return output.toString();
	}
	
	private String spacer(String columnHead, String value) {
		StringBuilder spacer = new StringBuilder();
		
		int fieldLength = value.length();
		int fieldLengthMax = columnHead.length();
		int spaces = fieldLengthMax - fieldLength;
		
		for (int i = 0; i < spaces; i++) {
			spacer.append(" ");
		}
		return spacer.toString();
	}
	
	public float gpa() {
		
		// if we don't have a gpa, calculate one
		if (Float.isNaN(this.gpa) || this.gpa == 0.0f) {
			this.calculateGpa();			
		}
		
		return this.gpa;
	}
	
	public void calculateGpa() {
		
		if (this.courses.isEmpty()) {
			this.gpa = 0.0f;
			return;
		}
		
		float totalQualityCredits = 0.0f;
		int totalCreditHoursAttempted = 0;
		
		for (Course course : this.courses) {
			if (!course.isPassFail()) {
				totalQualityCredits += course.getQualityCredits();
				totalCreditHoursAttempted += course.creditHours();
			}
		}
		
		this.gpa = totalQualityCredits / totalCreditHoursAttempted;
	}
	
	public float altGPA() {
		
		if (Float.isNaN(this.altGPA) || this.altGPA == 0.0f) {
			this.calculateAltGPA();
		}
		
		return this.altGPA;
	}
	
	public void calculateAltGPA() {
		
		if (this.courses.isEmpty()) {
			this.altGPA = 0.0f;
			return;
		}
		
		float totalQualityCredits = 0.0f;
		int totalCreditHoursAttempted = 0;
		
		for (Course course : this.courses) {
			if (course.department().equals(this.major()) && !course.isPassFail()) {
				totalQualityCredits += course.getQualityCredits();
				totalCreditHoursAttempted += course.creditHours();
			}
		}
		
		this.altGPA = totalQualityCredits / totalCreditHoursAttempted;
	}
	
	public float qualCred() {
		if (Float.isNaN(this.qualCred) || this.qualCred == 0.0f) {
			this.calculateQualCred();
		}
		return qualCred;
	}
	
	public void calculateQualCred() {
		if (this.courses.isEmpty()) {
			this.qualCred = 0.0f;
			return;
		}
		
		float totalQualityCredits = 0.0f;
		
		for (Course course : this.courses) {
			if (!course.isPassFail()) {
				totalQualityCredits += course.getQualityCredits();
			}
		}
		
		this.qualCred = totalQualityCredits;
	}
	
	public float hrsAtt() {
		if (Float.isNaN(this.hrsAtt) || this.hrsAtt == 0.0f) {
			this.calculateHrsAtt();
		}
		return hrsAtt;
	}
	
	public void calculateHrsAtt() {
		if (this.courses.isEmpty()) {
			this.hrsAtt = 0;
			return;
		}
		
		int totalCreditHoursAttempted = 0;
		
		for (Course course : this.courses) {
			if (!course.isPassFail()) {
				totalCreditHoursAttempted += course.creditHours();
			}
		}
		
		this.hrsAtt = totalCreditHoursAttempted;
	}

	@Override
	public boolean equals(Object student) {
		if (this.toString().equals(student.toString())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Student [idNumber=" + idNumber + ", name=" + name
				+ ", address=" + address + ", state=" + state + ", zip=" + zip
				+ ", major=" + major + ", minor=" + minor + ", rank=" + rank
				+ ", gpa=" + gpa + ", altGPA=" + altGPA + ", qualCred="
				+ qualCred + ", hrsAtt=" + hrsAtt + ", courses=" + courses
				+ "]";
	}

	@Override
	public int compareTo(Student other) {
		return name().compareTo(other.name());
	}

}
