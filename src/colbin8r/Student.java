package colbin8r;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 *  Models a student and their associated grade information.
 * 
 *  @author  colbin8r
 *  @version 2014.08.15
 */
public class Student
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

	public float altGPA() {
		return altGPA;
	}

	public void setAltGPA(float altGPA) {
		this.altGPA = altGPA;
	}
	
	public void setAltGPA(String altGPA) {
		this.setAltGPA(Float.parseFloat(altGPA));
	}

	public float qualCred() {
		return qualCred;
	}

	public void setQualCred(float qualCred) {
		this.qualCred = qualCred;
	}
	
	public void setQualCred(String qualCred) {
		this.setQualCred(Float.parseFloat(qualCred));
	}

	public float hrsAtt() {
		return hrsAtt;
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
		StringBuilder string = new StringBuilder();
		
		string.append(this.idNumber() + "\t");
		string.append(this.name() + "\t");
		string.append(this.address() + "\t");
		string.append(this.state() + "\t");
		string.append(this.zip() + "\t");
		string.append(this.major() + "\t");
		string.append(this.minor() + "\t");
		string.append(this.rank() + "\t");
		string.append(this.getGpa() + "\t");
		string.append(String.format("%.4f", this.altGPA()) + "\t");
		string.append(String.format("%.0f", this.qualCred()) + "\t");
		string.append(String.format("%.0f", this.hrsAtt()) + "\t");

		outStream.print(string);
		return false;
	}
	
	public float getGpa() {
		
		// if we don't have a gpa, calculate one
		if (Float.isNaN(this.gpa)) {
			this.calculateGpa();			
		}
		
		return this.gpa;
	}
	
	public void calculateGpa() {
		float totalQualityCredits = 0.0f;
		int totalCreditHoursAttempted = 0;
		
		for (Course course : this.courses) {
			totalQualityCredits += course.getQualityCredits();
			totalCreditHoursAttempted += course.creditHours();
		}
		
		this.gpa = totalQualityCredits / totalCreditHoursAttempted;
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

}
