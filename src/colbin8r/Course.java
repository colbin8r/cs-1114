package colbin8r;

public class Course {
	
	private String idNumber;
	private int index;
	private String department;
	private int courseNum;
	private String term;
	private int year;
	private float creditHours;
	private String grade;
	
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setIndex(String index) {
		this.setIndex(Integer.parseInt(index));
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	public void setCourseNum(String courseNum) {
		this.setCourseNum(Integer.parseInt(courseNum));
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public void setYear(String year) {
		this.setYear(Integer.parseInt(year));
	}

	public float getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(float creditHours) {
		this.creditHours = creditHours;
	}
	
	public void setCreditHours(String creditHours) {
		float creditHoursFloat = Float.parseFloat(creditHours);
		int creditHoursInt = (int) creditHoursFloat; 
		this.setCreditHours(creditHoursInt);
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Course() {
		// TODO Auto-generated constructor stub
	}
	
	public Course(String inCourse) {
		this.inputCourseData(inCourse);
	}
	
	public boolean inputCourseData(String inCourse) {
		String[] fields = inCourse.split("[\t]+");
		
		this.setIdNumber(fields[0]);
		this.setIndex(fields[1]);
		this.setDepartment(fields[2]);
		this.setCourseNum(fields[3]);
		this.setTerm(fields[4]);
		this.setYear(fields[5]);
		this.setCreditHours(fields[6]);
		this.setGrade(fields[7]);
		
		return true;
	}

}
