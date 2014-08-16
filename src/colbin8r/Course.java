package colbin8r;

import java.util.HashMap;

public class Course {
	
	private String idNumber;
	private int index;
	private String department;
	private int courseNum;
	private String term;
	private int year;
	private float creditHours;
	private String grade;
	
	private static final HashMap<String, Float> qualityPointsMap;
    static
    {
    	qualityPointsMap = new HashMap<String, Float>();
    	qualityPointsMap.put("A", 4.0f);
    	qualityPointsMap.put("A-", 3.7f);
    	qualityPointsMap.put("B+", 3.3f);
    	qualityPointsMap.put("B", 3.0f);
    	qualityPointsMap.put("B-", 2.7f);
    	qualityPointsMap.put("C+", 2.3f);
    	qualityPointsMap.put("C", 2.0f);
    	qualityPointsMap.put("C-", 1.7f);
    	qualityPointsMap.put("D+", 1.3f);
    	qualityPointsMap.put("D", 1.0f);
    	qualityPointsMap.put("D-", 0.7f);
    	qualityPointsMap.put("F", 0.0f);
    }
	
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
	
	public String getIdentifier() {
		return this.getIdNumber() + this.getDepartment() + this.getCourseNum();
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
	
	protected static float mapGradeToQualityPoints(String grade) {
		return Course.qualityPointsMap.get(grade);
	}
	
	public float getQualityCredits() {
		// quality credits times quality points (according to grade)
		return this.getCreditHours() * Course.mapGradeToQualityPoints(this.getGrade());
	}

}
