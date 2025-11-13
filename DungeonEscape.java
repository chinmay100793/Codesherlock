import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DungeonEscape {
    static class Player {
        String name;
        int health = 100;
        ArrayList<String> inventory = new ArrayList<>();

        Player(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("=== ESCAPE THE DUNGEON ===");
        System.out.print("Enter your hero name: ");
        Player player = new Player(sc.nextLine());

        System.out.println("\nWelcome, " + player.name + "! You awake in a dark dungeon...");
        
        String[] rooms = {"Dark Cell", "Torture Chamber", "Guard Room", "Treasure Vault", "Dragon's Lair"};
        boolean hasKey = false;
        boolean hasSword = false;

        for (int i = 0; i < rooms.length; i++) {
            System.out.println("\n--- You enter the " + rooms[i] + " ---");
            
            if (rand.nextBoolean() && !hasKey) {
                System.out.println("You found a rusty KEY!");
                player.inventory.add("Key");
                hasKey = true;
            }
            if (rand.nextBoolean() && !hasSword) {
                System.out.println("A shiny SWORD lies in the corner!");
                player.inventory.add("Sword");
                hasSword = true;
            }

            if (i < rooms.length - 1) {
                System.out.println("A monster appears! (Health: " + player.health + ")");
                int damage = hasSword ? 20 : 40;
                player.health -= damage;
                System.out.println("You fight bravely... but take " + damage + " damage!");

                if (player.health <= 0) {
                    System.out.println("You have been defeated! Game Over.");
                    return;
                }
            }

            System.out.println("Current inventory: " + player.inventory);
            System.out.println("Health: " + player.health);
            System.out.print("Press Enter to continue to the next room...");
            sc.nextLine();
        }

        System.out.println("\nFinal gate ahead! Do you have the Key? " + (hasKey ? "YES" : "NO"));
        if (hasKey) {
            System.out.println("The gate unlocks! You escape into the sunlight!");
            System.out.println("Congratulations, " + player.name + "! You survived with " + player.health + " health!");
        } else {
            System.out.println("The gate is locked forever... You remain trapped.");
            System.out.println("Game Over.");
        }

        System.out.println("\nThanks for playing!");
        sc.close();
    }
}