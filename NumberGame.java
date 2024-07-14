import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int roundsWon = 0;
        boolean playAgain = true;
        
        while (playAgain) {
            int randomNumber = random.nextInt(10) + 1;
            int attempts = 0;
            int maxAttempts = 5;
            boolean guessedCorrectly = false;

            System.out.println("\n\t\t!! Welcome to guess the number game !!");
            System.out.println("\n\tA random number between 1 and 10 has been generated.");
            System.out.println("\t\nYou have " + maxAttempts + " attempts to guess it correctly.");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("\nEnter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("\n   Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    roundsWon++;
                } else if (userGuess < randomNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You've run out of attempts! The correct number was " + randomNumber);
            }

            System.out.println("\t\tRounds won: " + roundsWon);
            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\n    Thanks for playing! You won total rounds : " + roundsWon +"\n");
        scanner.close();
    }
}