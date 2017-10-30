package de.luckycrew.halloween.event;

import java.util.ArrayList;

import de.luckycrew.halloween.block.HalloweenBlocks;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LuckyEventBlocks extends LuckyEventCustom {
	
	private ArrayList<IBlockState> blocks;
	
	public LuckyEventBlocks() {
		super("Blocks", 6);
		blocks = new ArrayList<>();
		addblocks();
	}
	
	private void addblocks() {
		add(HalloweenBlocks.luckyblock, 1);
		add(HalloweenBlocks.pumpkinbomb, 1);
		add(Blocks.diamond_block, 2);
		add(Blocks.emerald_block, 2);
		add(Blocks.iron_block, 5);
		add(Blocks.gold_block, 3);
		add(Blocks.log, 6);
		add(Blocks.planks, 10);
		add(Blocks.crafting_table, 5);
		add(Blocks.enchanting_table, 2);
		add(Blocks.diamond_ore, 5);
		add(Blocks.iron_ore, 7);
		add(Blocks.coal_block, 8);
		add(Blocks.furnace, 5);
	}
	
	private void add(Block entry, int count) {
		add(entry.getDefaultState(), count);
	}
	
	private void add(IBlockState entry, int count) {
		for (int i = 0; i < count; i++) {
			blocks.add(entry);
		}
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		for (int i = 0; i < MathUtil.getRandomNumberInRange(1, 5); i++) {
			EntityFallingBlock falling = new EntityFallingBlock(world, pos.getX(), pos.getY() + 5 + (10 * i), pos.getZ(), blocks.get(MathUtil.getRandomNumberInRange(0, blocks.size() - 1)));
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.spawnEntityInWorld(falling);
		}
	}
	
}
