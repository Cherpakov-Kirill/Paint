package nsu.graphics.firstlab;

import static java.lang.Math.*;

public class MathUtils {
    public static double cosDeg(int degrees){
        return cos(toRadians(degrees));
    }

    public static double sinDeg(int degrees){
        return sin(toRadians(degrees));
    }

    public static boolean isNumeric(String string) {
        int intValue;
        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}
