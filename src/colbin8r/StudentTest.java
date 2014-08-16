package colbin8r;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import student.testingsupport.PrintWriterWithHistory;

public class StudentTest {
	
	private Student student;
	
	// holds a sample course that can be used each test
	private Student fixture;
	public static final String fixtureDataIn = "135792468	Wayne,John Duke	101 Hollywood Way	CA	40815	CS	MATH	10	3.2667	4.0000	49	15";
	public static final String fixtureDataOut = "135792468 Wayne,John Duke      CS    MATH  10   3.2097 3.6333 99.5000  31.00";
	
	// for creating a sample course
	public static Student studentFixture() {
		return new Student(fixtureDataIn);
	}
	
	private final double gpaTolerance = .0001f;

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
		assertEquals(this.student.getIdNumber(), this.fixture.getIdNumber());
		assertEquals(this.student.getName(), this.fixture.getName());
		assertEquals(this.student.getAddress(), this.fixture.getAddress());
		assertEquals(this.student.getState(), this.fixture.getState());
		assertEquals(this.student.getZip(), this.fixture.getZip());
		assertEquals(this.student.getMajor(), this.fixture.getMajor());
		assertEquals(this.student.getMinor(), this.fixture.getMinor());
		assertEquals(this.student.getRank(), this.fixture.getRank());
		assertEquals(this.student.getGpa(), this.fixture.getGpa(), this.gpaTolerance);
		assertEquals(this.student.getAltGPA(), this.fixture.getAltGPA(), this.gpaTolerance);
		assertEquals(this.student.getQualCred(), this.fixture.getQualCred(), this.gpaTolerance);
		assertEquals(this.student.getHrsAtt(), this.fixture.getHrsAtt(), this.gpaTolerance);
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
//	public void testOutputStuData() {
//		
//		PrintWriterWithHistory out = new PrintWriterWithHistory();
//		
//		// run to get some results
//		this.fixture.outputStuData(out);
//			
//		assertEquals(StudentTest.fixtureDataOut, out.getHistory());
//		
//	}
	
//	public void testSetZip() {
//		// zip codes should always be positive
//		emptyStudent.setZip("-12345)");
//		assertSame(emptyStudent.getZip(), Integer.valueOf(12345));
//	}
}
