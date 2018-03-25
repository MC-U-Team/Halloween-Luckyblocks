package de.luckycrew.halloween.event;

import java.util.ArrayList;

import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.item.HalloweenItems;
import de.luckycrew.halloween.util.ItemStackEntry;
import info.u_team.u_team_core.item.armor.UItemArmor;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LuckyEventItems extends LuckyEventCustom {
	
	private ArrayList<ItemStackEntry> stacks;
	
	public LuckyEventItems() {
		super("Items", 6);
		stacks = new ArrayList<>();
		additems();
	}
	
	private void additems() {
		add(new ItemStackEntry(new ItemStack(HalloweenItems.candy)), 4);
		add(new ItemStackEntry(new ItemStack(HalloweenItems.candybag), 1, 2), 8);
		add(new ItemStackEntry(new ItemStack(HalloweenItems.candybag, 1, 1)), 1);
		add(new ItemStackEntry(new ItemStack(HalloweenItems.killerknive)), 5);
		add(new ItemStackEntry(new ItemStack(HalloweenItems.pumpkingrenade), 1, 5), 3);
		add(new ItemStackEntry(new ItemStack(HalloweenItems.teleporter)), 3);
		add(new ItemStackEntry(new ItemStack(HalloweenItems.witchsbroomstick)), 1);
		add(new ItemStackEntry(new ItemStack(HalloweenBlocks.pumpkinbomb), 1, 2), 2);
		
		for (UItemArmor item : HalloweenItems.scarecrow) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		for (UItemArmor item : HalloweenItems.slender) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		for (UItemArmor item : HalloweenItems.witch) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		for (UItemArmor item : HalloweenItems.zombie) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		for (UItemArmor item : HalloweenItems.clown) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		
		add(new ItemStackEntry(new ItemStack(Blocks.stone), 10, 64), 3);
		add(new ItemStackEntry(new ItemStack(Blocks.cobblestone), 10, 64), 3);
		
		for (int i = 0; i < 16; i++) {
			add(new ItemStackEntry(new ItemStack(Blocks.stained_hardened_clay, 1, i), 10, 64), 1);
		}
	}
	
	private void add(ItemStackEntry entry, int count) {
		for (int i = 0; i < count; i++) {
			stacks.add(entry);
		}
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		for (int i = 0; i < MathUtil.getRandomNumberInRange(1, 3); i++) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stacks.get(MathUtil.getRandomNumberInRange(0, stacks.size() - 1)).getItemStack());
			world.spawnEntityInWorld(item);
		}
	}
	
}
