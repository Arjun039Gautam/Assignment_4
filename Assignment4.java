package Assignment_4;
import java.util.*;
public class Assignment4 {

    public static boolean isPalindrome(String str, int start, int end) {
        if (start >= end) {
            return true;
        }

        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }

        return isPalindrome(str, start + 1, end - 1);
    }

    public static int binaryToDecimal(int number, int power) {
        if (number == 0) {
            return 0;
        }
        int lastDigit = number % 10;
        int decimal = lastDigit * (int) Math.pow(2, power);
        return decimal + binaryToDecimal(number / 10, power + 1);
    }

    public static void main(String[] args) {
        Constants constant = new Constants();
        Scanner input = new Scanner(System.in);
        boolean isQuit = true;
        while(isQuit){
            System.out.println(constant.OPERATION_TYPE);
            System.out.print(constant.ENTER_OPERATION);
            String operation = input.nextLine();
            switch (operation) {
                case "5":
                    String binaryString;
                    while (true) {
                        System.out.print("Enter a binary number: ");
                        binaryString = input.nextLine();
                        if (binaryString.matches("[01]+")) {
                            int binaryNumber = Integer.valueOf(binaryString);
                            System.out.println(constant.OUTPUT + binaryToDecimal(binaryNumber, 0));
                            break;  
                        } else {
                            System.out.println("Invalid input. Please enter a binary number (only 0s and 1s).");
                        } 
                    }
                    break;
                case "0" :
                    isQuit = false;
                    break;
                default:
                    System.out.println(constant.INVALID);
            }
        }
        input.close();
    }
}
