import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int score = 0;
            do {
                score += playGame(scanner);
                System.out.println("Your current score: " + score);
            } while (playAgain(scanner));
            System.out.println("Thanks for playing! Your final score is: " + score);
        }
    }
    private static int playGame(Scanner scanner) {
        int min = 1;
        int max = 100;
        int maxAttempts = 5;
        int score = 0;
        int randomNumber = generateRandomNumber(min, max);
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            int userGuess = getUserGuess(scanner);
            if (compareGuess(userGuess, randomNumber)) {
                System.out.println("You guessed the correct number in " + attempt + " attempts!");
                score++;
                break;
            } else {
                int remainingAttempts = maxAttempts - attempt;
                System.out.println("Attempts remaining: " + remainingAttempts);
            }
        }
        if (score == 0) {
            System.out.println("Sorry! You couldn't guess the correct number within " + maxAttempts + " attempts. The correct number was: " + randomNumber);
        }
        return score;
    }
    private static boolean playAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next().toLowerCase();
        return playAgain.equals("yes");
    }
    private static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than Min");
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    private static int getUserGuess(Scanner scanner) {
        System.out.print("Guess the number between 1 and 100: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
    private static boolean compareGuess(int userGuess, int randomNumber) {
        if (userGuess == randomNumber) {
            System.out.println("Congratulations! Your guess is correct.");
            return true;
        } else if (userGuess < randomNumber) {
            System.out.println("Too low! Try again.");
        } else {
            System.out.println("Too high! Try again.");
        }
        return false;
    }
}
