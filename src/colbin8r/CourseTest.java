package colbin8r;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTest extends Course {
	
	// new course created each test
	private Course course;
	
	// holds a sample course that can be used each test
	private Course fixture;
	
	// fixtures
	public static final String fixtureDataIn = "135792468	9345	CS	1044	FS	1999	3.00	A	Intro Prog in C";
	
	public static Course courseFixture() {
		return new Course(fixtureDataIn);
	}
	
	public static Course courseFixtureRandom() {
		// random four digit token
		int token = (int) Math.round(Math.random() * 1000);
		return CourseTest.courseFixtureRandom(token);
	}
	
	public static Course courseFixtureRandom(int token) {
		Course course = new Course();
		course.setIdNumber(String.valueOf(token));
		course.setIndex(token);
		course.setDepartment("CS");
		course.setCourseNum(token);
		course.setTerm("FS");
		course.setYear(2000);
		course.setCreditHours(3);
		course.setGrade("C");
		
		return course;
		// change the fields of the new course as needed
	}

	private static final float tolerance = .0001f;

	@Before
	public void setUp() throws Exception {
		this.course = new Course();
		this.fixture = CourseTest.courseFixture();
	}

	@After
	public void tearDown() throws Exception {
		this.course = null;
		this.fixture = null;
	}
	
	@Test
	public void testInputCourseData() {
		boolean result = this.course.inputCourseData(CourseTest.fixtureDataIn);
		
		// success?
		assertTrue(result);
		
		assertEquals(this.course.idNumber(), this.fixture.idNumber());
		assertEquals(this.course.index(), this.fixture.index());
		assertEquals(this.course.department(), this.fixture.department());
		assertEquals(this.course.courseNum(), this.fixture.courseNum());
		assertEquals(this.course.term(), this.fixture.term());
		assertEquals(this.course.year(), this.fixture.year());
		assertEquals(this.course.creditHours(), this.fixture.creditHours(), tolerance);
		assertEquals(this.course.grade(), this.fixture.grade());
	}
	
	@Test
	public void testMapGradeToQualityPoints() {
		// check to see if "B+" is mapped to 3.3
		assertEquals(Course.mapGradeToQualityPoints("B+"), 3.3f, tolerance);
	}
	
	@Test
	public void testGetQualityCredits() {
		// a 3 credit course with a B+ grade is 9.9 quality credits
		float qc = 9.9f;
		
		this.course.setCreditHours(3.0f);
		this.course.setGrade("B+");
		
		assertEquals(this.course.getQualityCredits(), qc, tolerance);
	}

}
