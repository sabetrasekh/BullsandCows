package bullscows;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Input the length of the secret code:");
            int length = Integer.parseInt(scanner.nextLine());

            if (length > 36 || length < 1) {
                throw new IllegalArgumentException("Error: the length of the secret code should be between 1 and 36.");
            }

            System.out.println("Input the number of possible symbols in the code:");
            int symbols = Integer.parseInt(scanner.nextLine());

            if (symbols < length || symbols > 36) {
                throw new IllegalArgumentException("Error: the number of possible symbols should be between the length of the code and 36.");
            }

            String secretCode = generateSecretCode(length, symbols);
            System.out.println("The secret is prepared: " + printSecretCode(secretCode) + " (" + getSecretCodeSymbols(symbols) + ").");
            System.out.println("Okay, let's start a game!");
            System.out.println(secretCode);

            int turn = 1;
            while (true) {
                System.out.println("Turn " + turn + ":");
                String guess = scanner.nextLine();
                if (guess.equals(secretCode)) {
                    System.out.println("Grade: " + gradeGuess(secretCode, guess));
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                } else {
                    System.out.println("Grade: " + gradeGuess(secretCode, guess));
                    turn++;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid integer input.");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public static String generateSecretCode(int length, int symbols) {
        StringBuilder secretCode = new StringBuilder();
        Random random = new Random();

        // Characters for the secret code
        StringBuilder characters = new StringBuilder("0123456789abcdefghijklmnopqrstuvwxyz");

            for (int i = 0; i < length; i++) {
                int index = random.nextInt(symbols);
                secretCode.append(characters.charAt(index));
                characters.deleteCharAt(index);
                symbols--;
        }

        return secretCode.toString();
    }

    public static String printSecretCode(String secretCode) {
        return "*".repeat(secretCode.length());
    }

    public static String getSecretCodeSymbols(int symbols) {
        String characters = "0123456789abcdefghijklmnopqrstuvwxyz";
        return characters.substring(0, symbols);
    }

    public static String gradeGuess(String secretCode, String guess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            char secretChar = secretCode.charAt(i);
            char guessChar = guess.charAt(i);
            if (secretChar == guessChar) {
                bulls++;
            } else if (secretCode.contains(String.valueOf(guessChar))) {
                cows++;
            }
        }

        return bulls + " bull" + (bulls != 1 ? "s" : "") + " and " + cows + " cow" + (cows != 1 ? "s" : "");
    }
}




