package de.luckycrew.halloween.util;

import info.u_team.u_team_core.item.armor.UItemArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ArmorUtility {
	
	public static int getArmorBaseCount(EntityPlayer player, String name) {
		int count = 0;
		ItemStack[] inventory = player.inventory.armorInventory;
		for (int i = 0; i < inventory.length; ++i) {
			if (inventory[i] != null && inventory[i].getItem() instanceof UItemArmor) {
				UItemArmor item = (UItemArmor) inventory[i].getItem();
				if (item.getRegistryName().contains(name)) {
					count++;
				}
			}
		}
		return count;
	}
	
}
