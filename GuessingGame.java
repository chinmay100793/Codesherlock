import java.io.*;
import java.util.*;

public class GuessingGame {
    static class Score implements Serializable {
        String name;
        int attempts;
        long timeTaken; // ms
        String difficulty;

        Score(String name, int attempts, long timeTaken, String difficulty) {
            this.name = name;
            this.attempts = attempts;
            this.timeTaken = timeTaken;
            this.difficulty = difficulty;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        List<Score> highScores = loadScores();

        System.out.println("\u001B[1m=== NUMBER GUESSING GAME ===\u001B[0m");
        System.out.print("Choose difficulty (easy/medium/hard): ");
        String diff = sc.nextLine().toLowerCase();
        int range = switch (diff) {
            case "easy" -> 50;
            case "hard" -> 500;
            default -> 100;
        };

        int target = rand.nextInt(range) + 1;
        int attempts = 0;
        long start = System.currentTimeMillis();

        while (true) {
            System.out.print("\u001B[33mGuess the number (1-" + range + "): \u001B[0m");
            int guess = sc.nextInt();
            attempts++;

            if (guess == target) {
                long timeTaken = System.currentTimeMillis() - start;
                System.out.println("\u001B[32mCORRECT! You won in " + attempts + " attempts and " +
                        (timeTaken / 1000) + " seconds!\u001B[0m");

                System.out.print("Enter your name for high scores: ");
                sc.nextLine(); // consume newline
                String name = sc.nextLine();

                highScores.add(new Score(name, attempts, timeTaken, diff));
                highScores.sort(Comparator.comparingInt(s -> s.attempts));
                if (highScores.size() > 10) highScores = highScores.subList(0, 10);
                saveScores(highScores);

                System.out.println("\u001B[1m--- TOP 10 HIGH SCORES ---\u001B[0m");
                highScores.forEach(s -> System.out.printf("%-15s | %-6s | %3d attempts | %4ds\u001B[0m%n",
                        s.name, s.difficulty.toUpperCase(), s.attempts, s.timeTaken / 1000));
                break;
            } else if (guess < target) {
                System.out.println("\u001B[31mToo low! Try higher.\u001B[0m");
            } else {
                System.out.println("\u001B[31mToo high! Try lower.\u001B[0m");
            }

            if (attempts % 5 == 0) {
                System.out.println("\u001B[36mHint: The number is " + (target % 2 == 0 ? "even" : "odd") + ".\u001B[0m");
            }
        }
        System.out.println("\nThanks for playing! Play again anytime. \uD83C\uDF89");
        sc.close();
    }

    static List<Score> loadScores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("highscores.dat"))) {
            return (List<Score>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    static void saveScores(List<Score> scores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("highscores.dat"))) {
            oos.writeObject(scores);
        } catch (Exception ignored) {}
    }
}