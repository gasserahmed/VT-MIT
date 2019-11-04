package person;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.chrono.IsoChronology;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class PersonTest
{

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Person fred;
    private Person jane;

    @Before
    public void setUp() throws Exception
    {
        System.setOut(new PrintStream(outContent));
        fred = new Person(
            "Fred",
            IsoChronology.INSTANCE.date(1980, 6, 20),
            Person.Sex.MALE,
            "fred@example.com");
        jane = new Person(
            "Jane",
            IsoChronology.INSTANCE.date(1990, 7, 15),
            Person.Sex.FEMALE,
            "jane@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContructorNameIsNull() 
    {
        fred = new Person(
                null,
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "fred@example.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructorBirthdayIsNull() 
    {
        fred = new Person(
                "Fred",
                null,
                Person.Sex.MALE,
                "fred@example.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructorGenderIsNull() 
    {
        fred = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                null,
                "fred@example.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructorEmailIsNull() 
    {
        fred = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContructorNameIsEmpty() 
    {
        fred = new Person(
                "",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "fred@example.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructorBirthdayIsEarly() 
    {
        fred = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1799, 12, 31),
                Person.Sex.MALE,
                "fred@example.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructorBirthdayIsLate() 
    {
        fred = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(2500, 1, 1),
                Person.Sex.MALE,
                "fred@example.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructorEmailIsEmpty() 
    {
        fred = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructorEmailNoAt() 
    {
        fred = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "fredexample.com");
    }
    
    @Test
    public void testGetName() {
        assertEquals("Fred", fred.getName());
        assertEquals("Jane", jane.getName());
    }

    @Test
    public void testGetBirthday() {
        assertEquals(IsoChronology.INSTANCE.date(1980, 6, 20), fred.getBirthday());
        assertEquals(IsoChronology.INSTANCE.date(1990, 7, 15), jane.getBirthday());
    }

    @Test
    public void testGetGender() {
        assertEquals(Person.Sex.MALE, fred.getGender());
        assertEquals(Person.Sex.FEMALE, jane.getGender());
    }

    @Test
    public void testGetEmail() {
        assertEquals("fred@example.com", fred.getEmailAddress());
        assertEquals("jane@example.com", jane.getEmailAddress());
    }

    @Test
    public void testGetAge() {
        assertTrue(fred.getAge() >= 35);
        assertTrue(jane.getAge() >= 25);
    }
    
    @Test
    public void testToString() 
    {
        assertEquals("(Fred, 1980-06-20, MALE, fred@example.com)", fred.toString());
        assertEquals("(Jane, 1990-07-15, FEMALE, jane@example.com)", jane.toString());
    }
    
    @Test
    public void testHashCode() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "fred@example.com");
        assertEquals(fred.hashCode(), fred2.hashCode());
        assertNotEquals(fred.hashCode(), jane.hashCode());
    }    
    
    @Test
    public void testEqualsFredFred2() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "fred@example.com");
        assertEquals(fred, fred2);
    }
    
    @Test
    public void testEqualsFredFred() 
    {
        assertEquals(fred, fred);
    }
    
    @Test
    public void testEqualsFredNull() 
    {
        assertNotEquals(fred, null);
    }
    
    @Test
    public void testEqualsFredHello() 
    {
        assertNotEquals(fred, "Hello");
    }
    
    @Test
    public void testEqualsFredJane() 
    {
        assertNotEquals(fred, jane);
    }
    
    @Test
    public void testEqualsBirthdayNotEqual() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1990, 7, 15),
                Person.Sex.FEMALE,
                "jane@example.com");
        assertNotEquals(fred, fred2);
    }
    
    @Test
    public void testEqualsGenderNotEqual() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.FEMALE,
                "jane@example.com");
        assertNotEquals(fred, fred2);
    }
    
    @Test
    public void testEqualsEmailNotEqual() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "jane@example.com");
        assertNotEquals(fred, fred2);
    }
    
    @Test
    public void testCompareToFredFred2() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "fred@example.com");
        assertTrue(fred.compareTo(fred2) == 0);
    }
    
    @Test
    public void testCompareToFredJane() 
    {
        assertTrue(fred.compareTo(jane) < 0);
    }
    
    @Test
    public void testCompareToBirthdayNotEqual() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1970, 7, 15),
                Person.Sex.FEMALE,
                "jane@example.com");
        assertTrue(fred.compareTo(fred2) > 0);
    }
    
    @Test
    public void testCompareToGenderNotEqual() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.FEMALE,
                "jane@example.com");
        assertTrue(fred.compareTo(fred2) < 0);
    }
    
    @Test
    public void testCompareToEmailNotEqual() 
    {
        Person fred2 = new Person(
                "Fred",
                IsoChronology.INSTANCE.date(1980, 6, 20),
                Person.Sex.MALE,
                "jane@example.com");
        assertTrue(fred.compareTo(fred2) < 0);
    }
    
    @Test
    public void testEnumSex() 
    {
        assertEquals(Person.Sex.MALE, Person.Sex.valueOf("MALE"));
        assertEquals(2, Person.Sex.values().length);
    }

}
