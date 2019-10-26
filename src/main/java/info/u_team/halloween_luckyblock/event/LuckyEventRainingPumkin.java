package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockBlocks;
import info.u_team.halloween_luckyblock.util.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class LuckyEventRainingPumkin extends LuckyEvent {
	
	public LuckyEventRainingPumkin() {
		super("Pumpkin Rain", 2);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, World world, BlockPos pos) {
		player.sendMessage(new TranslationTextComponent("luckyevent.rainingpumkin"));
		BlockPos highpos = new BlockPos(pos.getX(), 200, pos.getZ());
		for (int i = 0; i < 150; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-35, 35), MathUtil.getRandomNumberInRange(-5, 20), MathUtil.getRandomNumberInRange(-35, 35));
			FallingBlockEntity falling = new FallingBlockEntity(world, newpos.getX() + .5, newpos.getY(), newpos.getZ() + .5, MathUtil.getRandomNumberInRange(0, 30) == 0 ? HalloweenLuckyBlockBlocks.LUCKYBLOCK.getDefaultState() : PumpkinUtil.getRandomPumpkin());
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.addEntity(falling);
		}
	}
	
}
