package colbin8r;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import student.testingsupport.PrintWriterWithHistory;

public class StudentTest extends Student {
	
	private Student student;
	
	// holds a sample course that can be used each test
	private Student fixture;
	public static final String fixtureDataIn = "135792468	Wayne,John Duke	101 Hollywood Way	CA	40815	CS	MATH	10	3.2667	4.0000	49	15";
	public static final String fixtureDataOut = "135792468 Wayne,John Duke      CS    MATH  10   3.2097 3.6333 99.5000  31.00";
	
	// for creating a sample course
	public static Student studentFixture() {
		// start with some basic data
		Student student = new Student(fixtureDataIn);
		
		return student;
	}
	
	private static final double tolerance = .0001f;

	@Before
	public void setUp() throws Exception {
		this.student = new Student();
		this.fixture = StudentTest.studentFixture();
	}

	@After
	public void tearDown() throws Exception {
		this.student = null;
		this.fixture = null;
	}

	@Test
	/*
	 * Correctly read all of the student data into the corresponding fields from the provided message String.
	 */
	public void testInputStuData() {
		boolean result = this.student.inputStuData(StudentTest.fixtureDataIn);
		
		// success?
		assertTrue(result);
		
		// test that all fields are equal to the expected values
		assertEquals(this.student.idNumber(), this.fixture.idNumber());
		assertEquals(this.student.name(), this.fixture.name());
		assertEquals(this.student.address(), this.fixture.address());
		assertEquals(this.student.state(), this.fixture.state());
		assertEquals(this.student.zip(), this.fixture.zip());
		assertEquals(this.student.major(), this.fixture.major());
		assertEquals(this.student.minor(), this.fixture.minor());
		assertEquals(this.student.rank(), this.fixture.rank());
//		assertEquals(this.student.getGpa(), this.fixture.getGpa(), tolerance);
		assertEquals(this.student.altGPA(), this.fixture.altGPA(), tolerance);
		assertEquals(this.student.qualCred(), this.fixture.qualCred(), tolerance);
		assertEquals(this.student.hrsAtt(), this.fixture.hrsAtt(), tolerance);
	}
	
	@Test
	/*
	 *  Use the Course class inputCourseData method to create a corresponding Course object, storing the new object in the Student Vector or ArrayList. 
	 */
	public void testInputCoursesData() {
		// get course fixture
		Course course = CourseTest.courseFixture();
		// add the course fixture data to the student
		this.student.inputCoursesData(CourseTest.fixtureDataIn);
		// see if an appropriate course object has been added to the student
		assertTrue(this.student.hasCourse(course));
	}
	
	@Test
	/*
	 * Write the student data into the outStream using the output format described. 
	 */
	public void testOutputStuData() {
		PrintWriterWithHistory out = new PrintWriterWithHistory();
		
		// run to get some results
		this.fixture.outputStuData(out);
		
		// capture the output
		String output = out.getHistory();
		
		// instantiate new student from output
		Student outputStudent = new Student(output);
		
		// we aren't checking the gpa calculation here (need to load classes to generate, anyway)
		outputStudent.setGpa("3.2667");
		
		// is the output student equal to the one we put in?
		assertEquals(outputStudent, this.fixture);
	}
	
	@Test
	/*
	 * Test if GPA is calculated correctly.
	 */
	public void testGetGpa() {
		// create a few courses
		Course course1 = CourseTest.courseFixtureRandom(1234);
		Course course2 = CourseTest.courseFixtureRandom(2345);
		Course course3 = CourseTest.courseFixtureRandom(3456);
		course1.setIdNumber(student.idNumber());
		course2.setIdNumber(student.idNumber());
		course3.setIdNumber(student.idNumber());
		
		course1.setCreditHours(3);
		course1.setGrade("A");
		
		course2.setCreditHours(3);
		course2.setGrade("C");
		
		course3.setCreditHours(1);
		course3.setGrade("B-");
		
		// load the courses into the student
		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);
		
		// what's the new gpa?
		// quality credits = 20.7
		// attempted hrs = 7
		float expectedGpa = (20.7f / 7.0f);
		assertEquals(expectedGpa, this.student.getGpa(), tolerance);
	}
	
//	public void testSetZip() {
//		// zip codes should always be positive
//		emptyStudent.setZip("-12345)");
//		assertSame(emptyStudent.getZip(), Integer.valueOf(12345));
//	}
}
