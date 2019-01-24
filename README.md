# cs2261-project3
Board game called Wumpus World

The third project for Objected-Oriented Programming (Java) was making 
a boardgame called Wumpus World. The board is made by 4x4 squares. There are
Wumpus that causes stench, PIT that causes breeze, Gold, and a player on the 
board. The player wins if s/he can find gold and the player loses is s/he 
steps on PIT and Wumpus. A tip for this game is guessing where Wumpus and PIT
would be by the locations of stench and breeze. The locations of all the 
pbjects get shuffled everytime the user plays the game. Project3 class, 
Square class, and WumpusWorld class composes this project. 

Square class represents what is inside of each of the 16 squares on
the board. This class has a degault constructor, getters and setters for
all the private variables for the objects in the game. 

WumpusWorld class reperents the gameboard. This class has a constructor 
to create the 4x4 grids and set the start position for the player, a method to 
setup a board, a method to display the board, a method to update the board, and
a method to clear the board. For a method to display the board, I treated it 
as composed by horizontal lines. For a method to update the board when the 
player inputs a choice, I used switch.

Project 3 class displays the explanation about this game and creates
an instance of the board and constrolls the game such as when to end by using
while loop.

In my opinion, this project was the hardest of all in this course
and there were many problems so I had to completely start over the whole 
project. One of the problems was that in WumpusWorld class I was trying to make 
everything work inside of the constructor. However, this project involves 
many processes and the constructor was huge so I often forgot what I was doing
and what to do next. I ended up having 4 methods instead of 2. By working on 
this project, I learned the importance of brainstorming and drawing all the 
plans on a piece of paper before actually start coding. Also creating as many
methods as possible is very useful in such a big scale project for many 
reasons.

What I can do to improve this project is calculating the statistics of
the result like project 2. To do this I can simply use for loop by using a 
counter inside of the loop.
