/*
 * Class: CMSC204-31645
 * 
 * Instructor: Huseyin_Aygun 
 * 
 * Description: The PasswordCheckerTest.java
 *
 * This is a JUnit test class that performs unit tests on the password validation functionality. 
 * It checks if the exceptions are correctly thrown when passwords do not follow the rules.
 *
 * Due: 02-02-2025
 * 
 * Platform/compiler: Windows 
 * 
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: ALASSANE KONE
*/






package password_Package;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class PasswordCheckerTest {
    
    private ArrayList<String> passwords;

    @Before
    public void setUp() {
        passwords = new ArrayList<>();
        passwords.add("Hello@123"); // OK
        passwords.add("AAAbb@123"); // Invalid (InvalidSequenceException)
        passwords.add("Aaabb@123"); // OK
        passwords.add("short@1");   // Weak
        passwords.add("NoSpecial1"); // Invalid (NoSpecialCharacterException)
        passwords.add("noupper@123"); // Invalid (NoUpperAlphaException)
        passwords.add("NOLOWER@123"); // Invalid (NoLowerAlphaException)
        passwords.add("NoNumber@!");  // Invalid (NoDigitException)
        passwords.add("Abc@123");    // Weak
    }

    @Test
    public void testIsValidPassword() throws Exception {
        assertTrue(PasswordCheckerUtility.isValidPassword("Hello@123"));
    }

    @Test(expected = LengthException.class)
    public void testShortPassword() throws Exception {
        PasswordCheckerUtility.isValidPassword("A@1");
    }

    @Test(expected = NoUpperAlphaException.class)
    public void testNoUpperCase() throws Exception {
        PasswordCheckerUtility.isValidPassword("lowercase@123");
    }

    @Test(expected = NoLowerAlphaException.class)
    public void testNoLowerCase() throws Exception {
        PasswordCheckerUtility.isValidPassword("UPPERCASE@123");
    }

    @Test(expected = NoDigitException.class)
    public void testNoDigit() throws Exception {
        PasswordCheckerUtility.isValidPassword("Abcdef@!");
    }

    @Test(expected = NoSpecialCharacterException.class)
    public void testNoSpecialCharacter() throws Exception {
        PasswordCheckerUtility.isValidPassword("Abcdef123");
    }

    @Test(expected = InvalidSequenceException.class)
    public void testInvalidSequence() throws Exception {
        PasswordCheckerUtility.isValidPassword("AAAbb@123");
    }

    @Test(expected = WeakPasswordException.class)
    public void testWeakPassword() throws WeakPasswordException {
        PasswordCheckerUtility.isWeakPassword("Abc@123");
    }

    @Test
    public void testGetInvalidPasswords() {
        ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwords);
        assertEquals(6, invalidPasswords.size()); // 5 invalid passwords
    }
}
