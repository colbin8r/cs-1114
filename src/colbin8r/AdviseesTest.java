package colbin8r;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import student.TestCase;

public class AdviseesTest extends Advisees {
	
	private Advisees advisees;
	private TestCase testCase;
	private Scanner inStream;
	private StringBuilder inText;

	@Before
	public void setUp() throws Exception {
		this.advisees = new Advisees();
		this.testCase = new TestCase();
		this.inStream = this.testCase.in();
		this.inText = new StringBuilder();
		
		this.inText.append("// ID    	Name		Address			St	Zip  	Majr	Minr	Rk	QCA   	AltQCA	Crd	Hrs\n");
		this.inText.append("135792468	Wayne,John Duke	101 Hollywood Way	CA	40815	CS	MATH	10	3.2667	4.0000	49	15\n");
		this.inText.append("436587901	Mix, Tom	828 Dallas Ave	TX	52848	CS	MATH	20	3.1245	3.0000	54.0000	30.00\n");
		this.inText.append("147036925	Brennan, Walter	939 Brownsville Ave	TX	71392	CS	MATH	30	3.2831	2.5200	54	30");

		this.testCase.setIn(this.inText.toString());
	}

	@After
	public void tearDown() throws Exception {
		this.advisees = null;
	}
	
	@Test
	public void testHasStudent() throws Exception {
		Student student = StudentTest.studentFixture();
		
		this.advisees.students.add(student);
		
		assertNotEquals(this.advisees.students.indexOf(student), -1);
	}
	
	@Test
	public void testAddAndRemoveStudent() throws Exception {
		Student student = StudentTest.studentFixture();
		
		this.advisees.addStudent(student);
		
		// student added?
		assertTrue(this.advisees.hasStudent(student));
		
		this.advisees.removeStudent(student);
		
		// student removed?
		assertFalse(this.advisees.hasStudent(student));
	}
	
	@Test
	public void testInputStudents() throws Exception {
		this.advisees.inputStudents(this.inStream);
		
		// 135792468	Wayne,John Duke	101 Hollywood Way	CA	40815	CS	MATH	10	3.2667	4.0000	49	15
		Student student = new Student();
		student = this.advisees.find("135792468");
		
		assertNotNull("Student not found", student);	
		assertEquals(student.name(), "Wayne,John Duke");
		assertEquals(student.major(), "CS");		
	}
	
	@Test
	public void testGet() throws Exception {
		// test empty list of students
		assertNull(this.advisees.get(0));
		
		// test correct student is returned
		Student student = StudentTest.studentFixture();
		
		this.advisees.addStudent(student);
		
		assertEquals(this.advisees.get(0), student);
		
		// test bad boundaries
		assertNull(this.advisees.get(-1));
		assertNull(this.advisees.get(2));
	}

}
