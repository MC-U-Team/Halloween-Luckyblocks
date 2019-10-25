package info.u_team.halloween_luckyblock.util;

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
		this(stack, stack.getCount(), stack.getCount());
	}
	
	public ItemStack getItemStack() {
		ItemStack copy = stack.copy();
		copy.setCount(MathUtil.getRandomNumberInRange(min, max));
		return copy;
	}
	
}
