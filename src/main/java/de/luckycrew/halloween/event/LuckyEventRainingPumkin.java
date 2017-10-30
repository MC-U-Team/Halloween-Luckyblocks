package de.luckycrew.halloween.event;

import java.util.ArrayList;

import de.luckycrew.halloween.block.HalloweenBlocks;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class LuckyEventRainingPumkin extends LuckyEventCustom {
	
	private ArrayList<Block> blocks;
	
	public LuckyEventRainingPumkin() {
		super("Pumpkin Rain", 2);
		blocks = new ArrayList<Block>();
		for (int i = 0; i < 15; i++) {
			blocks.add(Blocks.pumpkin);
			blocks.add(Blocks.lit_pumpkin);
		}
		blocks.add(HalloweenBlocks.luckyblock);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		player.addChatMessage(new ChatComponentTranslation("luckyevent.rainingpumkin"));
		BlockPos highpos = new BlockPos(pos.getX(), 200, pos.getZ());
		for (int i = 0; i < 150; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-35, 35), MathUtil.getRandomNumberInRange(-5, 20), MathUtil.getRandomNumberInRange(-35, 35));
			EntityFallingBlock falling = new EntityFallingBlock(world, newpos.getX(), newpos.getY(), newpos.getZ(), blocks.get(MathUtil.getRandomNumberInRange(0, blocks.size() - 1)).getDefaultState());
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.spawnEntityInWorld(falling);
		}
	}
	
}
