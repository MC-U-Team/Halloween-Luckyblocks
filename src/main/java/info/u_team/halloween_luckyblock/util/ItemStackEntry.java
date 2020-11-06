package info.u_team.halloween_luckyblock.util;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.item.ItemStack;

public class ItemStackEntry {
	
	private final ItemStack stack;
	
	private final int min;
	private final int max;
	
	public ItemStackEntry(ItemStack stack, int min, int max) {
		this.stack = stack;
		this.min = min;
		this.max = max;
	}
	
	public ItemStackEntry(ItemStack stack) {
		this(stack, stack.getCount(), stack.getCount());
	}
	
	public ItemStack getItemStack() {
		final ItemStack copy = stack.copy();
		copy.setCount(MathUtil.randomNumberInRange(min, max));
		return copy;
	}
	
}
