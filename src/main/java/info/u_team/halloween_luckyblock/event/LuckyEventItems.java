package info.u_team.halloween_luckyblock.event;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.util.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyEventItems extends LuckyEvent {
	
	private ArrayList<ItemStackEntry> stacks;
	
	public LuckyEventItems() {
		super("Items", 6);
		stacks = new ArrayList<>();
		additems();
	}
	
	private void additems() {
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.candy)), 4);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.candybag), 1, 2), 8);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.candybag, 1, 1)), 1);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.killerknive)), 5);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.pumpkingrenade), 1, 5), 3);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.teleporter)), 3);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.witchsbroomstick)), 1);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockBlocks.pumpkinbomb), 1, 2), 2);
		
		for (UItemArmor item : HalloweenLuckyBlockItems.scarecrow) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		for (UItemArmor item : HalloweenLuckyBlockItems.slender) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		for (UItemArmor item : HalloweenLuckyBlockItems.witch) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		for (UItemArmor item : HalloweenLuckyBlockItems.zombie) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		for (UItemArmor item : HalloweenLuckyBlockItems.clown) {
			add(new ItemStackEntry(new ItemStack(item)), 2);
		}
		
		add(new ItemStackEntry(new ItemStack(Blocks.STONE), 10, 64), 3);
		add(new ItemStackEntry(new ItemStack(Blocks.COBBLESTONE), 10, 64), 3);
		
		for (int i = 0; i < 16; i++) {
			add(new ItemStackEntry(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, i), 10, 64), 1);
		}
	}
	
	private void add(ItemStackEntry entry, int count) {
		for (int i = 0; i < count; i++) {
			stacks.add(entry);
		}
	}
	
	@Override
	public void execute(ServerPlayerEntity player, World world, BlockPos pos) {
		for (int i = 0; i < MathUtil.getRandomNumberInRange(1, 3); i++) {
			ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stacks.get(MathUtil.getRandomNumberInRange(0, stacks.size() - 1)).getItemStack());
			world.addEntity(item);
		}
	}
	
}
