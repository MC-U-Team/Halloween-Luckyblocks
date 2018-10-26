package info.u_team.halloween_luckyblock.util;

import info.u_team.u_team_core.item.armor.UItemArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;

public class ArmorUtility {
	
	public static int getArmorBaseCount(EntityPlayer player, String name) {
		NonNullList<ItemStack> inventory = player.inventory.armorInventory;
		return (int) inventory.stream().filter(itemstack -> {
			if (itemstack.isEmpty()) {
				return false;
			}
			Item item = itemstack.getItem();
			return item instanceof UItemArmor && item.getRegistryName().getPath().contains(name);
		}).count();
	}
	
}
