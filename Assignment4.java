/**
 * Problem : A java program that can perform CountPalindromes, NthFibonacci, SnakeToCamel, CountConsonants, BinaryToDecimal.
 * Owner name : Arjun Gautam
 * Date : 11/09/24
 */
package Assignment_4;
import java.util.*;
public class Assignment4 {
    
    //#1 Count palindrome
    public static boolean isPalindrome(String str, int start, int end) {
        if (start >= end) {
            return true;
        }

        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }

        return isPalindrome(str, start + 1, end - 1);
    }
    // #5 Binary To Decimal
    public static int binaryToDecimal(int number, int power) {
        if (number == 0) {
            return 0;
        }
        int lastDigit = number % 10;
        int decimal = lastDigit * (int) Math.pow(2, power);
        return decimal + binaryToDecimal(number / 10, power + 1);
    }

    // #4 Count Consonents
    public static void countConsonents(String orginalString, int index, int count){
        if(index == orginalString.length()){
            System.out.println(count);
        } else {
            char currentCharacter = orginalString.charAt(index);
            currentCharacter = Character.toLowerCase(currentCharacter);
            boolean output = (currentCharacter >= 'a' && currentCharacter <= 'z') && (currentCharacter != 'a' && currentCharacter != 'e' && currentCharacter != 'i' && currentCharacter != 'o' && currentCharacter != 'u');

            if(output){
                count++ ;
            }
            index++ ;
            countConsonents(orginalString, index, count);
        }
    }

    //#3 Snakecase to Camelcase
    public static void snakeToCamel(String originalString, int index, String output) {
        if (index == originalString.length()) {
            System.out.println(output);
            return;
        }
    
        char currentCharacter = originalString.charAt(index);
        
        if (currentCharacter != '_') {
            output += currentCharacter;
        } else {
            if (index + 1 < originalString.length()) {
                output += Character.toUpperCase(originalString.charAt(index + 1));
                index++;
            }
        }
        snakeToCamel(originalString, index + 1, output);
    }

    //Normal to snakecase
    public static void normalToSnake(String originalString, int index, String output, boolean capitalizeNext) {
        
        if (index == originalString.length()) {
            System.out.println(output);
            System.out.print("Output in camelcase : ");
            snakeToCamel(output, 0, "");
            return;  
        }

        String str2 = originalString.replaceAll("[^a-zA-Z0-9]+", " ").toLowerCase().trim();

        char currentCharacter = str2.charAt(index);

        if (currentCharacter == ' ') {
            if (!output.isEmpty() && output.charAt(output.length() - 1) != '_') {
                output += "_";
            }
            capitalizeNext = true;
        } else {
            if (capitalizeNext) {
                output += currentCharacter ; 
                capitalizeNext = false;  
            } else {
                output += currentCharacter;  
            }
        }
        normalToSnake(str2, index + 1, output, capitalizeNext);
    }

    // #2 NthFibonacci
    public static int nthFibonacci(int index){
        if(index == 0){
            return 0;
        }
        else if(index == 1){
            return 1;
        }
        else{
            return nthFibonacci(index - 1) + nthFibonacci(index - 2);
        }
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
                case "2" :
                    while (true) {
                        try {
                            System.out.print(constant.ENTER_POSITIVE);
                            int number = input.nextInt();
                            input.nextLine(); 
                            if (number <= 0) {
                                System.out.println(constant.MUST_POSITIVE);
                                continue; 
                            }
                            System.out.print(constant.OUTPUT);
                            System.out.println(nthFibonacci(number));
                            break; 
                        } catch (InputMismatchException e) {
                            System.out.println(constant.INVALID_INPUT_INTEGER);
                            input.next(); 
                        }
                    }
                    break;
                case "3" :
                    System.out.print(constant.ENTER_STRING);
                    String orginalString = input.nextLine();
                    System.out.print("Output in snakecase : ");
                    normalToSnake(orginalString, 0, "", false);
                    break;
                case "4" :
                    System.out.print(constant.ENTER_STRING);
                    String originalString = input.nextLine();
                    System.out.print(constant.OUTPUT);
                    countConsonents(originalString, 0, 0);
                    break;
                case "5":
                    String binaryString;
                    while (true) {
                        System.out.print(constant.BINARY);
                        binaryString = input.nextLine();
                        if (binaryString.matches("[01]+")) {
                            int binaryNumber = Integer.valueOf(binaryString);
                            System.out.println(constant.OUTPUT + binaryToDecimal(binaryNumber, 0));
                            break;  
                        } else {
                            System.out.println(constant.INVALID_BINARY);
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
