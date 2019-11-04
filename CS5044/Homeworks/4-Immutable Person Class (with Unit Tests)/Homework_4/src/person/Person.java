package person;

/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
 
import java.time.chrono.IsoChronology;
import java.time.LocalDate;

/**
 * 
 * This class has private instances, helpers, and public methods for Person class
 *
 * @author gasser18
 * @version Nov 3, 2019
 *
 */
final public class Person implements Comparable<Person> {

    /**
     * 
     * This enum describes different types of gender a person can have i.e. male or female
     *
     * @author gasser18
     * @version Nov 3, 2019
     *
     */
    public enum Sex {
        /**
         * Gender is Male
         */
        MALE, 
        /**
         * Gender is Female
         */
        FEMALE
    }
  
    private final String name; 
    private final LocalDate birthday;
    private final Sex gender;
    private final String emailAddress;
    
    /**
     * Create a new Person object.
     *
     * @param personName person's name
     * @param personBirthday person's birthday
     * @param personGender person's gender
     * @param personEmailAddress person's email
     */
    public Person(String personName, LocalDate personBirthday, Sex personGender,
            String personEmailAddress)
    {
        if (anyAreNull(personName, personBirthday, personGender, personEmailAddress)) {
            throw new IllegalArgumentException();
        }        
        if (anyAreInvalid(personName, personBirthday, personEmailAddress)) {
            throw new IllegalArgumentException();
        }
        
        this.name = personName;
        this.birthday = personBirthday;
        this.gender = personGender;
        this.emailAddress = personEmailAddress;
    }
   
    /**
     * Check if any of the Person's fields is invalid
     * 
     * @param personName person's name
     * @param personBirthday person's birthday
     * @param personEmailAddress person's email
     * @return true if any is invalid, otherwise, false
     */
    private boolean anyAreInvalid(String personName, LocalDate personBirthday,
            String personEmailAddress)
    {
        return  personName.equals("") || 
                personBirthday.compareTo(IsoChronology.INSTANCE.date(1800, 1, 1)) < 0 ||
                personBirthday.compareTo(IsoChronology.INSTANCE.dateNow()) > 0 ||
                personEmailAddress.equals("") ||
                !personEmailAddress.contains("@");
    }

    /**
     * Check if any of the Person's fields is null
     * 
     * @param personName person's name
     * @param personBirthday person's birthday
     * @param personGender person's gender
     * @param personEmailAddress person's email
     * @return true if any is null, otherwise, false
     */
    private boolean anyAreNull(String personName, LocalDate personBirthday, Sex personGender,
            String personEmailAddress)
    {
        return personName == null || 
               personBirthday == null ||
               personGender == null ||
               personEmailAddress == null;
    }

    /**
     * Get person's name.
     *
     * @return person's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get person's birthday.
     *
     * @return person's birthday
     */
    public LocalDate getBirthday()
    {
        return birthday;
    }

    /**
     * Get person's gender.
     *
     * @return person's gender
     */
    public Sex getGender()
    {
        return gender;
    }

    /**
     * Get person's email.
     *
     * @return person's email
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }
    
    /**
     * Get person's age.
     *
     * @return person's age
     */
    public int getAge() {
        return birthday
            .until(IsoChronology.INSTANCE.dateNow())
            .getYears();
    }
    
    @Override
    public String toString()
    {
        return "(" + name + ", " + birthday + ", " + gender + ", " + emailAddress + ")";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + birthday.hashCode();
        result = prime * result + emailAddress.hashCode();
        result = prime * result + gender.hashCode();
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person that = (Person) obj;
        if (!this.name.equals(that.name)) {
            return false;
        }
        if (!this.birthday.equals(that.birthday)) {
            return false;
        }
        if (this.gender != that.gender) {
            return false;
        }
        
        return this.emailAddress.equals(that.emailAddress);
    }
    
    @Override
    public int compareTo(Person that)
    {
        if (!this.name.equals(that.name)) {
            return this.name.compareTo(that.name);
        }
        if (!this.birthday.equals(that.birthday)) {
            return this.birthday.compareTo(that.birthday);
        }
        if (this.gender != that.gender) {
            return this.gender.compareTo(that.gender);
        }
        if (!this.emailAddress.equals(that.emailAddress)) {
            return this.emailAddress.compareTo(that.emailAddress);
        }        
        return 0;
    }
}
