import java.util.Random;
import java.util.Scanner;


// Developer :- Vaishnavi kasoudhan
public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Enter your name : ");
        String name =  scanner.nextLine();
        System.out.println("Thanks for visiting our game Mr./Miss."+ name +" enjoy the Game and do fun.");
        
        int numberOfRounds = 1;
        int totalAttempts = 0;


        while (true) {
            int numTG = random.nextInt(1, 101);
            int attempts = 0;
            int maxAttempts = 10;

            System.out.println("Round " + numberOfRounds + ":");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numTG) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    break;
                } else if (userGuess < numTG) {
                    System.out.println("Too low! Try another in upward.");
                } else {
                    System.out.println("Too high! Try another in downward.");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you didn't guess the number. It was " + numTG + ".");
            }

            totalAttempts += attempts;
            numberOfRounds++;

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Your final score is " + totalAttempts + " attempts over " + (numberOfRounds - 1) + " rounds.");
    }
}
