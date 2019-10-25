package info.u_team.halloween_luckyblock.util;

import java.util.Random;

public class MathUtil {
	
	public static final Random RANDOM = new Random();
	
	public static int getRandomNumberInRange(int min, int max) {
		return getRandomNumberInRange(RANDOM, min, max);
	}
	
	public static int getRandomNumberInRange(Random random, int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
	
}
