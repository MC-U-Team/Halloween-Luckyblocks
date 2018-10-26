package info.u_team.halloween_luckyblock.util;

import java.lang.reflect.Field;
import java.util.*;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.u_team_core.item.armor.UItemArmor;
import net.minecraft.item.Item;

public class RegistryUtilArmor {
	
	public static List<Item> getArmorRegistryEntries(Class<?> init) {
		List<Item> list = new ArrayList<>();
		try {
			for (Field field : init.getDeclaredFields()) {
				if (UItemArmor[].class.isAssignableFrom(field.getType())) {
					UItemArmor[] array = (UItemArmor[]) field.get(null);
					for (UItemArmor entry : array) {
						list.add(entry);
					}
				}
			}
		} catch (Exception ex) {
			HalloweenLuckyBlockConstants.LOGGER.error("Failed fetching armor registry entries for class {}", init, ex);
		}
		return list;
	}
	
}
