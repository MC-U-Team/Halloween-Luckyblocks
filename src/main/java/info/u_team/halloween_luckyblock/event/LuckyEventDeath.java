package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.sound.HalloweenSounds;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public class LuckyEventDeath extends LuckyEventCustom {
	
	public LuckyEventDeath() {
		super("Death", 2);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		world.playSoundAtEntity(player, HalloweenLuckyBlockSounds.game_death, 1.0F, 1.0F);
		IThreadListener mainthread = (WorldServer) player.worldObj;
		new Thread(() -> {
			synchronized (this) {
				try {
					wait(8500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			mainthread.addScheduledTask(() -> {
				world.createExplosion(null, player.posX, player.posY, player.posZ, 20.0F, false);
				player.onKillCommand();
			});
		}).start();
	}
	
}
