package info.u_team.halloween_luckyblock.sound;

import java.lang.reflect.*;
import java.util.*;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;

public class HalloweenSounds {
	
	public static String basta, game_death, haha, happy_halloween, hi_im_chucky, one_two, organ, ringle, scubi_dabi, snivelling, strange_laughing, strange, tension, thunder, wind, wuhu, you_have;
	
	public static ArrayList<String> sounds;
	
	public HalloweenSounds() {
		load();
		sounds = new ArrayList<>(getFromClass());
	}
	
	private void load() {
		for (Field field : this.getClass().getDeclaredFields()) {
			try {
				if (field.getType().isAssignableFrom(String.class) && Modifier.isStatic(field.getModifiers())) {
					field.set(null, HalloweenLuckyBlockConstants.MODID + ":" + field.getName());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public Collection<String> getFromClass() {
		Collection<String> col = new ArrayList<String>();
		for (Field field : HalloweenSounds.class.getDeclaredFields()) {
			try {
				if (field.getType().isAssignableFrom(String.class)) {
					if (field.getName() != "organ" && field.getName() != "game_death") {
						col.add(field.get(null).toString());
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return col;
	}
}
