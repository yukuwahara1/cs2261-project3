//============================================================================
//Name        : Project3.java
//Course      : CS 2261: Objected-Oriented Programming
//Date        : 10/15/2018
//Author      : Yu Kuwahara
//============================================================================

// this class allows a user to play a board game called Wumpus World
// by combining it with Square class and WumpusWorld class

// take a user input
import java.util.Scanner;

public class Project3 {

	public static void main(String[] args)
	{

		System.out.println("Welcome to Wumpus World!");
		
		// explain about this game
		System.out.println("============================================================================");
		System.out.println("In this world, you are a brave explore whose job is finding gold by avoiding");
		System.out.println("Wumpuses that cause stench and PITs that cause breeze.");
		System.out.println("You win if you can find gold and you lose if you encounter a Wumpus or PIT.");
		System.out.println("\n");
		System.out.println("These are your choices on the board");
		System.out.println("n : go North, North or north or N does not work as input");
		System.out.println("e : go East, East or east or E does not work as input");
		System.out.println("s : go South, South or south or S does not work as input");
		System.out.println("w : go West, West or west or W does not work as input)");
		System.out.println("c : cheat = you can see where everything is on the board, ");
		System.out.println("Cheat or cheat or C does not work as input");
		System.out.println("\n");
		System.out.println("The tip of this game is guessing where Wumput and PIT would be with");
		System.out.println("the locations of stench that is caused by Wumpus");
		System.out.println("and breeze that is caused by PIT.");
		System.out.println("\n");
		System.out.println("Your start position is always at bottom left, (0,0).");
		System.out.println("============================================================================");
		System.out.println("\n");
		
		System.out.println("Let's begin the journey!");
		System.out.println("\n");
		
		// take the user input as a character
		Scanner sc = new Scanner(System.in);
		
		// create an instance of WumpusWorld to play a game
		WumpusWorld game = new WumpusWorld();
		
		// keep taking the user input until the user decides to stop 
		while(game.getRunning())
		{
			char playerChoice = sc.next().charAt(0);
			game.updateGame(playerChoice);
		}
			
		sc.close();
		
		// the end of the game 
		System.out.print("Thank you for playing the game!");
	}

}
