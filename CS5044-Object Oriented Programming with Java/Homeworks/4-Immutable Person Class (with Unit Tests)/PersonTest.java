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
    public void testEnumSex() {
        assertEquals(Person.Sex.MALE, Person.Sex.valueOf("MALE"));
        assertEquals(2, Person.Sex.values().length);
    }

}
