package de.luckycrew.halloween.util;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.item.ItemStack;

public class ItemStackEntry {
	
	private ItemStack stack;
	
	private int min;
	private int max;
	
	public ItemStackEntry(ItemStack stack, int min, int max) {
		this.stack = stack;
		this.min = min;
		this.max = max;
	}
	
	public ItemStackEntry(ItemStack stack) {
		this(stack, stack.stackSize, stack.stackSize);
	}
	
	public ItemStack getItemStack() {
		ItemStack copy = stack.copy();
		copy.stackSize = MathUtil.getRandomNumberInRange(min, max);
		return copy;
	}
	
}
