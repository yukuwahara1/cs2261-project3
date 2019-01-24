//============================================================================
//Name        : WumpusWorld.java
//Course      : CS 2261: Objected-Oriented Programming
//Date        : 10/15/2018
//Author      : Yu Kuwahara
//============================================================================

// this class stores the "gameboard"

// Implement a simple Square, WumpusWorld that has a 2d array of Squares and a Project3
// class (so only default constructors), then make sure Project3 can create that default object. 

import java.util.Random;

public class WumpusWorld 
{
	// create a Random object
	private Random rand = new Random();
	
	// 2D array that contains the squares of the game board
	private Square[][] gameBoard;
	
	// array of length 2 that stores current player position
	private int[] playerPosition;
	
	private boolean cheatSetting = false;
	private boolean isRunning = true;
	private boolean gameOver = false;
	
	
	// constructor
	public WumpusWorld()
	{
		
		// create a 4x4 grid of default Squares
		gameBoard = new Square[4][4];
		
		// set player position to (0, 0) .
		//(playerPosition[0], playerPosition[1]) -> (x, y)
		playerPosition = new int[2];
		
		setupBoard();
		
	}
	
	public boolean getRunning() { return isRunning; }
	public boolean getGameOver() {return gameOver; }
	
	// method to setup a board
	public void setupBoard()
	{
		// clear the board of any existing non-default squares
		clearBoard();
		
		// reset the players position
		playerPosition[0] = 0;
		playerPosition[1] = 3;
		
		//==============Generate 3 pits in random location============================================
		int pitCount = 0;
		while(pitCount < 3)
		{
			int pitLocation = rand.nextInt(15); // generates a random number from 0 to 15
			
			// uses a 1d to 2d mapping
			// pitLocation % 4 gives x value
			// pitLocation / 4 gives y value
			// e.g. let pitLocation = 15, then 15 % 4 = 3, 15 / 4 = 3 (since integer division auto floors)
			// so position (3, 3) would be opposite corner from (0, 0) or position 15
			// grid picture of example:
			//
			//	(0,0) -> 0  1  2  3
			//			 4  5  6  7
			//			 8  9  10 11
			//			 12 13 14 15 <- (3, 3)
			//
			// NOTE: (0, 0) could be in top or bottom left corner and still works
			// only places a pit if one is not already there
			
			int x = pitLocation % 4;
			int y = pitLocation / 4;
			if(!gameBoard[x][y].getPit() && !(x == 0 && y == 3))
			{
				gameBoard[x][y].setPit(true);
				
				// set surrounding squares to have breezes (with boundary checking)
				if(x + 1 <= 3) // one square to the right
					gameBoard[x + 1][y].setBreezy(true);
				if(x - 1 >= 0) // one Square to the left
					gameBoard[x - 1][y].setBreezy(true);
				if(y + 1 <= 3) // one Square down
					gameBoard[x][y + 1].setBreezy(true);
				if(y - 1 >= 0) // one Square up
					gameBoard[x][y - 1].setBreezy(true);
				
				++pitCount;
			}
			
		}
		
		//===================Generate Wumpus in a random location========================
		boolean wumpusGenerated = false;
		while(!wumpusGenerated)
		{
			int wumpusLocation = rand.nextInt(15);
			int x = wumpusLocation % 4;
			int y = wumpusLocation / 4;
			
			// check that Wumpus will not be in a pit or on start location
			// do not need to check for gold since it hasn't been generated yet
			if(!(x == 0 && y == 3) && !gameBoard[x][y].getPit())
			{
				// place Wumpus
				gameBoard[x][y].setWumpus(true);
				
				// clear breeze from Wumpus if it happens to land on one (reduce board clutter)
				gameBoard[x][y].setBreezy(false);
				
				// set surrounding squares to have stench
				if(x + 1 <= 3) // one square to the right
					gameBoard[x + 1][y].setStench(true);
				if(x - 1 >= 0) // one Square to the left
					gameBoard[x - 1][y].setStench(true);
				if(y + 1 <= 3) // one Square down
					gameBoard[x][y + 1].setStench(true);
				if(y - 1 >= 0) // one Square up
					gameBoard[x][y - 1].setStench(true);
				
				wumpusGenerated = true;
			}
		}
		
		//===============Generate Gold in random location======================================
		boolean goldGenerated = false;
		while(!goldGenerated)
		{
			int goldLocation = rand.nextInt(15);
			int x = goldLocation % 4;
			int y = goldLocation / 4;
			
			// check that gold is not in a pit, on wumpus, or at star
			if(!(x == 0 && y == 3) && !gameBoard[x][y].getPit() && !gameBoard[x][y].getWumpus())
			{
				gameBoard[x][y].setGold(true);
				goldGenerated = true;
			}
		}
		
		//=======================Place the person=============================================
		gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(true);
		
		//------------------------------------------------------------------------------------
		
		// clear pits of other statuses
		for(int i = 0; i < 3; ++i)
			for(int j = 0; j < 3; ++j)
			{
				if(gameBoard[i][j].getPit())
				{
					gameBoard[i][j].setBreezy(false);
					gameBoard[i][j].setStench(false);
				}
			}
		
		// draw initial board
		drawBoard(false);
	}
	
	// method to display a board
	public void drawBoard(boolean cheat)
	{
		// a square reference.
		// +---------+
		// | breeze  |
		// | stench  | 
		// | gold    |
		// | pit     |
		// | wumpus  |
		// | player  |
		// +---------+
		
		if(cheat)
		{
			// layers of Rows of strings to be built
			String a = "+---------+---------+---------+---------";
			String b = "| ";
			String c = "| ";
			String d = "| ";
			String e = "| ";
			String f = "| ";
			
			// build strings in waves of 3
			for(int i = 0; i < 16; ++i)
			{
				int x = i % 4;
				int y = i / 4;
				
				// reset strings per row
				if(x == 0)
				{
					a = "+---------+---------+---------+---------";
					b = "| ";
					c = "| ";
					d = "| ";
					e = "| ";
					f = "| ";
				}
				
				if(gameBoard[x][y].getBreezy())
					b += "breeze  | ";
				else
					b += "        | ";
				if(gameBoard[x][y].getGold())
					c += "GOLD    | ";
				else
					c += "        | ";
				if(gameBoard[x][y].getStench())
					d += "stench  | ";
				else
					d += "        | ";
				if(gameBoard[x][y].getPerson())
					e += "player  | ";
				else
					e += "        | ";
				if(gameBoard[x][y].getWumpus())
					f += "WUMPUS  | ";
				if(gameBoard[x][y].getPit())
					f += "PIT     | ";
				if(!gameBoard[x][y].getPit() && !gameBoard[x][y].getWumpus())
					f += "        | ";
				
				//Draw the row of squares.
				if(i == 3 || i == 7 || i == 11 || i == 15)
				{
					System.out.print(a + "+");
					System.out.print("\n");
					System.out.print(b);
					System.out.print("\n");
					System.out.print(c);
					System.out.print("\n");
					System.out.print(d);
					System.out.print("\n");
					System.out.print(e);
					System.out.print("\n");
					System.out.print(f);
					System.out.print("\n");
				}
				
			}
			System.out.print(a + "+");
			System.out.print("\n");
		}
		else
		{
			// layers of Rows of strings to be built
			String a = "+---------+---------+---------+---------";
			String b = "| ";
			String c = "| ";
			String d = "| ";
			String e = "| ";
			String f = "| ";
			
			// build strings in waves of 3
			for(int i = 0; i < 16; ++i)
			{
				int x = i % 4;
				int y = i / 4;
				
				// reset strings per row
				if(x == 0)
				{
					a = "+---------+---------+---------+---------";
					b = "| ";
					c = "| ";
					d = "| ";
					e = "| ";
					f = "| ";
				}
				
				if(gameBoard[x][y].getBreezy() && x == playerPosition[0] && y == playerPosition[1])
					b += "breeze  | ";
				else
					b += "        | ";
				if(gameBoard[x][y].getGold() && x == playerPosition[0] && y == playerPosition[1])
					c += "GOLD    | ";
				else
					c += "        | ";
				if(gameBoard[x][y].getStench() && x == playerPosition[0] && y == playerPosition[1])
					d += "stench  | ";
				else
					d += "        | ";
				if(gameBoard[x][y].getPerson() && x == playerPosition[0] && y == playerPosition[1])
					e += "player  | ";
				else
					e += "        | ";
				if(gameBoard[x][y].getWumpus() && x == playerPosition[0] && y == playerPosition[1])
					f += "WUMPUS  | ";
				if(gameBoard[x][y].getPit() && x == playerPosition[0] && y == playerPosition[1])
					f += "PIT     | ";
				else
					f += "        | ";
				
				// draw the row of squares
				if(i == 3 || i == 7 || i == 11 || i == 15)
				{
					System.out.print(a + "+");
					System.out.print("\n");
					System.out.print(b);
					System.out.print("\n");
					System.out.print(c);
					System.out.print("\n");
					System.out.print(d);
					System.out.print("\n");
					System.out.print(e);
					System.out.print("\n");
					System.out.print(f);
					System.out.print("\n");
				}
				
			}
			System.out.print(a + "+");
			System.out.print("\n");
		}
		
	}
	
	// method to update the board
	public void updateGame(char input)
	{
		if(gameOver)
		{
			switch(input)
			{
			case 'y':
				setupBoard();
				break;
			
			case 'n':
				isRunning = false;
				break;
				
			default:
				System.out.println("Invalid input.");
				break;
			}
			return;
		}
		
		// move player
		switch(input)
		{
		case 'n':
			if(playerPosition[1] - 1 >= 0) //if valid move.
			{
				// remove player from current position
				gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(false);
				
				// change position up 1
				--playerPosition[1];
			
				// set the player
				gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(true);
			}
			else // if invalid move
			{
				System.out.println("Invalid move");
			}
			
			// draw updated board
			drawBoard(cheatSetting);
			break;
			
		case 's':
			if(playerPosition[1] + 1 < 4) // if valid move
			{
				// remove player from current position
				gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(false);
				
				// change position up 1
				++playerPosition[1];
			
				// set the player
				gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(true);
			}
			else // if invalid move
			{
				System.out.println("Invalid move");
			}
			
			// draw updated board
			drawBoard(cheatSetting);
			break;
			
		case 'e':
			if(playerPosition[0] + 1 < 4) // if valid move
			{
				// remove player from current position
				gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(false);
				
				// change position up 1
				++playerPosition[0];
			
				// set the player
				gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(true);
			}
			else // if invalid move
			{
				System.out.println("Invalid move");
			}
			
			// draw updated board
			drawBoard(cheatSetting);
			break;
			
		case 'w':
			if(playerPosition[0] - 1 >= 0) // if valid move
			{
				// remove player from current position
				gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(false);
				
				// change position up 1
				--playerPosition[0];
			
				// set the player
				gameBoard[playerPosition[0]] [playerPosition[1]].setPerson(true);
			}
			else // if invalid move
			{
				System.out.println("Invalid move");
			}
			
			// draw updated board
			drawBoard(cheatSetting);
			break;
			
		case 'c':
			cheatSetting = !cheatSetting;
			drawBoard(cheatSetting);
			break;
			
		case 'q':
			isRunning = false;
			break;
			
		default:
			System.out.println("Invalid input.");
			break;
		}
		
		// check for win/loss conditions
		for(int i = 0; i < 16; ++i)
		{
			int x = i % 4;
			int y = i / 4;
			
			if(gameBoard[x][y].getPerson() && gameBoard[x][y].getGold())
			{
				gameOver = true;
				System.out.println("YOU WIN!\nPlay again? (y/n)");
				break;
			}
			
			if(gameBoard[x][y].getPerson() && gameBoard[x][y].getPit())
			{
				gameOver = true;
				System.out.println("YOU FELL INTO A PIT!");
				System.out.println("\nTry Again? (y/n)");
				break;
			}
			
			if(gameBoard[x][y].getPerson() && gameBoard[x][y].getWumpus())
			{
				gameOver = true;
				System.out.println("YOU ENCOUNTERED A FILTHY WUMPUS!");
				System.out.println("\nTry Again? (y/n)");
				break;
			}
		}
		
		
	}
	
	// method to clear the board
	public void clearBoard()
	{
		// iterate through the 2D array of squares and construct a new default square
		for(int i = 0; i < 4; ++i)
		{
			for(int j = 0; j < 4; ++j)
			{
				gameBoard[i][j] = new Square();
			}
		}
		
		gameOver = false;
		cheatSetting = false;
		
		return;
	}
	
}
