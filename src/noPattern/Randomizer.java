package noPattern;

import java.util.Random;

public final class Randomizer {

	public static Random generator = new Random();
	
	public static int getRandomInt()
	{
		return generator.nextInt();
	}
	
	public static int getRandomInt(int max)
	{
		return generator.nextInt(max);
	}
	
	public static boolean getRandomBool()
	{
		return generator.nextBoolean();
	}
	
	public static int getRandomInt(int min, int max)
	{
		return generator.nextInt(max + 1 - min) + min;
	}
}
