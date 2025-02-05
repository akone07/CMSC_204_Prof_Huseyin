/*
 * Class: CMSC204-31645
 * 
 * Instructor: Huseyin_Aygun 
 * 
 * Description: The PasswordCheckerUtility.java
 *
 * This class contains the main methods for validating passwords. It checks if a password is valid or weak and identifies invalid passwords from a given list.
 * It uses internal methods to check for uppercase letters, lowercase letters, digits, special characters, and repetitive sequences.
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

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordCheckerUtility {

    public static boolean isValidPassword(String password) throws Exception {
        if (password.length() < 6) throw new LengthException();
        if (!containsUpperCase(password)) throw new NoUpperAlphaException();
        if (!containsLowerCase(password)) throw new NoLowerAlphaException();
        if (!containsDigit(password)) throw new NoDigitException();
        if (!containsSpecialCharacter(password)) throw new NoSpecialCharacterException();
        if (hasInvalidSequence(password)) throw new InvalidSequenceException();
        return true;
    }

    public static boolean isWeakPassword(String password) throws WeakPasswordException {
        if (password.length() >= 6 && password.length() <= 9) {
            throw new WeakPasswordException();
        }
        return false;
    }

    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();
        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (Exception e) {
                invalidPasswords.add(password + " " + e.getMessage());
            }
        }
        return invalidPasswords;
    }

    private static boolean containsUpperCase(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }

    private static boolean containsLowerCase(String password) {
        return password.chars().anyMatch(Character::isLowerCase);
    }

    private static boolean containsDigit(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    private static boolean containsSpecialCharacter(String password) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }

    private static boolean hasInvalidSequence(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

	public static boolean comparePasswordsWithReturn(String password, String passwordA) {
		// TODO Auto-generated method stub
		return false;
	}
}
