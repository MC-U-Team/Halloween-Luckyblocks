package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockSounds;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public class LuckyEventDeath extends LuckyEvent {
	
	public LuckyEventDeath() {
		super("Death", 2);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, World world, BlockPos pos) {
		world.playSound(null, pos, HalloweenLuckyBlockSounds.GAME_DEATH, HalloweenLuckyBlockSounds.CATEGORY, 1.0F, 1.0F);
		new Thread(() -> {
			try {
				synchronized (this) {
					wait(8500);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			world.getServer().execute(() -> {
				world.createExplosion(null, player.posX, player.posY, player.posZ, 20.0F, false, Explosion.Mode.DESTROY);
				player.onKillCommand();
			});
		}).start();
	}
	
}
