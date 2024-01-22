package flowershop.utilities;

import java.util.Scanner;

public class InputScanner {

    static Scanner scan = new Scanner(System.in);

    public static String askString(String message) {
        System.out.println(message);
        String text = scan.nextLine();
        scan.nextLine(); //limpieza de buffer
        return text;
    }

    public static int askInt(String message) {
        System.out.println(message);
        return scan.nextInt();
    }

    public static byte askByte(String message) {
        System.out.println(message);
        return scan.nextByte();
    }

}


