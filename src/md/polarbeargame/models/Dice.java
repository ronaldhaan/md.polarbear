package md.polarbeargame.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import md.polarbeargame.exceptions.ExceptionCodes;
import ec.shared.Utility;

import java.awt.Graphics;
import java.awt.Color;


/**
 * Object that represents a dice.
 * @see paintComponent
 * @author R Haan
 * @since 10-3-2017
 * @version 3.0
 */
public class Dice
{
	private int eyes;
	private int x;
	private int y;
	private int size;	
	private DiceProps props;
	private Map<Integer, DiceProps> dictionary;
	
	private Random rand;	
	
	public Dice(Integer x, Integer y, Integer size) {
		this();
		setPos(x, y);
		this.size = size;
	}
	
	public Dice() 
	{
		rand = new Random();
		props = new DiceProps();
		
		dictionary = new HashMap<Integer, DiceProps>();
		dictionary.put(1, new DiceProps(1, 6, 0));
		dictionary.put(3, new DiceProps(1, 4, 2));
		dictionary.put(5, new DiceProps(1, 2, 4));
	}
	
	/**
	 * Sets the position.
	 * @param X
	 * @param Y
	 */
	public void setPos( int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the this.eyes.
	 */
	public void setEyes()
	{
		// Generate random integer between 1 and 6
		eyes = rand.nextInt(6) + 1;

		if(dictionary.keySet().contains(eyes)) {
			props = dictionary.get(eyes);
		}
		
	}
	
	/**
	 * Gets the X position.
	 * @return the X postion.
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Gets the Y position.
	 * @return The Y position.
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Gets the this.eyes.
	 * @return The this.eyes
	 */
	public int getEyes()
	{
		return eyes;
	}
	
	/**
	 * Gets the Wakes.
	 * @return the wakes.
	 */
	public int getWakes()
	{
		return props.getWakes();
	}
	
	/**
	 * Gets the polar bears
	 * @return the polar bears
	 */
	public int getPolarBears()
	{
		return props.getPolarBears();
	}
	
	/**
	 * Gets the Pinguins.
	 * @return the pinguins.
	 */
	public int getPinguins()
	{
		return props.getPinguins();
	}
	
	/**
	 * 	Draws the Dice.
	 * @return Graphics
	 */
	public Graphics draw(Graphics g)
	{
		//
		// position of the eye in the middle of the dice
		//
		Integer[] middle = { 1, 3, 5 };
		Integer[] cornersOfTwo = { 2, 3, 4, 5, 6 };
		Integer[] otherCorners = { 4, 5, 6 };

		//
		// Define the this.size of the this.eyes.
		//
		int dot = size/6; 

		int centered = (size / 2) - (dot / 2);
		int padding = size / 7;
		int paddingBottom = size - dot - padding;

		try
		{
			g.setColor(Color.white);
			g.fillRoundRect(x, y, size, size, 25, 25);
			g.setColor(Color.black);		 
			g.drawRoundRect(x, y, size, size, 25, 25);	
			

			if 	(contains(middle, eyes))
			{
				g.fillOval(x + centered, y + centered, dot, dot);			
			}
			if	(contains(cornersOfTwo, eyes) )
			{
				g.fillOval(x + padding, y + padding, dot, dot);	// links boven
				g.fillOval(x + paddingBottom, y + paddingBottom, dot, dot); // rechts onder
			} 
			if	(contains(otherCorners, eyes)) 
			{
				g.fillOval(x + paddingBottom, y + padding, dot, dot); // linksonder
				g.fillOval(x + padding, y + paddingBottom, dot, dot); // rechts boven
			}
			if	(eyes == 6 )
			{		
				g.fillOval(x + padding, y + centered, dot, dot); // linksmidden
				g.fillOval(x + paddingBottom, y + centered, dot, dot); // rechtmidden
			}				
		}
		catch(Exception ex)
		{
			Utility.handleUnexpectedException(ExceptionCodes.DICE1, ex, true);
		}
		
		return g;
	}

	public static <T> Boolean contains(T[] a, T v) {
		for (T t : a) {
			if(t == v) return true;
		}

		return false;
	}
}


