package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockCreativeTabs;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.item.*;

public class ItemWitchsBroomstick extends UItem {
	
	public ItemWitchsBroomstick(String name) {
		super(name, HalloweenLuckyBlockCreativeTabs.tab);
		setMaxDamage(300);
		setMaxStackSize(1);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !areItemsEqual(oldStack, newStack);
	}
	
	public boolean areItemsEqual(ItemStack a, ItemStack b) {
		return a == null && b == null ? true : (a != null && b != null ? isItemEqualWithoutDamage(a, b) : false);
	}
	
	public boolean isItemEqualWithoutDamage(ItemStack a, ItemStack b) {
		return b != null && a.getItem() == b.getItem();
	}
}
