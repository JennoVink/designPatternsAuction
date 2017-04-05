package noPattern;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Class that can generates random ints, booleans etc.
 * Also able to generate a random url (image url).
 */
public final class Randomizer {

	private static Random generator = new Random();
	
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
	
	/**
	 * Generate a Random bool with a 1 out of @param outOf chance.
	 * @param outOf 
	 * @return bool.
	 */
	public static boolean getRandomBool(int outOf){
		return Randomizer.getRandomInt(0, outOf - 1) == 0;
	}
	
	public static int getRandomInt(int min, int max)
	{
		return generator.nextInt(max + 1 - min) + min;
	}

	/**
	 * Gets a random url from the type @param type. The width and the height are random.
	 * @param type car, plane or bike in this case.
	 * @return URL with the random image (also random width and height).
	 */
	public static URL getRandomUrl(String type) {
		int width = Randomizer.getRandomInt(400, 500);
		int height = Randomizer.getRandomInt(300, 400);
		
		try {
			return new URL("http://loremflickr.com/" + width + "/" + height + "/" + type);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
