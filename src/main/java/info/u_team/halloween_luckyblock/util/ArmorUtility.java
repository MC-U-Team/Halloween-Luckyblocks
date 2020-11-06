package info.u_team.halloween_luckyblock.util;

import info.u_team.u_team_core.item.armor.UArmorItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;

public class ArmorUtility {
	
	public static int getArmorBaseCount(PlayerEntity player, String name) {
		final NonNullList<ItemStack> inventory = player.inventory.armorInventory;
		return (int) inventory.stream().filter(itemstack -> {
			if (itemstack.isEmpty()) {
				return false;
			}
			final Item item = itemstack.getItem();
			return item instanceof UArmorItem && item.getRegistryName().getPath().contains(name);
		}).count();
	}
	
}
