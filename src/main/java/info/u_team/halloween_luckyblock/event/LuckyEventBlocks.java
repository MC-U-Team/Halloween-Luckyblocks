package info.u_team.halloween_luckyblock.event;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockBlocks;
import info.u_team.halloween_luckyblock.util.MathUtil;
import net.minecraft.block.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyEventBlocks extends LuckyEvent {
	
	private final ArrayList<BlockState> blocks;
	
	public LuckyEventBlocks() {
		super("Blocks", 6);
		blocks = new ArrayList<>();
		addblocks();
	}
	
	private void addblocks() {
		add(HalloweenLuckyBlockBlocks.luckyblock, 1);
		add(HalloweenLuckyBlockBlocks.pumpkinbomb, 1);
		add(Blocks.DIAMOND_BLOCK, 2);
		add(Blocks.EMERALD_BLOCK, 2);
		add(Blocks.IRON_BLOCK, 5);
		add(Blocks.GOLD_BLOCK, 3);
		add(Blocks.OAK_LOG, 6);
		add(Blocks.OAK_PLANKS, 10);
		add(Blocks.CRAFTING_TABLE, 5);
		add(Blocks.ENCHANTING_TABLE, 2);
		add(Blocks.DIAMOND_BLOCK, 5);
		add(Blocks.IRON_ORE, 7);
		add(Blocks.COAL_BLOCK, 8);
		add(Blocks.FURNACE, 5);
	}
	
	private void add(Block entry, int count) {
		add(entry.getDefaultState(), count);
	}
	
	private void add(BlockState entry, int count) {
		for (int i = 0; i < count; i++) {
			blocks.add(entry);
		}
	}
	
	@Override
	public void execute(ServerPlayerEntity player, World world, BlockPos pos) {
		for (int i = 0; i < MathUtil.getRandomNumberInRange(1, 5); i++) {
			final FallingBlockEntity falling = new FallingBlockEntity(world, pos.getX(), pos.getY() + 5 + (10 * i), pos.getZ(), blocks.get(MathUtil.getRandomNumberInRange(0, blocks.size() - 1)));
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.addEntity(falling);
		}
	}
	
}
