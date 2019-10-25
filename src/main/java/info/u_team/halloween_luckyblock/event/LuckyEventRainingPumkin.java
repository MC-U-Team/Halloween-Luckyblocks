package info.u_team.halloween_luckyblock.event;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockBlocks;
import info.u_team.halloween_luckyblock.util.MathUtil;
import net.minecraft.block.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class LuckyEventRainingPumkin extends LuckyEvent {
	
	private ArrayList<Block> blocks;
	
	public LuckyEventRainingPumkin() {
		super("Pumpkin Rain", 2);
		blocks = new ArrayList<Block>();
		for (int i = 0; i < 15; i++) {
			blocks.add(Blocks.PUMPKIN);
			blocks.add(Blocks.JACK_O_LANTERN);
		}
		blocks.add(HalloweenLuckyBlockBlocks.luckyblock);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, World world, BlockPos pos) {
		player.sendMessage(new TranslationTextComponent("luckyevent.rainingpumkin"));
		BlockPos highpos = new BlockPos(pos.getX(), 200, pos.getZ());
		for (int i = 0; i < 150; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-35, 35), MathUtil.getRandomNumberInRange(-5, 20), MathUtil.getRandomNumberInRange(-35, 35));
			FallingBlockEntity falling = new FallingBlockEntity(world, newpos.getX(), newpos.getY(), newpos.getZ(), blocks.get(MathUtil.getRandomNumberInRange(0, blocks.size() - 1)).getDefaultState());
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.addEntity(falling);
		}
	}
	
}
