package christmas.domain;

public class Validator {

    public static boolean validateDate(String input) {
        try {
            int num = Integer.parseInt(input);
            return (1 <= num && num <= 31);
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
