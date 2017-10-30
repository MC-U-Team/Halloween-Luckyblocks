package de.luckycrew.halloween.event;

import de.luckycrew.halloween.sound.HalloweenSounds;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class LuckyEventThunder extends LuckyEventCustom {
	
	public LuckyEventThunder() {
		super("Thunder", 1);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		
		long time = world.getWorldTime();
		EntityPlayer other = world.getClosestPlayerToEntity(player, -1);
		IThreadListener mainthread = (WorldServer) player.worldObj;
		
		world.playSoundAtEntity(player, HalloweenSounds.thunder, 1.0F, 1.0F);
		if (other != null) {
			world.playSoundAtEntity(other, HalloweenSounds.thunder, 1.0F, 1.0F);
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
					EntityLightningBolt light1 = new EntityLightningBolt(world, player.posX, player.posY, player.posZ);
					world.addWeatherEffect(light1);
					if (other != null) {
						EntityLightningBolt light2 = new EntityLightningBolt(world, other.posX, other.posY, other.posZ);
						world.addWeatherEffect(light2);
					}
					world.playSoundAtEntity(player, HalloweenSounds.wind, 1.0F, 1.0F);
					if (other != null) {
						world.playSoundAtEntity(other, HalloweenSounds.wind, 1.0F, 1.0F);
					}
				});
			}
			mainthread.addScheduledTask(() -> world.setWorldTime(time));
		}).start();
	}
}
