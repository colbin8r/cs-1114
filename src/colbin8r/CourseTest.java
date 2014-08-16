package colbin8r;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {
	
	// new course created each test
	private Course course;
	
	// holds a sample course that can be used each test
	private Course fixture;
	
	// fixtures
	public static final String fixtureDataIn = "135792468	9345	CS	1044	FS	1999	3.00	A	Intro Prog in C";
	public static Course courseFixture() {
		return new Course(fixtureDataIn);
	}

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
		
		assertEquals(this.course.getIdNumber(), this.fixture.getIdNumber());
		assertEquals(this.course.getIndex(), this.fixture.getIndex());
		assertEquals(this.course.getDepartment(), this.fixture.getDepartment());
		assertEquals(this.course.getCourseNum(), this.fixture.getCourseNum());
		assertEquals(this.course.getTerm(), this.fixture.getTerm());
		assertEquals(this.course.getYear(), this.fixture.getYear());
		assertEquals(this.course.getCreditHours(), this.fixture.getCreditHours(), .0001f);
		assertEquals(this.course.getGrade(), this.fixture.getGrade());
	}

}
