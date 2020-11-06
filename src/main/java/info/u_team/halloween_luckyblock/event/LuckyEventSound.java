package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.*;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class LuckyEventSound extends LuckyEvent {
	
	public LuckyEventSound() {
		super("Sound", 3);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		world.playSound(null, pos, HalloweenLuckyBlockSounds.COMMON_SOUNDS.get(MathUtil.randomNumberInRange(0, HalloweenLuckyBlockSounds.COMMON_SOUNDS.size() - 1)).get(), HalloweenLuckyBlockSounds.CATEGORY, 1.0F, 1.0F);
		new Thread(() -> {
			try {
				synchronized (this) {
					wait(5000);
				}
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			world.getServer().execute(() -> {
				final FallingBlockEntity falling = new FallingBlockEntity(world, pos.getX(), pos.getY() + 15, pos.getZ(), HalloweenLuckyBlockBlocks.LUCKYBLOCK.get().getDefaultState());
				falling.fallTime = 100;
				falling.shouldDropItem = false;
				falling.setHurtEntities(true);
				world.addEntity(falling);
			});
		}).start();
	}
	
}
