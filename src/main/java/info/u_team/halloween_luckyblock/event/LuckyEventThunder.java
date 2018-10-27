package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockSounds;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.*;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public class LuckyEventThunder extends LuckyEventCustom {
	
	public LuckyEventThunder() {
		super("Thunder", 1);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		
		long time = world.getWorldTime();
		EntityPlayer other = world.getClosestPlayerToEntity(player, -1);
		IThreadListener mainthread = (WorldServer) player.world;
		
		world.playSound(null, player.getPosition(), HalloweenLuckyBlockSounds.thunder, HalloweenLuckyBlockSounds.category, 1.0F, 1.0F);
		if (other != null) {
			world.playSound(null, other.getPosition(), HalloweenLuckyBlockSounds.thunder, HalloweenLuckyBlockSounds.category, 1.0F, 1.0F);
		}
		
		new Thread(() -> {
			mainthread.addScheduledTask(() -> world.setWorldTime(110000));
			for (int i = 0; i < 8; i++) {
				synchronized (this) {
					try {
						wait(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				mainthread.addScheduledTask(() -> {
					EntityLightningBolt light1 = new EntityLightningBolt(world, player.posX, player.posY, player.posZ, false);
					world.addWeatherEffect(light1);
					if (other != null) {
						EntityLightningBolt light2 = new EntityLightningBolt(world, other.posX, other.posY, other.posZ, false);
						world.addWeatherEffect(light2);
					}
					world.playSound(null, player.getPosition(), HalloweenLuckyBlockSounds.wind, HalloweenLuckyBlockSounds.category, 1.0F, 1.0F);
					if (other != null) {
						world.playSound(null, other.getPosition(), HalloweenLuckyBlockSounds.wind, HalloweenLuckyBlockSounds.category, 1.0F, 1.0F);
					}
				});
			}
			mainthread.addScheduledTask(() -> world.setWorldTime(time));
		}).start();
	}
}
