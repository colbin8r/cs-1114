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
	private float gpa;
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
    
    public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = Math.abs(zip);
	}
	
	public void setZip(String zip) {
		this.setZip(Integer.parseInt(zip));
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = Math.abs(rank);
	}
	
	public void setRank(String rank) {
		this.setRank(Integer.parseInt(rank));
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public void setGpa(String gpa) {
		this.setGpa(Float.parseFloat(gpa));
	}

	public float getAltGPA() {
		return altGPA;
	}

	public void setAltGPA(float altGPA) {
		this.altGPA = altGPA;
	}
	
	public void setAltGPA(String altGPA) {
		this.setAltGPA(Float.parseFloat(altGPA));
	}

	public float getQualCred() {
		return qualCred;
	}

	public void setQualCred(float qualCred) {
		this.qualCred = qualCred;
	}
	
	public void setQualCred(String qualCred) {
		this.setQualCred(Float.parseFloat(qualCred));
	}

	public float getHrsAtt() {
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
			if (c.getIdNumber().equals(course.getIdNumber())) {
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
	
//	public boolean outputStuData(PrintWriter outStream) {
//		StringBuilder string = new StringBuilder();
//		
//		string.append(this.getIdNumber() + "\t");
//		string.append(this.getName() + "\t");
//		string.append(this.getAddress() + "\t");
//		string.append(this.getState() + "\t");
//		string.append(this.getZip() + "\t");
//		string.append(this.getMajor() + "\t");
//		string.append(this.getMinor() + "\t");
//		string.append(this.getRank() + "\t");
//		string.append(this.getGpa() + "\t");
//		string.append(this.getQualCred() + "\t");
//		string.append(this.getHrsAtt() + "\t");
//
//		outStream.print(string);
//		return false;
//	}

}
