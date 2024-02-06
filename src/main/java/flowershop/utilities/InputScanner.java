package flowershop.utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputScanner {

    static Scanner scan = new Scanner(System.in);

    public static String askString(String message) {
        System.out.println(message);
        String text = scan.nextLine();
        return text;
    }

    public static int askInt(String message) {
        System.out.println(message);
        int num = scan.nextInt();
        scan.nextLine(); //limpieza de buffer
        return num;
    }

    public static byte askByte(String message) {
        System.out.println(message);
        byte bytes = 0;
        try {
            bytes = scan.nextByte();
        } catch (InputMismatchException e) {
            System.out.println("Wrong type inserted");
        } finally {
            scan.nextLine(); //limpieza de buffer
        }
        return bytes;
    }

    public static double askDouble(String message) {
        System.out.println(message);
        double num = 0;
        try {
            num = scan.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Wrong type inserted");
        }
        finally {
            scan.nextLine();
        }
        return num;
    }
}