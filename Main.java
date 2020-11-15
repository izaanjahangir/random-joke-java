import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name? ");

        String myName = scanner.nextLine();

        System.out.println("Your name is " + myName);
        scanner.close();
    }
}