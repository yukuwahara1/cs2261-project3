//============================================================================
//Name        : Square.java
//Course      : CS 2261: Objected-Oriented Programming
//Date        : 10/15/2018
//Author      : Yu Kuwahara
//============================================================================

// this class represents a square which the user is at
// Square class should not have any method other than 
// appropriate getters and setters 

public class Square 
{	
	private boolean isBreezy;
	private boolean hasStench;
	private boolean hasPit;
	private boolean hasPerson;
	private boolean hasWumpus;
	private boolean hasGold;
	
	// default constructor
	public Square()
	{
		isBreezy = false;
		hasStench = false;
		hasPit = false;
		hasPerson = false;
		hasWumpus = false;
		hasGold = false;
	}
	
	// getters
	public boolean getBreezy() 
	{ 
		return isBreezy; 
	}
	
	public boolean getStench() 
	{ 
		return hasStench; 
	}
	
	public boolean getPit()
	{ 
		return hasPit;
	}
	
	public boolean getPerson() 
	{ 
		return hasPerson;
	}
	
	public boolean getWumpus()
	{ 
		return hasWumpus;
	}
	
	public boolean getGold() 
	{ 
		return hasGold;
	}
	
	// setters
	public void setBreezy(boolean setting)
	{
		isBreezy = setting;
		return;
	}
	public void setStench(boolean setting)
	{
		hasStench = setting;
		return;
	}
	public void setPit(boolean setting)
	{
		hasPit = setting;
		return;
	}
	public void setPerson(boolean setting)
	{
		hasPerson = setting;
		return;
	}
	public void setWumpus(boolean setting)
	{
		hasWumpus = setting;
		return;
	}
	public void setGold(boolean setting)
	{
		hasGold = setting;
		return;
	}
	
}
