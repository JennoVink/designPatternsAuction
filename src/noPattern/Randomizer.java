<<<<<<< HEAD
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
=======
package noPattern;

import java.util.Random;

/**
 * Class that provides random values.
 * todo: implement.
 * 
 */
public final class Randomizer {
	private Random generator = new Random();
	
	public static int getRandomInt(){
		return 0;
	}
	
	public static int getRandomInt(int max){
		return 0;
	}
	
	public static boolean getRandomBool(){
		return false;
	}
	
	public static boolean getRandomBool(int percentage){
		return false;
	}
	
	
}
>>>>>>> branch 'master' of https://github.com/JennoVink/designPatternsAuction
